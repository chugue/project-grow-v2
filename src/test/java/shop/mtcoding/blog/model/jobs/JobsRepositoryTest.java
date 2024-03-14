package shop.mtcoding.blog.model.jobs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(JobsRepository.class)
@DataJpaTest
public class JobsRepositoryTest {

    @Autowired
    private JobsRepository jobsRepository;

    @Test
    public void findAll_test() {
        //given

        //when
        // jobsRepository.findAll();

    }

}
