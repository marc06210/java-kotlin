package com.afklm.tecc.article.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.afklm.tecc.article.Extensions;
import com.afklm.tecc.article.data.Article;
import com.afklm.tecc.article.data.ArticleRepository;
import com.afklm.tecc.article.data.User;

@RestController
public class JavaController {
    
    private ArticleRepository articleRepository;

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

    class RenderedArticle {
        private String slug;
        private String title;
        private String headline;
        private String content;
        private User author;
        private String addedAt;
        
        public RenderedArticle(String slug, String title, String headline, String content, User author,
                String addedAt) {
            super();
            this.slug = slug;
            this.title = title;
            this.headline = headline;
            this.content = content;
            this.author = author;
            this.addedAt = addedAt;
        }

        public String getSlug() {
            return slug;
        }
        public void setSlug(String slug) {
            this.slug = slug;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getHeadline() {
            return headline;
        }
        public void setHeadline(String headline) {
            this.headline = headline;
        }
        public String getContent() {
            return content;
        }
        public void setContent(String content) {
            this.content = content;
        }
        public User getAuthor() {
            return author;
        }
        public void setAuthor(User author) {
            this.author = author;
        }
        public String getAddedAt() {
            return addedAt;
        }
        public void setAddedAt(String addedAt) {
            this.addedAt = addedAt;
        }
    }

}
