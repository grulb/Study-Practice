package com.example.TownLibrary.Repository;

import com.example.TownLibrary.Model.Libraries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Libraries, Long> {
    Optional<Libraries> findByName(String name);
    Optional<Libraries> findByAddress(String address);
}