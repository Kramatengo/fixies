package ru.fixies.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.fixies.fixies.model.Model;
import ru.fixies.fixies.repositories.ModelRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelService {

    public final ModelRepository modelRepository;

//    public Page<Models> findAllModels(int pageIndex, int pageSize) {
//        return modelsRepository.findAllModels(PageRequest.of(pageIndex, pageSize));
//    }

    public Page<Model> findAllModelsPageable(int pageIndex, int pageSize) {
        return modelRepository.findAllModelsPageable(PageRequest.of(pageIndex, pageSize));
    }

    public List<Model> findAllModelsForOrderPage() {
        return modelRepository.findAllModelsForOrderPage();
    }

    public List<Model> findAll(){
        return modelRepository.findAll();
    }

    public List<Model> findModelsBySelectedBrandForOrderPage(Long selectedBrandId) {
        return modelRepository.findModelsBySelectedBrandForOrderPage(selectedBrandId);
    }


    //    public List<Model> findAll() {
//        return modelRepository.findAll();
//    }

    public List<Model> findBrandsBySelectedCategory(Long selectedCategoryId) {
        return modelRepository.findBrandsBySelectedCategory(selectedCategoryId);

    }


    public List<Model> findModelsBySelectedBrand(Long selectedBrandId, Long selectedCategoryId) {
        return modelRepository.findModelsBySelectedBrand(selectedBrandId, selectedCategoryId);

    }

}
