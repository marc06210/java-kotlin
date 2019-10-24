package com.afklm.tecc.kotlin

import org.springframework.context.annotation.Configuration
import com.afklm.tecc.kotlin.data.UserRepository
import com.afklm.tecc.kotlin.data.ArticleRepository
import org.springframework.boot.ApplicationRunner
import com.afklm.tecc.kotlin.data.Article
import org.springframework.context.annotation.Bean
import com.afklm.tecc.kotlin.data.User

@Configuration
open class KotlinAppInit 

    @Bean
    fun databaseInitializer(userRepository: UserRepository,
                            articleRepository: ArticleRepository) = ApplicationRunner {

        var me = userRepository.save(User("maguerrini", "Marc", "Guerrini"))
        articleRepository.save(Article(
                title = "Reactor Bismuth is out",
                headline = "Lorem ipsum",
                content = "dolor sit amet",
                author = me
        ))
        articleRepository.save(Article(
                title = "Reactor Aluminium has landed",
                headline = "Lorem ipsum",
                content = "dolor sit amet",
                author = me
        ))
    }