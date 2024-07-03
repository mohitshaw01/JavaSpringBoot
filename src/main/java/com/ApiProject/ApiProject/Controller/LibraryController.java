package com.ApiProject.ApiProject.Controller;

import com.ApiProject.ApiProject.Repository.LibraryRepository;
import com.ApiProject.ApiProject.Service.UserService;
import com.ApiProject.ApiProject.model.LibraryModel;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/library")
public class LibraryController {
    @Autowired
    private LibraryRepository libraryRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @GetMapping
    public List<LibraryModel> getLibrary() {
        try{
            logger.info("Fetching Library");
            return libraryRepository.findAll();
        }
        catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    @GetMapping("/{id}")
    public LibraryModel getLibraryById(@PathVariable Long id) {
        return libraryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No library with id " + id + " found"));
    }
    @Transactional
    @PostMapping("/save")
    public LibraryModel saveLibrary(@RequestBody LibraryModel library) {
        try {
            logger.info("Saving");
            return libraryRepository.save(library);
        } catch (Exception e) {
            logger.info(e.getMessage());
            throw new RuntimeException("Failed to save library: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteLibrary(@PathVariable Long id) {
        libraryRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public LibraryModel updateLibrary(@PathVariable Long id, @RequestBody LibraryModel updatedLibrary) {
        return libraryRepository.findById(id)
                .map(library -> {
                    library.setName(updatedLibrary.getName());
                    library.setLocation(updatedLibrary.getLocation());
                    library.setAnnualBudget(updatedLibrary.getAnnualBudget());
                    return libraryRepository.save(library);
                })
                .orElseThrow(() -> new RuntimeException("No library with id " + id + " found"));
    }
}
