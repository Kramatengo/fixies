package ru.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fixies.dtos.BrandDto;
import ru.fixies.dtos.CategoryDto;
import ru.fixies.dtos.ModelDto;
import ru.fixies.mapper.ModelMapper;
import ru.fixies.models.Brand;
import ru.fixies.models.Category;
import ru.fixies.models.Model;
import ru.fixies.repositories.BrandRepository;
import ru.fixies.repositories.CategoryRepository;
import ru.fixies.repositories.ModelRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelService {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;

    public Page<Model> findAllModels(int pageIndex, int pageSize) {
        return modelRepository.findAllModels(PageRequest.of(pageIndex, pageSize));
    }

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
    public Model findByName(String name) {
        return modelRepository.findByName(name).orElse(null);
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
    public List<ModelDto> findByBrandIdAndCategoryId(Long brandId, Long categoryId) {
        List<Model> models = modelRepository.findByBrandIdAndCategoryId(brandId, categoryId).orElse(new ArrayList<>());

        return models.stream().map(ModelMapper.INSTANCE::modelToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ModelDto> findByCategoryId(Long categoryId) {
        List<Model> models = modelRepository.findByCategoryId(categoryId).orElse(new ArrayList<>());
        return models.stream().map(ModelMapper.INSTANCE::modelToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ModelDto> findByBrandId(Long brandId) {
        List<Model> models = modelRepository.findByBrandId(brandId).orElse(new ArrayList<>());
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

    @Transactional(readOnly = true)
    public List<ModelDto> findByNameLike(String partName) {
        List<Model> models = modelRepository.findByPartName(partName).orElse(new ArrayList<>());
        return models.stream().map(ModelMapper.INSTANCE::modelToDto).collect(Collectors.toList());
    }
}
