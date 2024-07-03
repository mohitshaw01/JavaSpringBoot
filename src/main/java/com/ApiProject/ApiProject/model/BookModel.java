package com.ApiProject.ApiProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Entity
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment Primary key
    private Long id;
    @Getter
    @Setter
    private String author;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String genre;
    @Getter
    @Setter
    private LocalDate publicationDate;
    // ISBN - Indian Standard Book Number
    @Setter
    @Getter
    private String isbn;

}
