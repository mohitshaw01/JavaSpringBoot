package com.ApiProject.ApiProject.Repository;

import com.ApiProject.ApiProject.model.LibraryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<LibraryModel,Long> {
}
