package io.zipcoder.tc_spring_poll_application.controller;

import java.util.Collection;
import io.zipcoder.tc_spring_poll_application.domain.Vote;
import io.zipcoder.tc_spring_poll_application.dtos.OptionCount;
import io.zipcoder.tc_spring_poll_application.dtos.VoteResult;
import io.zipcoder.tc_spring_poll_application.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * REST Controller for computing vote results.
 * 
 * ENDPOINT:
 * GET /computeresult?pollId=1
 * 
 * Returns vote counts and winner!
 */
@RestController
public class ComputeResultController {
    
    private VoteRepository voteRepository;
    
    @Autowired
    public ComputeResultController(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }
    
    /**
     * Compute and return vote results for a poll.
     * 
     * URL: GET /computeresult?pollId=1
     * 
     * @RequestParam - Extract pollId from query parameter
     * 
     * ALGORITHM:
     * 1. Get all votes for this poll
     * 2. Count votes per option using HashMap
     * 3. Create OptionCount objects
     * 4. Return VoteResult with counts
     * 
     * @param pollId ID of poll (from query parameter)
     * @return VoteResult with vote counts
     */
    @RequestMapping(value = "/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        
        // Create result object
        VoteResult voteResult = new VoteResult();
        
        // Get all votes for this poll
        Iterable<Vote> allVotes = voteRepository.findVotesByPoll(pollId);
        
        // Count votes per option using HashMap
        // Key: Option ID, Value: Vote count
        Map<Long, Integer> voteCounts = new HashMap<>();
        
        int totalVotes = 0;
        
        // Loop through all votes and count
        for (Vote vote : allVotes) {
            totalVotes++;
            
            // Get option ID from this vote
            Long optionId = vote.getOption().getId();
            
            // If we've seen this option before, increment count
            if (voteCounts.containsKey(optionId)) {
                int count = voteCounts.get(optionId);
                voteCounts.put(optionId, count + 1);
            } 
            // If first time seeing this option, set count to 1
            else {
                voteCounts.put(optionId, 1);
            }
        }
        
        // Set total votes
        voteResult.setTotalVotes(totalVotes);
        
        // Convert HashMap to Collection of OptionCount objects
        Collection<OptionCount> results = new java.util.ArrayList<>();
        for (Map.Entry<Long, Integer> entry : voteCounts.entrySet()) {
            OptionCount optionCount = new OptionCount();
            optionCount.setOptionId(entry.getKey());
            optionCount.setCount(entry.getValue());
            results.add(optionCount);
        }
        
        voteResult.setResults(results);
        
        // Return results with 200 OK
        return new ResponseEntity<>(voteResult, HttpStatus.OK);
    }
}