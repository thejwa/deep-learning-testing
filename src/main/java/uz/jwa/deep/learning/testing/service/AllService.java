package uz.jwa.deep.learning.testing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.jwa.deep.learning.testing.entity.Author;
import uz.jwa.deep.learning.testing.entity.Book;
import uz.jwa.deep.learning.testing.repository.AuthorRepository;
import uz.jwa.deep.learning.testing.repository.BookRepository;

import java.util.Objects;

/**
 * @author TheJWA (Jonibek)
 * @since 2/16/2023 Thursday
 */
@Service
@RequiredArgsConstructor
public class AllService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final MockTestService mockTestService;

    public ResponseEntity<Book> createBook(Book book) {
        if (Objects.nonNull(book.getId()))
            throw new RuntimeException("WHILE CREATING ID MUST NOT BE SET");
        if (!mockTestService.authorExists(book.getAuthorId()))
            throw new RuntimeException("AUTHOR NOT FOUND");
        bookRepository.save(book);
        return ResponseEntity.ok(book);
    }

    public ResponseEntity<Book> getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("BOOK NOT FOUND"));
        return ResponseEntity.ok(book);
    }

    public ResponseEntity<Page<Book>> getAllBooks(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        return ResponseEntity.ok(books);
    }

    public ResponseEntity<Author> createAuthor(Author author) {
        if (Objects.nonNull(author.getId()))
            throw new RuntimeException("WHILE CREATING ID MUST NOT BE SET");
        authorRepository.save(author);
        return ResponseEntity.ok(author);
    }

    public ResponseEntity<Author> getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("AUTHOR NOT FOUND"));
        return ResponseEntity.ok(author);
    }

    public ResponseEntity<Page<Author>> getAllAuthors(Pageable pageable) {
        Page<Author> authors = authorRepository.findAll(pageable);
        return ResponseEntity.ok(authors);
    }
}
