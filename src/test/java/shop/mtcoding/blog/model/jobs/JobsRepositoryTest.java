package shop.mtcoding.blog.model.jobs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Sort;

import java.util.List;

@Import(JobsRepository.class)
@DataJpaTest
public class JobsRepositoryTest {

    @Autowired
    private JobsRepository jobsRepository;

    @Autowired
    private JobJPARepository jobJPARepository;

    @Test
    public void findAll_test() {
//
//        //given
//
//        //when
//        List<Jobs> jobsList = jobJPARepository.findAll2();
//
//        System.out.println("결과값 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ "+jobsList);

    }

}
