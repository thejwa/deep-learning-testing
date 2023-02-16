package uz.jwa.deep.learning.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uz.jwa.deep.learning.testing.entity.Book;
import uz.jwa.deep.learning.testing.repository.BookRepository;
import uz.jwa.deep.learning.testing.service.AllService;
import uz.jwa.deep.learning.testing.service.MockTestService;

/**
 * @author TheJWA (Jonibek)
 * @since 2/16/2023 Thursday
 */
@ExtendWith(MockitoExtension.class)
public class AllServiceTest {
    @Mock
    private MockTestService mockTestService;
    @InjectMocks
    private AllService allService;
    @Mock
    private BookRepository bookRepository;

//    @BeforeAll
//    void setUp() {
//
//    }

    @Test
    @DisplayName("SUCCESS: book is created")
    void createBookTest() {
        Book book = getBookRequest();
        initilizeMocksForBookCreate(book);

        ResponseEntity<Book> response = allService.createBook(book);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getName(), "Deep learning, o'tgan kunlari");
        Assertions.assertEquals(response.getBody().getDefinition(), "akhjwefsa");
        Assertions.assertEquals(response.getBody().getAuthorId(), 1L);
    }

    private void initilizeMocksForBookCreate(Book book) {
        BDDMockito.when(mockTestService.authorExists(ArgumentMatchers.any()))
                .thenReturn(true);
        BDDMockito.when(bookRepository.save(ArgumentMatchers.any()))
                .thenReturn(book);
    }

    private static Book getBookRequest() {
        Book book = new Book();
        book.setAuthorId(1L);
        book.setName("Deep learning, o'tgan kunlari");
        book.setDefinition("akhjwefsa");
        return book;
    }



}
