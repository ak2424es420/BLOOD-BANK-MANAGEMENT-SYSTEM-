package com.example.bloodbank.controller;

import com.example.bloodbank.model.Donor;
import com.example.bloodbank.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/donors")
public class BloodBankController {

    @Autowired
    private DonorService donorService;

    @GetMapping
    public Iterable<Donor> getAllDonors() {
        return donorService.getAllDonors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donor> getDonorById(@PathVariable Integer id) {
        Optional<Donor> donor = donorService.getDonorById(id);
        return donor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Donor> createDonor(@RequestBody Donor donor) {
        Donor savedDonor = donorService.saveDonor(donor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDonor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Donor> updateDonor(@PathVariable Integer id, @RequestBody Donor donor) {
        if (!donorService.getDonorById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        donor.setId(id);
        Donor updatedDonor = donorService.saveDonor(donor);
        return ResponseEntity.ok(updatedDonor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonor(@PathVariable Integer id) {
        if (!donorService.getDonorById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        donorService.deleteDonor(id);
        return ResponseEntity.noContent().build();
    }
}
