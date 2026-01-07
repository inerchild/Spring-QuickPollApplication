package io.zipcoder.tc_spring_poll_application.repositories;

import io.zipcoder.tc_spring_poll_application.domain.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for Vote entity.
 * 
 * Includes a custom query to find all votes for a specific poll.
 */
public interface VoteRepository extends CrudRepository<Vote, Long> {
    
    /**
     * Custom query - Find all votes for a specific poll.
     * 
     * WHY CUSTOM QUERY?
     * Vote only references Option (not Poll directly)
     * So we need to join through Option to get votes by Poll
     * 
     * THE SQL QUERY EXPLAINED:
     * SELECT v.*                    -- Get all vote columns
     * FROM Option o, Vote v         -- From Option and Vote tables
     * WHERE o.POLL_ID = ?1          -- Where Option belongs to this poll
     * AND v.OPTION_ID = o.OPTION_ID -- And Vote is for that option
     * 
     * ?1 PLACEHOLDER:
     * - Spring replaces ?1 with the pollId parameter
     * - ?2 would be second parameter, etc.
     * 
     * EXAMPLE:
     * findVotesByPoll(5L) becomes:
     * SELECT v.* FROM Option o, Vote v
     * WHERE o.POLL_ID = 5
     * AND v.OPTION_ID = o.OPTION_ID
     * 
     * RESULT:
     * Returns all votes cast for ANY option in poll #5
     * 
     * @param pollId The poll ID to find votes for
     * @return All votes for that poll
     */
    @Query(value = "SELECT v.* " +
            "FROM Option o, Vote v " +
            "WHERE o.POLL_ID = ?1 " +
            "AND v.OPTION_ID = o.OPTION_ID", 
            nativeQuery = true)
    Iterable<Vote> findVotesByPoll(Long pollId);
}
