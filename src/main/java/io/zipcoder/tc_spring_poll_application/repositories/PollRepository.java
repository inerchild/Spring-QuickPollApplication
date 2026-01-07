package io.zipcoder.tc_spring_poll_application.repositories;

import io.zipcoder.tc_spring_poll_application.domain.Poll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository with pagination support.
 * 
 * PagingAndSortingRepository adds:
 * - Page<Poll> findAll(Pageable pageable)
 * - Iterable<Poll> findAll(Sort sort)
 * 
 * CrudRepository provides basic CRUD operations.
 * 
 * By extending both, we get ALL features!
 */
public interface PollRepository extends 
        PagingAndSortingRepository<Poll, Long>, 
        CrudRepository<Poll, Long> {
    
    // No methods needed - Spring Data provides everything!
}