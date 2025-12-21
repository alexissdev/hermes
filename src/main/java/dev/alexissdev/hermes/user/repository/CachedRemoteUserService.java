package dev.alexissdev.hermes.user.repository;

import dev.alexissdev.hermes.redis.RedisConfiguration;
import dev.alexissdev.hermes.user.User;
import dev.alexissdev.hermes.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User save(User user, boolean updateCache) {
        if (user ==  null) {
            LOGGER.warning("User cannot be null for save operation.");
            return User.NULL_USER;
        }

        User savedUser = userRepository.save(user);
        if (updateCache) {
            updateInCache(savedUser);
        } else {
            // TODO: Ideally, when a player leaves the Minecraft server, they should no longer be cached.
            deleteCache(savedUser.getId());
        }
        return savedUser;
    }

    @Override
    public User updateInCache(User user) {
        if (user ==  null) {
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

    private void deleteCache(String id) {
        if (id ==  null) {
            LOGGER.warning("User ID cannot be null for cache deletion.");
            return;
        }

        redisCache.delete(String.format(RedisConfiguration.USER_KEY, id));
    }

    private void cacheObject(String id, Object object) {
        if (id ==  null || object ==  null) {
            LOGGER.warning("User ID or object cannot be null for cache operation.");
            return;
        }

        redisCache.opsForValue().set(RedisConfiguration.USER_KEY.formatted(id), object);
    }
}
