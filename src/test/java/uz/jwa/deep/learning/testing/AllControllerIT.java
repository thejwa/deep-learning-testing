package uz.jwa.deep.learning.testing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import uz.jwa.deep.learning.testing.entity.Book;
import uz.jwa.deep.learning.testing.repository.AuthorRepository;
import uz.jwa.deep.learning.testing.repository.BookRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author TheJWA (Jonibek)
 * @since 2/16/2023 Thursday
 */
@IntegrationTest
public class AllControllerIT {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @BeforeEach
    void setUp() {
        bookRepository.saveAll(Helper.getBooksToInitialize());
        authorRepository.saveAll(Helper.getAuthorsToInitialize());
    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    @DisplayName("SUCCESS: book is created")
    void it_should_create_book() throws Exception {
        Book book = Book.builder().name("Otamdan qolgan dalalar")
                .definition("alkdfj")
                .authorId(2L)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/book")
                        .content(mapper.writeValueAsString(book))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Otamdan qolgan dalalar"))
                .andExpect(jsonPath("$.definition").value("alkdfj"))
                .andExpect(jsonPath("$.authorId").value(2L));
    }

    @Test
    @DisplayName("FAILED: id must not be set")
    void it_should_return_internal_server_error_id_must_not_be_set() throws Exception {
        Book book = Book.builder().name("Otamdan qolgan dalalar")
                .id(31L)
                .definition("alkdfj")
                .authorId(2L)
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/book")
                        .content(mapper.writeValueAsString(book))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errorMessage").value("WHILE CREATING ID MUST NOT BE SET"));

    }

    @Test
    @DisplayName("FAILED: id must not be set")
    void it_should_return_internal_server_error_author_not_found() throws Exception {
        Book book = Book.builder().name("Otamdan qolgan dalalar")
                .definition("alkdfj")
                .authorId(212L)
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/api/book")
                        .content(mapper.writeValueAsString(book))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.errorMessage").value("AUTHOR NOT FOUND"));
    }

    @Test
    @DisplayName("SUCCESS: all books are received")
    void it_shoult_return_books_with_page() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.totalElements").value(3));
    }
}
