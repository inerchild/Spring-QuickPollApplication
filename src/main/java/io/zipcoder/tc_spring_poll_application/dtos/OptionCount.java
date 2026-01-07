package io.zipcoder.tc_spring_poll_application.dtos;

/**
 * DTO (Data Transfer Object) for holding vote count per option.
 * 
 * NOT AN ENTITY - Just for transferring data to client
 * 
 * EXAMPLE:
 * {
 *   "optionId": 2,
 *   "count": 5
 * }
 * 
 * Meaning: Option #2 (Python) has 5 votes
 */
public class OptionCount {
    
    /**
     * ID of the option that was voted for
     */
    private Long optionId;
    
    /**
     * Number of votes this option received
     */
    private int count;
    
    // ==========================================
    // Constructors
    // ==========================================
    
    /**
     * Default constructor
     */
    public OptionCount() {
    }
    
    /**
     * Constructor with parameters
     * 
     * @param optionId ID of the option
     * @param count Number of votes
     */
    public OptionCount(Long optionId, int count) {
        this.optionId = optionId;
        this.count = count;
    }
    
    // ==========================================
    // Getters and Setters
    // ==========================================
    
    public Long getOptionId() {
        return optionId;
    }
    
    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    @Override
    public String toString() {
        return "OptionCount{optionId=" + optionId + ", count=" + count + "}";
    }
}