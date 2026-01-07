package io.zipcoder.tc_spring_poll_application.exception;

import io.zipcoder.tc_spring_poll_application.dtos.ErrorDetail;
import io.zipcoder.tc_spring_poll_application.dtos.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Global exception handler for all controllers.
 * 
 * @ControllerAdvice - AOP feature that wraps all controllers
 * Intercepts exceptions and provides custom responses
 * 
 * HANDLES:
 * 1. ResourceNotFoundException (404)
 * 2. MethodArgumentNotValidException (400 - validation errors)
 */
@ControllerAdvice
public class RestExceptionHandeler {
    
    /**
     * MessageSource for externalized error messages
     * Autowired by Spring (will use messages.properties if exists)
     */
    @Autowired(required = false)
    private MessageSource messageSource;
    
    /**
     * Handle ResourceNotFoundException (404).
     * 
     * Triggered when:
     * - Poll not found
     * - Vote not found
     * - Any resource with invalid ID
     * 
     * @param rnfe The exception thrown
     * @param request HTTP request that caused error
     * @return ErrorDetail with 404 status
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(
            ResourceNotFoundException rnfe, 
            HttpServletRequest request) {
        
        // Create error detail
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Resource Not Found");
        errorDetail.setDetail(rnfe.getMessage());
        errorDetail.setDeveloperMessage(rnfe.getClass().getName());
        
        // Return 404 NOT FOUND
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }
    
    /**
     * Handle validation errors (400).
     * 
     * Triggered when:
     * - @Valid annotation finds validation errors
     * - @NotEmpty fails (empty question)
     * - @Size fails (wrong number of options)
     * 
     * @param manve Validation exception
     * @param request HTTP request
     * @return ErrorDetail with validation errors and 400 status
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError(
            MethodArgumentNotValidException manve,
            HttpServletRequest request) {
        
        // Create error detail
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setTitle("Validation Failed");
        errorDetail.setDetail("Input validation failed");
        errorDetail.setDeveloperMessage(manve.getClass().getName());
        
        // Get all field errors
        List<FieldError> fieldErrors = manve.getBindingResult().getFieldErrors();
        
        // Process each field error
        for (FieldError fe : fieldErrors) {
            // Get or create validation error list for this field
            List<ValidationError> validationErrorList = 
                errorDetail.getErrors().get(fe.getField());
            
            if (validationErrorList == null) {
                validationErrorList = new ArrayList<>();
                errorDetail.getErrors().put(fe.getField(), validationErrorList);
            }
            
            // Create validation error
            ValidationError validationError = new ValidationError();
            validationError.setCode(fe.getCode());
            
            // Get message (use MessageSource if available, otherwise default)
            if (messageSource != null) {
                validationError.setMessage(messageSource.getMessage(fe, null));
            } else {
                validationError.setMessage(fe.getDefaultMessage());
            }
            
            validationErrorList.add(validationError);
        }
        
        // Return 400 BAD REQUEST
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }
}