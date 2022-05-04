package ru.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fixies.models.Status;
import ru.fixies.repositories.StatusRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final StatusRepository statusRepository;

    public List<Status> findAll() {
        return statusRepository.findAll();
    }


}
