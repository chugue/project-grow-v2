package shop.mtcoding.blog.model.jobs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public interface JobsJPARepository extends JpaRepository <Jobs, Integer> {
  
    @Query("select j from Jobs j where j.user.id = :userId")
    List<Jobs> findAllByJobsId(@Param("userId") Integer userId);
}