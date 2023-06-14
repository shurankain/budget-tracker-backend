package com.ohusiev.budgettracker.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ohusiev.budgettracker.persistence.repository.TagsRepository;

@Service
public class TagsService {

    private Set<String> tagsCache;

    private final TagsRepository tagsRepository;

    public TagsService(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
        updateTagsCache(); //on bean creation
    }

    public void updateTagsCache() {
        tagsCache = new HashSet<>();
        this.tagsRepository.getAllTags().collect(Collectors.toSet()).subscribe(tagsCache::addAll);
    }

    public Set<String> getAllTags() {
        return tagsCache;
    }
}
