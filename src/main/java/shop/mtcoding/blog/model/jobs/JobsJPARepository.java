package shop.mtcoding.blog.model.jobs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public interface JobsJPARepository extends JpaRepository <Jobs, Integer> {

    @Query("select j, u FROM Jobs j join fetch User u on j.user.id = u.id WHERE u.id = :userId")
    List<Jobs> findAllByUserId(@Param("userId") Integer userId);
}