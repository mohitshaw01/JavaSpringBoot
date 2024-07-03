package com.ApiProject.ApiProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ApiProject.ApiProject.model.BookModel;

@Repository
public interface BookRepository extends JpaRepository<BookModel,Long> {
    // This file provides the crud operations for the BookModel entity
    // without writing boilerPlate code
    // Repositiory is used to store and retrieve data
    // <Model and tyep of Primary key>
}
