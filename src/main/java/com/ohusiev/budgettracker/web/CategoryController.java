package com.ohusiev.budgettracker.web;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohusiev.budgettracker.service.CategoryService;
import com.ohusiev.budgettracker.web.dto.CategoryDTO;

import reactor.core.publisher.Flux;

@Controller
@ResponseBody
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    Set<String> getCategories() {
        return this.categoryService.getAllCategories();
    }

    @GetMapping("/categories/sum")
    Flux<CategoryDTO> getSumByCategory() {
        return this.categoryService.getSumByCategory();
    }

    @PostMapping("/categories/between")
    Flux<CategoryDTO> getDataPerCategoryLimitedByDates(@RequestParam String from, @RequestParam String to) {
        return this.categoryService.getTotalAmountByCategoryForPeriod(from, to);
    }
}
