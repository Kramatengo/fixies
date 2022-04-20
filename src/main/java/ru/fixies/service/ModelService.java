package ru.fixies.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fixies.dto.BrandDto;
import ru.fixies.dto.CategoryDto;
import ru.fixies.dto.ModelDto;
import ru.fixies.entity.Brand;
import ru.fixies.entity.Category;
import ru.fixies.entity.Model;
import ru.fixies.mapper.ModelMapper;
import ru.fixies.repository.BrandRepository;
import ru.fixies.repository.CategoryRepository;
import ru.fixies.repository.ModelRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModelService {

    @Resource
    private ModelRepository modelRepository;

    @Resource
    private BrandRepository brandRepository;

    @Resource
    private CategoryRepository categoryRepository;

    @Transactional
    public ModelDto save(ModelDto modelDto) {
        Optional<Model> modelExist = modelRepository.findByName(modelDto.getName());
        if (modelExist.isPresent()) {
            return ModelMapper.INSTANCE.modelToDto(modelRepository.save(modelExist.get()));
        }

        Brand brand = brandRepository.findByName(modelDto.getBrand().getName()).orElse(new Brand());
        Category category = categoryRepository.findByName(modelDto.getCategory().getName()).orElse(new Category());

        Model model = ModelMapper.INSTANCE.dtoToModel(modelDto);
        model.setBrand(brand);
        model.setCategory(category);

        return ModelMapper.INSTANCE.modelToDto(modelRepository.save(model));
    }

    @Transactional(readOnly = true)
    public ModelDto findById(long id) {
        Optional<Model> model = modelRepository.findById(id);
        return model.map(ModelMapper.INSTANCE::modelToDto).orElse(null);
    }

    @Transactional(readOnly = true)
    public ModelDto findByName(String name) {
        Optional<Model> model = modelRepository.findByName(name);
        return model.map(ModelMapper.INSTANCE::modelToDto).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<ModelDto> findByBrand(BrandDto brandDto) {
        Brand brand = brandRepository.findByName(brandDto.getName()).orElse(new Brand());
        List<Model> models = modelRepository.findByBrand(brand).orElse(new ArrayList<>());

        return models.stream().map(ModelMapper.INSTANCE::modelToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ModelDto> findByCategory(CategoryDto categoryDto) {
        Category category = categoryRepository.findByName(categoryDto.getName()).orElse(new Category());
        List<Model> models = modelRepository.findByCategory(category).orElse(new ArrayList<>());

        return models.stream().map(ModelMapper.INSTANCE::modelToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ModelDto> findByBrandAndCategory(BrandDto brandDto, CategoryDto categoryDto) {
        Brand brand = brandRepository.findByName(brandDto.getName()).orElse(new Brand());
        Category category = categoryRepository.findByName(categoryDto.getName()).orElse(new Category());
        List<Model> models = modelRepository.findByBrandAndCategory(brand, category).orElse(new ArrayList<>());

        return models.stream().map(ModelMapper.INSTANCE::modelToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ModelDto> findAll() {
        List<Model> all = modelRepository.findAll();
        return all.stream().map(ModelMapper.INSTANCE::modelToDto).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        modelRepository.deleteById(id);
    }
}
