package com.afklm.tecc.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.afklm.tecc.article.data.Article;
import com.afklm.tecc.article.data.ArticleRepository;
import com.afklm.tecc.article.data.User;
import com.afklm.tecc.article.data.UserRepository;

@SpringBootApplication
public class ArticleJavaApplication {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public static void main(String[] args) {
        SpringApplication.run(ArticleJavaApplication.class, args);
    }

    
    @EventListener(ApplicationReadyEvent.class)
    public void initDb() {
        User me = userRepository.save(new User("maguerrini", "Marc", "Guerrini"));
        articleRepository.save(
              new Article(
                      "Reactor Bismuth is out",
                      "Lorem ipsum",
                      "dolor sit amet",
                      me
              )
          );
          articleRepository.save(
              new Article(
                      "Reactor Aluminium has landed",
                      "Lorem ipsum",
                      "dolor sit amet",
                      me
              )
          );
    }
}
