package shop.mtcoding.blog.model.resume;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.model.resume.ResumeRepository;

@Import(ResumeRepository.class)
@DataJpaTest
public class ResumeRepositoryTest {

    @Autowired
    private ResumeRepository resumeRepository;

    @Test
    public void findAll_test() {
        //given

        //when
        resumeRepository.findAll();

    }

}
