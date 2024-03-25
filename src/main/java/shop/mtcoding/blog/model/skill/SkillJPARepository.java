package shop.mtcoding.blog.model.skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SkillJPARepository extends JpaRepository<Skill, Integer> {

    @Query("select s from Skill s where s.jobs.id = :jobsId and s.role = :role")
    List<Skill> findByJobsIdAndRole(@Param("jobsId") Integer jobsId, @Param("role") Integer role);

    @Query("select s from Skill s where s.jobs.id = :jobsId")
    List<Skill> findAllById(@Param("jobsId") Integer jobsId);
}
