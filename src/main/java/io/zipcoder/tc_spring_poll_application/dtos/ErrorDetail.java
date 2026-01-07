package io.zipcoder.tc_spring_poll_application.dtos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DTO for error responses.
 * 
 * Provides detailed error information to clients.
 * 
 * EXAMPLE RESPONSE:
 * {
 *   "title": "Resource Not Found",
 *   "status": 404,
 *   "detail": "Poll with id 999 not found",
 *   "timeStamp": 1704650400000,
 *   "developerMessage": "Poll with id 999 does not exist in database"
 * }
 */
public class ErrorDetail {
    
    /**
     * Brief title of error
     * Example: "Validation Failure", "Resource Not Found"
     */
    private String title;
    
    /**
     * HTTP status code (redundant but useful for clients)
     * Example: 404, 400, 500
     */
    private int status;
    
    /**
     * User-friendly description of error
     * Example: "Poll not found"
     */
    private String detail;
    
    /**
     * Time when error occurred (milliseconds since epoch)
     * Example: 1704650400000
     */
    private Long timeStamp;
    
    /**
     * Technical details for developers
     * Example: "NullPointerException at line 42"
     */
    private String developerMessage;
    
    /**
     * Map of field-specific validation errors
     * Key: field name (e.g., "question", "options")
     * Value: List of validation errors for that field
     * 
     * EXAMPLE:
     * {
     *   "question": [
     *     {"code": "NotEmpty", "message": "Question is required"}
     *   ],
     *   "options": [
     *     {"code": "Size", "message": "Must have 2-6 options"}
     *   ]
     * }
     */
    private Map<String, List<ValidationError>> errors = new HashMap<>();
    
    // ==========================================
    // Constructors
    // ==========================================
    
    public ErrorDetail() {
    }
    
    public ErrorDetail(String title, int status, String detail, Long timeStamp, String developerMessage) {
        this.title = title;
        this.status = status;
        this.detail = detail;
        this.timeStamp = timeStamp;
        this.developerMessage = developerMessage;
    }
    
    // ==========================================
    // Getters and Setters
    // ==========================================
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getStatus() {
        return status;
    }
    
    public void setStatus(int status) {
        this.status = status;
    }
    
    public String getDetail() {
        return detail;
    }
    
    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    public Long getTimeStamp() {
        return timeStamp;
    }
    
    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    public String getDeveloperMessage() {
        return developerMessage;
    }
    
    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
    
    public Map<String, List<ValidationError>> getErrors() {
        return errors;
    }
    
    public void setErrors(Map<String, List<ValidationError>> errors) {
        this.errors = errors;
    }
}