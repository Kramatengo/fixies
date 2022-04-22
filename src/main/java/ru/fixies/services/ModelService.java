package ru.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.fixies.model.Model;
import ru.fixies.repositories.ModelRepository;

@Service
@RequiredArgsConstructor
public class ModelService {

    public final ModelRepository modelRepository;

    public Page<Model> findAllModels(int pageIndex, int pageSize) {
        return modelRepository.findAllModels(PageRequest.of(pageIndex, pageSize));
    }

}
