package com.example.TownLibrary.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "loans")
public class Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LoanID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ReaderID")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "BookID")
    private Books books;

    @Column(name = "LoanDate")
    private String loanDate;

    @Column(name = "ReturnDate")
    private String returnDate;

    public Long getId() {
        return id;
    }

    public void setId(Long loanId) {
        this.id = loanId;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Books getBooks() {
        return books;
    }

    public void setBooks(Books books) {
        this.books = books;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}