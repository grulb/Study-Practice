package com.example.TownLibrary.Repository;

import com.example.TownLibrary.Model.Books;
import com.example.TownLibrary.Model.Libraries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksRepository extends JpaRepository<Books, Long> {
    List<Books> findByLibrary(Libraries libraryId);
}