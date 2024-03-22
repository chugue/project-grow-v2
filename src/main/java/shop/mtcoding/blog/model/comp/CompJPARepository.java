package shop.mtcoding.blog.model.comp;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.mtcoding.blog.model.user.User;

public interface CompJPARepository extends JpaRepository <User, Integer>{
}
