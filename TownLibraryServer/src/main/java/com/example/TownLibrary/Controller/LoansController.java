package com.example.TownLibrary.Controller;

import com.example.TownLibrary.Model.Loans;
import com.example.TownLibrary.Repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoansController {
    @Autowired
    private LoanRepository loanRepository;

    @GetMapping
    public List<Loans> getAllLoans() {
        return loanRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loans> getLoanById(@PathVariable Long id) {
        return loanRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Loans createLibrary(@RequestBody Loans loans) {
        return loanRepository.save(loans);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loans> updateLoan(@PathVariable Long id, @RequestBody Loans loanDetails) {
        return loanRepository.findById(id)
                .map(loan -> {
                    loan.setUsers(loanDetails.getUsers());
                    loan.setBooks(loanDetails.getBooks());
                    loan.setLoanDate(loanDetails.getLoanDate());
                    loan.setReturnDate(loanDetails.getReturnDate());
                    Loans updatedLoans = loanRepository.save(loan);
                    return ResponseEntity.ok(updatedLoans);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        return loanRepository.findById(id)
                .map(loan -> {
                    loanRepository.delete(loan);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}