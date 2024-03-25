package shop.mtcoding.blog.model.jobs;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillService;

import java.util.List;

@Import(JobsService.class)
@DataJpaTest
public class JobsServiceTest {
    @Autowired
    private JobsService jobsService;

    @Test
    public void jobsList_test(){
        // given

        // when
        List<Jobs> jobsList = jobsService.jobsList();
        // then
        System.out.println(jobsList.size());
        System.out.println(jobsList.get(0).getSkillList().size());
    }
}
