package dev.alexissdev.hermes.user.service;

import dev.alexissdev.hermes.redis.RedisConfiguration;
import dev.alexissdev.hermes.user.User;
import dev.alexissdev.hermes.user.controller.request.UpdateUserRequest;
import dev.alexissdev.hermes.user.economy.UserEconomy;
import dev.alexissdev.hermes.user.page.PageResponse;
import dev.alexissdev.hermes.user.repository.UserRepository;
import dev.alexissdev.hermes.user.statistic.UserStatistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CachedRemoteUserService
        implements UserService {

    private static final Logger LOGGER = Logger.getLogger(CachedRemoteUserService.class.getName());
    private final UserRepository userRepository;
    private final RedisTemplate<String, Object> redisCache;

    @Autowired
    public CachedRemoteUserService(UserRepository userRepository, RedisTemplate<String, Object> redisCache) {
        this.userRepository = userRepository;
        this.redisCache = redisCache;
    }

    @Override
    public List<User> findAll() {
        return new LinkedList<>(userRepository.findAll());
    }

    @Override
    public PageResponse findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageable);

        return new PageResponse(
                userPage.getContent(),
                userPage.getNumber(),
                userPage.getTotalPages(),
                userPage.getTotalElements()
        );
    }

    @Override
    public Optional<User> findById(String id) {
        User cachedUser = (User) redisCache.opsForValue().get(String.format(RedisConfiguration.USER_KEY, id));
        if (cachedUser != null) {
            return Optional.of(cachedUser);
        }

        return Optional.ofNullable(userRepository.findById(id).map((user) -> {
            cacheObject(id, user);
            return user;
        }).orElse(User.NULL_USER));
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public Optional<User> save(User user, boolean updateCache) {
        if (user == null) {
            LOGGER.warning("User cannot be null for save operation.");
            return Optional.empty();
        }

        User savedUser = userRepository.save(user);
        if (updateCache) {
            updateInCache(savedUser);
        } else {
            // TODO: Ideally, when a player leaves the Minecraft server, they should no longer be cached.
            deleteCache(savedUser.getId());
        }

        return Optional.of(savedUser);
    }

    @Override
    public User updateInCache(User user) {
        if (user == null) {
            LOGGER.warning("User cannot be null for update operation.");
            return User.NULL_USER;
        }

        cacheObject(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> deleteById(String id) {
        return Optional.ofNullable(findById(id).map(user -> {
            deleteCache(user.getId());
            userRepository.deleteById(id);
            return user;
        }).orElseGet(() -> {
            LOGGER.warning(String.format("User with ID %s not found in database.", id));
            return User.NULL_USER;
        }));
    }

    @Override
    public Optional<User> update(String id, UpdateUserRequest request) {
        return userRepository.findById(id).map(user -> {
            UserEconomy economy = user.getEconomy();
            economy.setCoins(request.coins());
            economy.setGems(request.gems());

            UserStatistic stats = user.getStatistic();
            stats.setKills(request.kills());
            stats.setDeaths(request.deaths());
            stats.setWins(request.wins());
            stats.setLosses(request.losses());

            user.setLanguage(request.language());
            return userRepository.save(user);
        });
    }

    private void deleteCache(String id) {
        if (id == null) {
            LOGGER.warning("User ID cannot be null for cache deletion.");
            return;
        }

        redisCache.delete(String.format(RedisConfiguration.USER_KEY, id));
    }

    private void cacheObject(String id, Object object) {
        if (id == null || object == null) {
            LOGGER.warning("User ID or object cannot be null for cache operation.");
            return;
        }

        redisCache.opsForValue().set(RedisConfiguration.USER_KEY.formatted(id), object);
    }
}
