package ru.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fixies.models.Status;
import ru.fixies.models.Warranty;
import ru.fixies.repositories.WarrantyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarrantyService {

    private final WarrantyRepository warrantyRepository;

    public List<Warranty> findAll() {
        return warrantyRepository.findAll();
    }


    public Warranty findByName(String name) {
        return warrantyRepository.findByName(name);
    }


}
