package shop.mtcoding.blog.model.apply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.mtcoding.blog.model.resume.Resume;

import java.util.Optional;

public interface ApplyJPARepository extends JpaRepository<Apply, Integer> {

    @Query("select a from Apply a where a.resume.id = :resumeId and a.jobs.id = :jobsId")
    Optional<Apply> findByResumeIdAndJobsId(@Param("resumeId") Integer resumeId, @Param("jobsId") Integer jobsId);
}
