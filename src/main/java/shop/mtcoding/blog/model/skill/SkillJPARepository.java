package shop.mtcoding.blog.model.skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SkillJPARepository extends JpaRepository<Skill, Integer> {

    @Query("select s from Skill s where s.jobs.id = :jobsId")
    List<Skill> findAllById(@Param("jobsId") Integer jobsId);

    @Query("select s from Skill s where s.resume.id = :resumeId")
    List<Skill> findAllByResumeId(@Param("resumeId") Integer resumeId);

    @Query("select s from Skill s where s.jobs.id = :jobsId")
    List<Skill> findAllByJobsId(@Param("jobsId") Integer jobsId);
}
