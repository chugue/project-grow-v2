package shop.mtcoding.blog.model.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.sql.Date;


@Import(UserRepository.class)
@DataJpaTest
public class UpdateTest {
    @Autowired
    private EntityManager em;

    @Test
    public void update (){
        UserRequest.UpdateDTO requestDTO = new UserRequest.UpdateDTO();
        requestDTO.setPassword("1234");
        requestDTO.setMyName("1234");
        requestDTO.setPhone("1234");
        requestDTO.setBirth(Date.valueOf("1112-11-22"));
        requestDTO.setAddress("1234");
        int id = 1;


        String q = """
                UPDATE user_tb
                SET password = ?, my_name = ?, phone = ?,  birth = ?, address = ?
                WHERE id =?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getPassword());
        query.setParameter(2, requestDTO.getMyName());
        query.setParameter(3, requestDTO.getPhone());
        query.setParameter(4, requestDTO.getBirth());
        query.setParameter(5, requestDTO.getAddress());
        query.setParameter(6, id);
        query.executeUpdate();
    }

}
