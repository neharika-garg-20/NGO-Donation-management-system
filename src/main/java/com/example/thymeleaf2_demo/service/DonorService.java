package com.example.thymeleaf2_demo.service;

import com.example.thymeleaf2_demo.model.Donor;
import com.example.thymeleaf2_demo.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonorService {

    @Autowired
    private DonorRepository donorRepository;

    public void registerDonor(Donor donor) {
        donorRepository.save(donor);
    }

    public Donor getDonorByEmail(String email) {
        return donorRepository.findByEmail(email);
    }
}
