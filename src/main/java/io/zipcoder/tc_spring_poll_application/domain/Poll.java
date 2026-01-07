package io.zipcoder.tc_spring_poll_application.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.Set;

/**
 * Poll entity - represents a question with multiple options.
 * 
 * RELATIONSHIPS:
 * One Poll → Many Options
 * When Poll deleted → Options auto-deleted (CascadeType.ALL)
 * 
 * VALIDATION:
 * - Question cannot be empty
 * - Must have 2-6 options
 */
@Entity
public class Poll {
    
    /**
     * Primary key - auto-generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POLL_ID")
    private Long id;
    
    /**
     * The poll question
     * 
     * @NotEmpty - Cannot be null or empty
     */
    @Column(name = "QUESTION")
    @NotEmpty(message = "Question is a required field")
    private String question;
    
    /**
     * Options for this poll
     * 
     * @OneToMany - One poll has many options
     * cascade = CascadeType.ALL - Save/delete options with poll
     * @JoinColumn - Foreign key in Option table
     * @OrderBy - Sort options by ID
     * @Size - Must have 2-6 options
     */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "POLL_ID")
    @OrderBy
    @Size(min = 2, max = 6, message = "Options must be between {min} and {max}")
    private Set<Option> options;
    
    // ==========================================
    // Constructors
    // ==========================================
    
    /**
     * Default constructor - REQUIRED by JPA
     */
    public Poll() {
    }
    
    // ==========================================
    // Getters and Setters
    // ==========================================
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getQuestion() {
        return question;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }
    
    public Set<Option> getOptions() {
        return options;
    }
    
    public void setOptions(Set<Option> options) {
        this.options = options;
    }
    
    @Override
    public String toString() {
        return "Poll{id=" + id + ", question='" + question + "', options=" + 
               (options != null ? options.size() : 0) + "}";
    }
}