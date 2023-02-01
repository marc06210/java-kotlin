package com.mgu.javakotlin.web;

import java.util.List;
import java.util.stream.Collectors;

import com.mgu.javakotlin.Extensions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mgu.javakotlin.data.Article;
import com.mgu.javakotlin.data.ArticleRepository;
import com.mgu.javakotlin.data.User;

@RestController
public class JavaController {
    
    final private ArticleRepository articleRepository;

    @Autowired
    public JavaController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/")
    public List<RenderedArticle> findAll() {
        return articleRepository.findAllByOrderByAddedAtDesc()
            .stream()
            .map(this::render)
            .collect(Collectors.toList());
    }

    @GetMapping("/{slug}")
    public Object findOne(@PathVariable String slug) {
        return articleRepository.findBySlug(slug)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist"));
    }

    private RenderedArticle render(Article a) {
        return new RenderedArticle(
                a.getSlug(), 
                a.getTitle(),
                a.getHeadline(),
                a.getContent(),
                a.getAuthor(),
                Extensions.format(a.getAddedAt()));
    }

    record RenderedArticle(String slug, String title, String headline, String content, User author, String addedAt) {}

}
