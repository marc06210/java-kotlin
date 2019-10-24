package com.afklm.tecc.kotlin.web

import com.afklm.tecc.kotlin.data.Article
import com.afklm.tecc.kotlin.data.ArticleRepository
import com.afklm.tecc.kotlin.data.User
import com.afklm.tecc.kotlin.data.UserRepository
import com.afklm.tecc.kotlin.format
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class KotlinController(private val repository: ArticleRepository, private val userRepository: UserRepository) {
    
    @GetMapping("/insert")
    fun insert() {
        var me = userRepository.save(User("maguerrini", "Marc", "Guerrini"))
        repository.save(Article(
                title = "Reactor Bismuth is out",
                headline = "Lorem ipsum",
                content = "dolor sit amet",
                author = me
        ))
        repository.save(Article(
                title = "Reactor Aluminium has landed",
                headline = "Lorem ipsum",
                content = "dolor sit amet",
                author = me
        ))
    }
    
  @GetMapping("/")
  fun findAll() = repository.findAllByOrderByAddedAtDesc()

  @GetMapping("/{slug}")
  fun findOne(@PathVariable slug: String) =
      repository.findBySlug(slug) ?: ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")

//    @GetMapping("/")
//    fun blog(): List<RenderedArticle> {
//        return repository.findAllByOrderByAddedAtDesc().map { it.render() };
//    }
//
//  @GetMapping("/article/{slug}")
//  fun article(@PathVariable slug: String): RenderedArticle {
//    val article = repository
//        .findBySlug(slug)
//        ?.render()
//        ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")
//
//    return article
//  }

  fun Article.render() = RenderedArticle(
      slug,
      title,
      headline,
      content,
      author,
      addedAt.format()
  )

  data class RenderedArticle(
      val slug: String,
      val title: String,
      val headline: String,
      val content: String,
      val author: User,
      val addedAt: String)

}

