package ru.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fixies.repositories.SparesRepository;

@Service
@RequiredArgsConstructor
public class SparesService {

    private final SparesRepository sparesRepository;



}
