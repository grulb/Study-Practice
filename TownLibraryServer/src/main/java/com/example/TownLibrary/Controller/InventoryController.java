package com.example.TownLibrary.Controller;

import com.example.TownLibrary.Model.Inventory;
import com.example.TownLibrary.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
        return inventoryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Inventory createLibrary(@RequestBody Inventory Inventory) {
        return inventoryRepository.save(Inventory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody Inventory InventoryDetails) {
        return inventoryRepository.findById(id)
                .map(inventory -> {
                    inventory.setBooks(InventoryDetails.getBooks());
                    inventory.setInventoryNumber(InventoryDetails.getInventoryNumber());
                    inventory.setShelf(InventoryDetails.getShelf());
                    Inventory updatedInventory = inventoryRepository.save(inventory);
                    return ResponseEntity.ok(updatedInventory);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        return inventoryRepository.findById(id)
                .map(Inventory -> {
                    inventoryRepository.delete(Inventory);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
