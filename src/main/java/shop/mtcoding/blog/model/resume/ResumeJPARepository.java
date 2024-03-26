package shop.mtcoding.blog.model.resume;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.mtcoding.blog.model.skill.SkillRequest;

import java.util.List;
import java.util.Optional;

public interface ResumeJPARepository extends JpaRepository<Resume, Integer> {

    @Query("select r from Resume r join fetch r.user u order by r.id desc")
    List<Resume> findAllJoinUser();

    @Query("select r.id, r.title, r.edu, r.area, r.career from Resume r where r.id = :id")
    List<ResumeRequest.UserViewDTO> findAllUserId(@Param("id") Integer id);

    @Query("select s.name, s.color from Skill s inner join fetch Resume r on s.resume.id = r.id where r.id = :id")
    List<SkillRequest.ResumeSkillDTO> findAllByResumeId(Integer id);
}
