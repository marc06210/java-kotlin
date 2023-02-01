package com.mgu.javakotlin.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    public Optional<Article> findBySlug(String slug);
    public List<Article> findAllByOrderByAddedAtDesc();
}
