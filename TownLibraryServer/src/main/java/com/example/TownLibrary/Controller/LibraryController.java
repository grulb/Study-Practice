package com.example.TownLibrary.Controller;

import com.example.TownLibrary.Model.Libraries;
import com.example.TownLibrary.Repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController {
    @Autowired
    private LibraryRepository libraryRepository;

    @GetMapping
    public List<Libraries> getAllLibraries() {
        return libraryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libraries> getLibraryById(@PathVariable Long id) {
        return libraryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Libraries createLibrary(@RequestBody Libraries libraries) {
        return libraryRepository.save(libraries);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libraries> updateLibrary(@PathVariable Long id, @RequestBody Libraries librariesDetails) {
        return libraryRepository.findById(id)
                .map(library -> {
                    library.setName(librariesDetails.getName());
                    library.setAddress(librariesDetails.getAddress());
                    Libraries updatedLibraries = libraryRepository.save(library);
                    return ResponseEntity.ok(updatedLibraries);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        return libraryRepository.findById(id)
                .map(book -> {
                    libraryRepository.delete(book);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}