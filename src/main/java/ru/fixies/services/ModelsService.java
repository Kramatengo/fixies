package ru.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.fixies.model.Models;
import ru.fixies.repositories.ModelsRepository;

@Service
@RequiredArgsConstructor
public class ModelsService {

    public final ModelsRepository modelsRepository;

    public Page<Models> findAllModels(int pageIndex, int pageSize) {
        return modelsRepository.findAllModels(PageRequest.of(pageIndex, pageSize));
    }

}
