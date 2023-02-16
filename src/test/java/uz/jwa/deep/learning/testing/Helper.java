package uz.jwa.deep.learning.testing;

import uz.jwa.deep.learning.testing.entity.Author;
import uz.jwa.deep.learning.testing.entity.Book;

import java.time.LocalDate;
import java.util.List;

/**
 * @author TheJWA (Jonibek)
 * @since 2/16/2023 Thursday
 */
public class Helper {

    public static List<Author> getAuthorsToInitialize() {
        Author author1 = Author.builder()
                .id(1L)
                .firstName("Polvon")
                .lastName("Tog'o")
                .birthDate(LocalDate.EPOCH)
                .build();
        Author author2 = Author.builder()
                .id(2L)
                .firstName("Ferferro")
                .lastName("Jerjerror")
                .birthDate(LocalDate.EPOCH)
                .build();
        return List.of(author1, author2);
    }

    public static List<Book> getBooksToInitialize() {
        Book book1 = Book.builder().name("Otamdan qolgan dalalar1")
                .id(1L)
                .definition("alkdfj")
                .authorId(2L)
                .build();
        Book book2 = Book.builder().name("Otamdan qolgan dalalar2")
                .id(2L)
                .definition("alkdfj")
                .authorId(2L)
                .build();
        Book book3 = Book.builder().name("Otamdan qolgan dalalar3")
                .id(3L)
                .definition("alkdfj")
                .authorId(2L)
                .build();
        return List.of(book1, book2, book3);

    }

//    public static List<Book>
}
