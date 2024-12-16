package com.example.TownLibrary.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BookID")
    private Long bookID;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Author", nullable = false)
    private String author;

    @Column(name = "Count")
    private Long count;

    @ManyToOne
    @JoinColumn(name = "LibraryID")
    private Libraries library;

    @Column(name = "Rating")
    private Double rating;

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Libraries getLibrary () {
        return library;
    }

    public void setLibrary(Libraries library) {
        this.library = library;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}