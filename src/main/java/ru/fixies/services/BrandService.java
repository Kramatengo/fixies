package ru.fixies.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fixies.dtos.BrandDto;
import ru.fixies.mapper.ModelMapper;
import ru.fixies.model.Brand;
import ru.fixies.repositories.BrandRepository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandService {

    @Resource
    private BrandRepository repository;

    @Transactional
    public BrandDto save(BrandDto brandDto) {
        Brand brand = repository.save(ModelMapper.INSTANCE.dtoToBrand(brandDto));
        return ModelMapper.INSTANCE.brandToDto(brand);
    }

    @Transactional(readOnly = true)
    public BrandDto findById(long id) {
        Optional<Brand> brand = repository.findById(id);
        return brand.map(ModelMapper.INSTANCE::brandToDto).orElse(null);
    }

    @Transactional(readOnly = true)
    public BrandDto findByName(String name) {
        Optional<Brand> brand = repository.findByName(name);
        return brand.map(ModelMapper.INSTANCE::brandToDto).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<BrandDto> findAll() {
        List<Brand> all = repository.findAll();
        return all.stream().map(ModelMapper.INSTANCE::brandToDto).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
