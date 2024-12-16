package com.example.TownLibrary.Repository;

import com.example.TownLibrary.Model.Loans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loans, Long> {}
