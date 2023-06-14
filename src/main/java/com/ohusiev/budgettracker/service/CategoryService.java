package com.ohusiev.budgettracker.service;

import com.ohusiev.budgettracker.persistence.repository.CategoryRepository;
import com.ohusiev.budgettracker.service.utils.DateUtils;
import com.ohusiev.budgettracker.web.dto.CategoryDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Flux<CategoryDTO> getAllCategories() {
        return this.categoryRepository.countTotalAmountByCategory();
    }

    public Flux<CategoryDTO> getTotalAmountByCategoryForPeriod(String from, String to) {
        return this.categoryRepository.getTotalAmountByCategoryForPeriod(DateUtils.formatToDate(from),
                DateUtils.formatToDate(to));
    }
}
