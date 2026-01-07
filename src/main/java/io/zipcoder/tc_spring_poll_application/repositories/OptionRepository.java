package io.zipcoder.tc_spring_poll_application.repositories;

import io.zipcoder.tc_spring_poll_application.domain.Option;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for Option entity.
 * 
 * WHAT DOES THIS DO?
 * By extending CrudRepository, Spring automatically provides:
 * - save(option) - Create or update
 * - findById(id) - Find by ID
 * - findAll() - Get all options
 * - deleteById(id) - Delete by ID
 * - count() - Count all
 * - existsById(id) - Check exists
 * 
 * NO SQL NEEDED - Spring generates it automatically!
 * 
 * CrudRepository<Option, Long>:
 * - Option = Entity type
 * - Long = Primary key type (id field type)
 */
public interface OptionRepository extends CrudRepository<Option, Long> {
    
    // That's it! Spring Data JPA auto-implements all methods.
    
    // Could add custom methods if needed:
    // List<Option> findByValue(String value);
    // List<Option> findByValueContaining(String keyword);
}
