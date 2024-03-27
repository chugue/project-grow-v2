package shop.mtcoding.blog.model.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import shop.mtcoding.blog.model.apply.ApplyResponse;


import java.util.List;
import java.util.Optional;

public interface ResumeJPARepository extends JpaRepository<Resume, Integer> {

    @Query("select r from Resume r join fetch r.user u order by r.id desc")
    List<Resume> findAllJoinUser();


    @Query("select r from Resume r where r.id = :id order by r.id desc")
    List<Resume> findAllUserId(@Param("id") Integer id);

    @Query("select r from Resume r where r.user.id = :userId")
    List<Resume> findAllByUserId(@Param("userId") Integer userId);

}
