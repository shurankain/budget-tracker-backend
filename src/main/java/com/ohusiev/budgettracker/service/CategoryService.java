package com.ohusiev.budgettracker.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.ohusiev.budgettracker.persistence.repository.CategoryRepository;
import com.ohusiev.budgettracker.service.utils.DateUtils;
import com.ohusiev.budgettracker.web.dto.CategoryDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CategoryService {

    private Set<String> cache;

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Set<String> getAllCategories() {
        return cache;
    }

    public Flux<CategoryDTO> getSumByCategory() {
        return this.categoryRepository.countTotalAmountByCategory();
    }

    public Flux<CategoryDTO> getTotalAmountByCategoryForPeriod(String from, String to) {
        return this.categoryRepository.getTotalAmountByCategoryForPeriod(DateUtils.formatToDate(from),
                DateUtils.formatToDate(to));
    }

    public void updateCache() {
        cache = new HashSet<>();
        this.categoryRepository.getAllCategories().collect(Collectors.toSet()).subscribe(cache::addAll);
    }
}
