package uz.jwa.deep.learning.testing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.jwa.deep.learning.testing.entity.Author;

/**
 * @author TheJWA (Jonibek)
 * @since 2/16/2023 Thursday
 */
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
