package shop.mtcoding.blog.model.comp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(CompRepository.class)
@DataJpaTest
public class CompRepositoryTest {
    @Autowired
    private CompRepository compRepository;

    @Test
    public void findAll_test() {
        compRepository.findAllByUserId();
    }
}
