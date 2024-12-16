package com.example.TownLibrary.Controller;

import com.example.TownLibrary.Model.Books;
import com.example.TownLibrary.Model.Libraries;
import com.example.TownLibrary.Repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {
    @Autowired
    private BooksRepository booksRepository;

    @GetMapping
    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable Long id) {
        return booksRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Books createBook(@RequestBody Books book) {
        return booksRepository.save(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Books> updateBook(@PathVariable Long id, @RequestBody Books bookDetails) {
        return booksRepository.findById(id)
                .map(book -> {
                    book.setTitle(bookDetails.getTitle());
                    book.setAuthor(bookDetails.getAuthor());
                    book.setCount(bookDetails.getCount());
                    Books updatedBook = booksRepository.save(book);
                    return ResponseEntity.ok(updatedBook);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        return booksRepository.findById(id)
                .map(book -> {
                    booksRepository.delete(book);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/library/{libraryId}")
    public ResponseEntity<List<Books>> getBooksByLibraryID(@PathVariable Libraries libraryId) {
        List<Books> books = booksRepository.findByLibrary(libraryId);
        return ResponseEntity.ok(books);
    }
}