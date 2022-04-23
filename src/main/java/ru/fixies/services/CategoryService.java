package ru.fixies.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fixies.dtos.CategoryDto;
import ru.fixies.mapper.ModelMapper;
import ru.fixies.models.Category;
import ru.fixies.repositories.CategoryRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Resource
    private CategoryRepository repository;

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
}
