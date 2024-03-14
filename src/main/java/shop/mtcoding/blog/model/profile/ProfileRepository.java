package shop.mtcoding.blog.model.profile;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.blog.model.user.User;

@RequiredArgsConstructor
@Repository
public class ProfileRepository {

    private final EntityManager em;

    public void insert(String imgFilename) {
        Query query = em.createNativeQuery("insert into user_tb(photo) values(?)");
        query.setParameter(1, imgFilename);
        query.executeUpdate();
    }

    public User findById(int id) {
        Query query = em.createNativeQuery("select photo from user_tb where id = ?");
        query.setParameter(1, id);

        return (User) query.getSingleResult();
    }

}
