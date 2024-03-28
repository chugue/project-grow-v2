package shop.mtcoding.blog.model.apply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.jobs.JobsJPARepository;
import shop.mtcoding.blog.model.resume.Resume;

@Import(ApplyService.class)
@DataJpaTest
public class ApplyServiceTest {
    @Autowired
    private ApplyService applyService;
    @Autowired
    private JobsJPARepository jobsJPARepo;



    @Test
    public void newApply_test(){
        // given
        int jobsId = 1;
        int resumeId = 1;
        // when
        applyService.newApply(jobsId, resumeId);
        // then

    }
}
