package shop.mtcoding.blog.model.user;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


@Import(UserRepository.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private EntityManager em;

    @Test
    public void findById_test(){
        // given
        int id = 1;
        // when
        em.find(User.class, id);

    }

    @Test
    public void findByEmailAndPassword_test(){
        UserRequest.LoginDTO requestDTO = new UserRequest.LoginDTO();
        requestDTO.setEmail("bluepig4b@naver.com");
        requestDTO.setPassword("1234");

        String q = """
                SELECT u FROM User u 
                WHERE u.email = :email AND u.password = :password
                """;
        Query query = em.createQuery(q, User.class);
        query.setParameter("email", requestDTO.getEmail());
        query.setParameter("password", requestDTO.getPassword());
        User user = (User) query.getSingleResult();

        System.out.println(user.toString());
    }
}
