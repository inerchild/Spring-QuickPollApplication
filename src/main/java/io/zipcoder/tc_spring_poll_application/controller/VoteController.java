package io.zipcoder.tc_spring_poll_application.controller;

import io.zipcoder.tc_spring_poll_application.domain.Vote;
import io.zipcoder.tc_spring_poll_application.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * REST Controller for Vote operations.
 * 
 * ENDPOINTS:
 * - POST /polls/{pollId}/votes  → Cast a vote
 * - GET  /polls/{pollId}/votes  → Get all votes for a poll
 * - GET  /polls/votes           → Get all votes (all polls)
 */
@RestController
public class VoteController {
    
    /**
     * Repository for vote database operations.
     */
    private VoteRepository voteRepository;
    
    @Autowired
    public VoteController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }
    
    /**
     * POST /polls/{pollId}/votes - Cast a vote for a poll option
     * 
     * URL STRUCTURE:
     * POST /polls/5/votes
     *   - pollId = 5 (from URL)
     *   - vote data in request body (which option was chosen)
     * 
     * REQUEST BODY EXAMPLE:
     * {
     *   "option": {
     *     "id": 1,
     *     "value": "Stranger Things"
     *   }
     * }
     * 
     * RESPONSE:
     * - Status: 201 CREATED
     * - Location header: /polls/5/votes/1
     * 
     * @param pollId ID of the poll (from URL)
     * @param vote Vote data (from request body)
     * @return HTTP 201 CREATED with Location header
     */
    @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.POST)
    public ResponseEntity<?> createVote(@PathVariable Long pollId, @RequestBody Vote vote) {
        // Save vote to database
        vote = voteRepository.save(vote);
        
        // Build URI for newly created vote
        // Example: /polls/5/votes/1
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(
            ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(vote.getId())
                .toUri()
        );
        
        // Return 201 CREATED with Location header
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
    
    /**
     * GET /polls/votes - Get ALL votes from ALL polls
     * 
     * Returns every vote in the database.
     * 
     * @return All votes with HTTP 200 OK
     */
    @RequestMapping(value = "/polls/votes", method = RequestMethod.GET)
    public Iterable<Vote> getAllVotes() {
        // Return all votes from database
        return voteRepository.findAll();
    }
    
    /**
     * GET /polls/{pollId}/votes - Get all votes for a specific poll
     * 
     * Uses custom query: findVotesByPoll(pollId)
     * 
     * EXAMPLE: GET /polls/5/votes
     *   - Returns all votes cast for poll #5
     * 
     * HOW IT WORKS:
     * 1. VoteRepository.findVotesByPoll(5) runs custom SQL
     * 2. Joins Vote → Option → Poll
     * 3. Returns all votes where Poll ID = 5
     * 
     * @param pollId ID of the poll
     * @return All votes for that poll with HTTP 200 OK
     */
    @RequestMapping(value = "/polls/{pollId}/votes", method = RequestMethod.GET)
    public Iterable<Vote> getVote(@PathVariable Long pollId) {
        // Use custom query to find votes by poll
        return voteRepository.findVotesByPoll(pollId);
    }
}
