package shop.mtcoding.blog.model.jobs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobJPARepository extends JpaRepository<Jobs, Integer> {
//    @Query("select j from Jobs j join fetch j.user u left join fetch j.skillList s")
//    List<Jobs> findAll2();
}
