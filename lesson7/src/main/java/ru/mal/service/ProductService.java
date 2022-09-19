package ru.mal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.mal.model.dto.ProductDto;
import ru.mal.model.mapper.ProductDtoMapper;
import ru.mal.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDtoMapper mapper;
    private final ProductRepository productRepository;

    public Page<ProductDto> findAllByFilter(String titleFilter, BigDecimal minCostFilter, BigDecimal maxCostFilter,int page, int size, String sortField) {
        titleFilter = titleFilter == null || titleFilter.isBlank() ? null : "%" + titleFilter.trim() + "%";
        return productRepository.productsByFilter(titleFilter, minCostFilter, maxCostFilter, PageRequest.of(page, size, Sort.by(sortField)))
                .map(mapper::map);
    }

    public Optional<ProductDto> findProductById(Long id) {
        return productRepository.findById(id).map(mapper::map);
    }

    public void save(ProductDto dto) {
        productRepository.save(mapper.map(dto));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
