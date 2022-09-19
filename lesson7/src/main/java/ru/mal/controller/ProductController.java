package ru.mal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mal.model.dto.ProductDto;
import ru.mal.service.ProductService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;


@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;


    @GetMapping
    public String listPage(
            @RequestParam(required = false) String titleFilter,
            @RequestParam(required = false) BigDecimal minCostFilter,
            @RequestParam(required = false) BigDecimal maxCostFilter,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false) Optional<String> sortField,
            Model model
    ) {
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(4);
        String sortFiledValue = sortField.filter(s -> !s.isBlank()).orElse("id");
        model.addAttribute("products", service.findAllByFilter(titleFilter, minCostFilter, maxCostFilter, pageValue, sizeValue, sortFiledValue));
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", service.findProductById(id));
        return "product_form";
    }


    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new ProductDto());
        return "product_form";
    }

    @DeleteMapping("{id}")
    public String deleteProductById(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/product";
    }

    @PostMapping
    public String saveProduct(@Valid @ModelAttribute("product") ProductDto product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        service.save(product);
        return "redirect:/product";
    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") ProductDto product) {
        service.save(product);
        return "redirect:/product";
    }

}
