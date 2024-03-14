package shop.mtcoding.blog.model.apply;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.model.comp.CompRepository;

import java.util.List;


@Import(ApplyRepository.class)
@DataJpaTest
public class ApplyRepositoryTest {
    @Autowired
    private ApplyRepository applyRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void saveTest() {
        int resumeId = 1;
        int jobsId = 3;


        String q = """
                INSERT INTO apply_tb (resume_id, jobs_id, is_pass, created_at)
                VALUES ( ?, ?, 0, NOW())
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, resumeId);
        query.setParameter(2, jobsId);
        query.executeUpdate();
    }

    @Test
    public void findAllByJobsIdWithApply() {
        int resumeId = 1;
        String q = """
                    SELECT j.*, r.id, a.is_pass
                    FROM jobs_tb j
                    JOIN apply_tb a ON j.id = a.jobs_id
                    JOIN resume_tb r ON a.resume_id = r.id
                    WHERE resume_id = ?;
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, resumeId);

        JpaResultMapper mapper = new JpaResultMapper();
        List<ApplyRequest.ApplyResumeJobsDTO2> result = mapper.list(query, ApplyRequest.ApplyResumeJobsDTO2.class);

    }
}
