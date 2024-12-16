package com.example.TownLibrary.Repository;

import com.example.TownLibrary.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {}
