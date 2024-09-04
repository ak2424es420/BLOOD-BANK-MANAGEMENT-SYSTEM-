package com.example.bloodbank.repository;

import com.example.bloodbank.model.Donor;
import org.springframework.data.repository.CrudRepository;

public interface DonorRepository extends CrudRepository<Donor, Integer> {
}
