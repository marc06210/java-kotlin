package com.afklm.tecc.kotlin

import com.afklm.tecc.kotlin.data.Article
import com.afklm.tecc.kotlin.data.ArticleRepository
import com.afklm.tecc.kotlin.data.User
import com.afklm.tecc.kotlin.data.UserRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener

@SpringBootApplication
open class KotlinApp(
    private val articleRepository: ArticleRepository,
    private val userRepository: UserRepository
) {

    fun main(args: Array<String>) {
        runApplication<KotlinApp>(*args)
    }

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