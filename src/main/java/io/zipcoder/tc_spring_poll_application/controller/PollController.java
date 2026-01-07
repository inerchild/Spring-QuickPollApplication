package io.zipcoder.tc_spring_poll_application.controller;

import io.zipcoder.tc_spring_poll_application.domain.Poll;
import io.zipcoder.tc_spring_poll_application.exception.ResourceNotFoundException;
import io.zipcoder.tc_spring_poll_application.repositories.PollRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * REST Controller for Poll operations with exception handling.
 */
@RestController
public class PollController {
    
    private PollRepository pollRepository;
    
    @Autowired
    public PollController(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }
    
    /**
     * Verify poll exists, throw exception if not.
     * 
     * @param pollId ID to check
     * @throws ResourceNotFoundException if poll doesn't exist
     */
    private void verifyPoll(Long pollId) throws ResourceNotFoundException {
        Poll poll = pollRepository.findById(pollId).orElse(null);
        if (poll == null) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }
    }
    
    /**
     * GET /polls - Get all polls
     */
    /**
 * GET /polls - Get all polls with optional pagination
 * 
 * WITHOUT PAGINATION:
 * GET /polls
 * Returns all polls
 * 
 * WITH PAGINATION:
 * GET /polls?page=0&size=5
 * Returns page 0 with 5 polls per page
 * 
 * @param pageable Pagination parameters (optional)
 * @return Page of polls or all polls
 */
@RequestMapping(value = "/polls", method = RequestMethod.GET)
public ResponseEntity<Iterable<Poll>> getAllPolls(
        @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer size) {
    
    // If pagination parameters provided
    if (page != null && size != null) {
        // Create Pageable object
        org.springframework.data.domain.Pageable pageable = 
            org.springframework.data.domain.PageRequest.of(page, size);
        
        // Get paginated results
        org.springframework.data.domain.Page<Poll> pollsPage = 
            pollRepository.findAll(pageable);
        
        // Return page content
        return new ResponseEntity<>(pollsPage.getContent(), HttpStatus.OK);
    }
    
    // No pagination - return all polls
    Iterable<Poll> allPolls = pollRepository.findAll();
    return new ResponseEntity<>(allPolls, HttpStatus.OK);
}
    
    /**
     * POST /polls - Create new poll
     * @Valid ensures validation rules are checked
     */
    @RequestMapping(value = "/polls", method = RequestMethod.POST)
    public ResponseEntity<?> createPoll(@Valid @RequestBody Poll poll) {
        poll = pollRepository.save(poll);
        
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(newPollUri);
        
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
    
    /**
     * GET /polls/{pollId} - Get specific poll
     * Throws 404 if poll not found
     */
    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        Poll poll = pollRepository.findById(pollId).orElse(null);
        return new ResponseEntity<>(poll, HttpStatus.OK);
    }
    
    /**
     * PUT /polls/{pollId} - Update poll
     * Throws 404 if poll not found
     * @Valid ensures validation rules are checked
     */
    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@Valid @RequestBody Poll poll, @PathVariable Long pollId) {
        verifyPoll(pollId);
        pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    /**
     * DELETE /polls/{pollId} - Delete poll
     * Throws 404 if poll not found
     */
    @RequestMapping(value = "/polls/{pollId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        pollRepository.deleteById(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}