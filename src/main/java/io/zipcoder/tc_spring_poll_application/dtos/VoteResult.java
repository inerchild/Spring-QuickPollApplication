package io.zipcoder.tc_spring_poll_application.dtos;

import java.util.Collection;

/**
 * DTO for holding complete voting results.
 * 
 * EXAMPLE RESPONSE:
 * {
 *   "totalVotes": 4,
 *   "results": [
 *     {"optionId": 2, "count": 2},
 *     {"optionId": 3, "count": 1},
 *     {"optionId": 1, "count": 1}
 *   ]
 * }
 * 
 * Shows:
 * - Total votes cast: 4
 * - Python (id=2): 2 votes
 * - Java (id=3): 1 vote
 * - JavaScript (id=1): 1 vote
 */
public class VoteResult {
    
    /**
     * Total number of votes cast for this poll
     */
    private int totalVotes;
    
    /**
     * Collection of vote counts per option
     */
    private Collection<OptionCount> results;
    
    // ==========================================
    // Constructors
    // ==========================================
    
    /**
     * Default constructor
     */
    public VoteResult() {
    }
    
    /**
     * Constructor with parameters
     * 
     * @param totalVotes Total votes
     * @param results Vote counts per option
     */
    public VoteResult(int totalVotes, Collection<OptionCount> results) {
        this.totalVotes = totalVotes;
        this.results = results;
    }
    
    // ==========================================
    // Getters and Setters
    // ==========================================
    
    public int getTotalVotes() {
        return totalVotes;
    }
    
    public void setTotalVotes(int totalVotes) {
        this.totalVotes = totalVotes;
    }
    
    public Collection<OptionCount> getResults() {
        return results;
    }
    
    public void setResults(Collection<OptionCount> results) {
        this.results = results;
    }
    
    @Override
    public String toString() {
        return "VoteResult{totalVotes=" + totalVotes + ", results=" + results + "}";
    }
}