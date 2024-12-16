package com.example.TownLibrary.Controller;

import com.example.TownLibrary.Model.Libraries;
import com.example.TownLibrary.Model.Users;
import com.example.TownLibrary.Repository.LibraryRepository;
import com.example.TownLibrary.Repository.UsersRepository;
import com.example.TownLibrary.Requests.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    @GetMapping
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
        return usersRepository.findById(id).map(user -> ResponseEntity.ok().body(user)).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Users createUser(@RequestBody Users user) {
        return usersRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return usersRepository.findById(id)
                .map(user -> {
                    usersRepository.delete(user);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRequest> login(@RequestBody Users loginRequest) {
        Users user = usersRepository.findByEmail(loginRequest.getEmail());

        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok(new LoginRequest(200, user.getRole(), user.getReaderID()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginRequest(401, null, null));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Integer> register(@RequestBody Users registerRequest) {
        Users existingUser  = usersRepository.findByEmail(registerRequest.getEmail());
        if (existingUser  != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(409);
        }

        Optional<Libraries> optionalLibrary = libraryRepository.findByName(registerRequest.getLibrary().getName());
        if (!optionalLibrary.isPresent()) {
            optionalLibrary = libraryRepository.findByAddress(registerRequest.getLibrary().getAddress());
        }

        if (!optionalLibrary.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(400);
        }

        Libraries libraries = optionalLibrary.get();

        Users newUser  = new Users();
        newUser.setName(registerRequest.getName());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(registerRequest.getPassword());
        newUser.setRegistrationDate(registerRequest.getRegistrationDate());
        newUser.setStatus(registerRequest.getStatus());
        newUser.setRole(registerRequest.getRole());
        newUser.setLibrary(libraries);
        usersRepository.save(newUser);
        return ResponseEntity.ok(201);
    }
}