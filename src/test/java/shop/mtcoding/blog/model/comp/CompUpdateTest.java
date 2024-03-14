package shop.mtcoding.blog.model.comp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.model.user.UserRepository;

import java.sql.Date;

@Import(UserRepository.class)
@DataJpaTest
public class CompUpdateTest {

    @Autowired
    private EntityManager em;

    @Test
    public void updateById() {
        CompRequest.UpdateDTO requestDTO = new CompRequest.UpdateDTO();
        requestDTO.setMyName("테스트");
        requestDTO.setPassword("1234");
        requestDTO.setCompName("테스트");
        requestDTO.setPhone("테스트");
        requestDTO.setAddress("테스트");
        requestDTO.setBirth(Date.valueOf("1985-11-24"));
        requestDTO.setHomepage("테스트");
        int id = 4;


        String q = """
                UPDATE user_tb
                SET my_name = ?, password = ?,  comp_name = ?, phone = ?,  address = ?, birth = ?,  homepage = ?
                WHERE id =?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getMyName());
        query.setParameter(2, requestDTO.getPassword());
        query.setParameter(3, requestDTO.getCompName());
        query.setParameter(4, requestDTO.getPhone());
        query.setParameter(5, requestDTO.getAddress());
        query.setParameter(5, requestDTO.getBirth());
        query.setParameter(5, requestDTO.getHomepage());
        query.setParameter(6, id);
        query.executeUpdate();
    }

}

