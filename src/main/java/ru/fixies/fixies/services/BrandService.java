package ru.fixies.fixies.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.fixies.fixies.model.Brand;
import ru.fixies.fixies.repositories.BrandRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public Page<Brand> findAllBrandsPageable(int pageIndex, int pageSize) {
        return brandRepository.findAllBrandsPageable(PageRequest.of(pageIndex, pageSize));
    }

    public List<Brand> findAllBrandsForOrderPage() {
        return brandRepository.findAllBrandsForOrderPage();
    }



}
