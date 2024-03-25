package shop.mtcoding.blog.model.jobs;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobsJPARepository extends JpaRepository <Jobs, Integer> {

    @Query("select j from Jobs j left join fetch j.skillList s")
    List<Jobs> findAllJoinSkill(Sort sort);
}
