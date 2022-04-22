package ru.fixies.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fixies.dto.BrandDto;
import ru.fixies.dto.CategoryDto;
import ru.fixies.entity.Brand;
import ru.fixies.entity.Category;
import ru.fixies.mapper.ModelMapper;
import ru.fixies.repository.BrandRepository;
import ru.fixies.repository.CategoryRepository;

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
