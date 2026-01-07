package io.zipcoder.tc_spring_poll_application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception for when a resource is not found.
 * 
 * @ResponseStatus(HttpStatus.NOT_FOUND)
 * - Tells Spring to return 404 status when this exception is thrown
 * - Without a global handler, this alone handles the exception
 * 
 * USAGE:
 * if (poll == null) {
 *     throw new ResourceNotFoundException("Poll not found with id: " + pollId);
 * }
 * 
 * RESULT:
 * HTTP 404 NOT FOUND
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * Default constructor
     */
    public ResourceNotFoundException() {
        super();
    }
    
    /**
     * Constructor with custom message
     * 
     * @param message Error message describing what wasn't found
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    /**
     * Constructor with message and cause
     * 
     * @param message Error message
     * @param cause Original exception that caused this
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}