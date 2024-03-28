package shop.mtcoding.blog.model.apply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.mtcoding.blog.model.resume.Resume;

import java.util.List;
import java.util.Optional;

public interface ApplyJPARepository extends JpaRepository<Apply, Integer> {

    //select * from apply_tb a join jobs_tb j on a.jobs_id = j.id where a.resume_id = 1;
    @Query("select j from Apply a join fetch Jobs j on a.jobs.id = j.id where a.resume.id = :resumeId")
    List<Apply> findAllByJobsIdAndUserId(@Param("resumeId") Integer resumeId);

    @Query("select a from Apply a where a.resume.id = :resumeId")
    Optional<Apply> findByResumeId(@Param("resumeId") Integer resumeId);

    @Query("select a from Apply a where a.resume.id = :resumeId and a.jobs.id = :jobsId")
    Optional<Apply> findByResumeIdAndJobsId(@Param("resumeId") Integer resumeId, @Param("jobsId") Integer jobsId);

    @Query("select a from Apply a where a.isPass not in ('1') and a.resume.id = :resumeId")
    List<Apply> findAppliesByNot1ByResumeId (@Param("resumeId") Integer resumeId);
}
