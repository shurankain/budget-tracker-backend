package com.ohusiev.budgettracker.web;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ohusiev.budgettracker.service.TagsService;

import reactor.core.publisher.Mono;

@Controller
@ResponseBody
public class TagsController {

    private final TagsService tagsService;

    public TagsController(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    @GetMapping("/tags")
    Set<String> get() {
        return this.tagsService.getAllTags();
    }

    @GetMapping("/update")
    Mono<Void> update() {
        this.tagsService.updateTagsCache();
        return Mono.empty();
    }
}
