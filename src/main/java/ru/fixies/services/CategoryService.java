package ru.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fixies.dtos.CategoryDto;
import ru.fixies.mapper.ModelMapper;
import ru.fixies.models.Category;
import ru.fixies.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    @Transactional
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = repository.save(ModelMapper.INSTANCE.dtoToCategory(categoryDto));
        return ModelMapper.INSTANCE.categoryToDto(category);
    }

    @Transactional(readOnly = true)
    public CategoryDto findById(long id) {
        Optional<Category> category = repository.findById(id);
        return category.map(ModelMapper.INSTANCE::categoryToDto).orElse(null);
    }

    @Transactional(readOnly = true)
    public CategoryDto findByName(String name) {
        Optional<Category> category = repository.findByName(name);
        return category.map(ModelMapper.INSTANCE::categoryToDto).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<CategoryDto> findAll() {
        List<Category> all = repository.findAll();
        return all.stream().map(ModelMapper.INSTANCE::categoryToDto).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public CategoryDto findByModelId(Long modelId) {
        Optional<Category> brand = repository.findByModelId(modelId);
        return brand.map(ModelMapper.INSTANCE::categoryToDto).orElse(null);
    }
}
