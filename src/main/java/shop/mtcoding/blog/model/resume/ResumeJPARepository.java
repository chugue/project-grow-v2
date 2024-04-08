package shop.mtcoding.blog.model.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.mtcoding.blog.model.jobs.Jobs;


import java.util.List;

public interface ResumeJPARepository extends JpaRepository<Resume, Integer> {

    @Query("select r from Resume r join fetch r.user u order by r.id desc")
    List<Resume> findAllJoinUser();



    @Query("select r from Resume r where r.id = :id order by r.id desc")
    List<Resume> findAllUserId(@Param("id") Integer id);

    @Query("select r from Resume r where r.user.id = :userId")
    List<Resume> findAllByUserId(@Param("userId") Integer userId);

    @Query("select r from Resume r join fetch r.user u where r.id = :resumeId")
    Resume findByIdJoinUser(@Param("resumeId") Integer resumeId);

    //멀티옵션 관련 쿼리

    @Query("select r from Resume r join fetch Skill s on r.id = s.resume.id where r.area like %:area%  and r.career like %:career% and s.name like %:skillName order by r.id desc")
    List<Resume> findAllByMultioption(@Param("area") String area, @Param("career") String career, @Param("skillName") String skillName);


}
