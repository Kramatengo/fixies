package ru.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fixies.repositories.SpareRepository;

@Service
@RequiredArgsConstructor
public class SpareService {

    private final SpareRepository spareRepository;



}
