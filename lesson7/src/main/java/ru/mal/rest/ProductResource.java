package ru.mal.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mal.exceptions.EntityNotFoundException;
import ru.mal.model.dto.ProductDto;
import ru.mal.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor

public class ProductResource {
    private final ProductService service;

    @GetMapping
    public List<ProductDto> listPage(
            @RequestParam(required = false) String titleFilter,
            @RequestParam(required = false) BigDecimal minCostFilter,
            @RequestParam(required = false) BigDecimal maxCostFilter,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false) Optional<String> sortField,
            Model model
    ) {
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(3);
        String sortFieldValue = sortField.filter(s -> !s.isBlank()).orElse("id");
        Page<ProductDto> allByFilter = service.findAllByFilter(titleFilter, minCostFilter, maxCostFilter, pageValue, sizeValue, sortFieldValue);
        List<ProductDto> products = allByFilter.get().collect(Collectors.toList());
        return products;
    }

    @GetMapping("/{id}")
    public ProductDto form(@PathVariable("id") long id, Model model) {
        ProductDto productDto = service.findProductById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return productDto;
    }

    @PostMapping
    public ProductDto saveProduct(@RequestBody ProductDto product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("Created product shouldn't have id");
        }
        service.save(product);
        return product;
    }
}
