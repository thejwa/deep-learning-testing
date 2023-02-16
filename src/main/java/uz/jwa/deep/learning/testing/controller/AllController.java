package uz.jwa.deep.learning.testing.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.jwa.deep.learning.testing.entity.Author;
import uz.jwa.deep.learning.testing.entity.Book;
import uz.jwa.deep.learning.testing.service.AllService;

/**
 * @author TheJWA (Jonibek)
 * @since 2/16/2023 Thursday
 */

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AllController {
    private final AllService allService;

    @PostMapping("/book")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return allService.createBook(book);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return allService.getBookById(id);
    }

    @GetMapping("/book")
    public ResponseEntity<Page<Book>> getAllBooks(Pageable pageable) {
        return allService.getAllBooks(pageable);
    }

    @PostMapping("/author")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        return allService.createAuthor(author);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        return allService.getAuthorById(id);
    }

    @GetMapping("/author")
    public ResponseEntity<Page<Author>> getAllAuthors(Pageable pageable) {
        return allService.getAllAuthors(pageable);
    }

}
