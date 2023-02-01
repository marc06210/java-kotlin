package com.mgu.javakotlin.kotlin

import com.mgu.javakotlin.kotlin.data.Article
import com.mgu.javakotlin.kotlin.data.ArticleRepository
import com.mgu.javakotlin.kotlin.data.User
import com.mgu.javakotlin.kotlin.data.UserRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener

@SpringBootApplication
open class KotlinApp()

    fun main(args: Array<String>) {
        runApplication<KotlinApp>(*args)
    }


@Configuration
class KotlinConfig(
    private val articleRepository: ArticleRepository,
    private val userRepository: UserRepository
) {
    @EventListener
    fun initDb(e: ApplicationReadyEvent) {
        var me = userRepository.save(User("maguerrini", "Marc", "Guerrini"))
        articleRepository.save(
            Article(
                title = "Reactor Bismuth is out",
                headline = "Lorem ipsum",
                content = "dolor sit amet",
                author = me
            )
        )
        articleRepository.save(
            Article(
                title = "Reactor Aluminium has landed",
                headline = "Lorem ipsum",
                content = "dolor sit amet",
                author = me
            )
        )
    }
}