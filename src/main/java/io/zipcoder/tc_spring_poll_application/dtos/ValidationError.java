package io.zipcoder.tc_spring_poll_application.dtos;

/**
 * DTO for individual validation errors.
 * 
 * Used inside ErrorDetail's errors map.
 * 
 * EXAMPLE:
 * {
 *   "code": "NotEmpty",
 *   "message": "Question is a required field"
 * }
 */
public class ValidationError {
    
    /**
     * Validation constraint code
     * Examples: "NotEmpty", "Size", "Min", "Max"
     */
    private String code;
    
    /**
     * Human-readable error message
     * Example: "Question is a required field"
     */
    private String message;
    
    // ==========================================
    // Constructors
    // ==========================================
    
    public ValidationError() {
    }
    
    public ValidationError(String code, String message) {
        this.code = code;
        this.message = message;
    }
    
    // ==========================================
    // Getters and Setters
    // ==========================================
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
