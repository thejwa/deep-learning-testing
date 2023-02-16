package uz.jwa.deep.learning.testing.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.jwa.deep.learning.testing.entity.Author;
import uz.jwa.deep.learning.testing.repository.AuthorRepository;

import java.util.Optional;

/**
 * @author TheJWA (Jonibek)
 * @since 2/16/2023 Thursday
 */
@Service
@RequiredArgsConstructor
public class MockTestService {
    private final AuthorRepository authorRepository;

    public boolean authorExists(Long authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        return authorOptional.isPresent();
    }
}
