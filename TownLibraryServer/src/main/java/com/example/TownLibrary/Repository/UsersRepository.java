package com.example.TownLibrary.Repository;

import com.example.TownLibrary.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByReaderID(Long readerID);
    Users findByEmail(String email);
}