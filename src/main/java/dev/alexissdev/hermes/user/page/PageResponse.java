package dev.alexissdev.hermes.user.page;

import dev.alexissdev.hermes.user.User;

import java.util.List;

/**
 * PageResponse is a record used to encapsulate paginated information about a list of User entities.
 * It includes details about the current page, total pages, total items, and the list of User entities
 * within a specific page.
 *
 * @param userList the list of User entities for the current page
 * @param currentPage the current page number in the pagination
 * @param totalPages the total number of pages available
 * @param totalItems the total number of items across all pages
 */

public record PageResponse(List<User> userList, int currentPage, int totalPages, long totalItems) {
}
