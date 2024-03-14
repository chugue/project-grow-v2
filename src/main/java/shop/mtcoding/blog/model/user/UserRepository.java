package shop.mtcoding.blog.model.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    public List<User> findAll() {
        Query query = em.createNativeQuery("select * from user_tb order by id desc", User.class);

        List<User> userList = query.getResultList();
        return userList;
    }

    public List<User> findByCompAll() {
        Query query = em.createNativeQuery("select * from user_tb where role = 2 order by id desc ", User.class);

        List<User> userList = query.getResultList();
        return userList;
    }


    public User findById(Integer id) {
        User user = em.find(User.class, id);
        return user;
    }


    @Transactional
    public void save(UserRequest.UserAllDTO requestDTO) {
        String q = """
                insert into user_tb(email, my_name,password,phone, address, birth, business_number,photo, comp_name,homepage,role,created_at)
                values (?,?,?,?,?,?,?,?,?,?,?,now());
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, requestDTO.getEmail());
        query.setParameter(2, requestDTO.getMyName());
        query.setParameter(3, requestDTO.getPassword());
        query.setParameter(4, requestDTO.getPhone());
        query.setParameter(5, requestDTO.getAddress());
        query.setParameter(6, requestDTO.getBirth());
        query.setParameter(7, requestDTO.getBusinessNumber());
        query.setParameter(8, requestDTO.getPhoto());
        query.setParameter(9, requestDTO.getCompName());
        query.setParameter(10, requestDTO.getHomepage());
        query.setParameter(11, requestDTO.getRole());
        query.executeUpdate();
    }

    @Transactional
    public void deleteById() {
    }

    public User findByEmailAndPassword(UserRequest.LoginDTO requestDTO) {
        String q = """
                SELECT u FROM User u 
                WHERE u.email = :email AND u.password = :password
                """;
        Query query = em.createQuery(q, User.class);
        query.setParameter("email", requestDTO.getEmail());
        query.setParameter("password", requestDTO.getPassword());
        User user = (User) query.getSingleResult();

        return user;
    }

    //회원가입 시 중복확인을 위해 email 찾는 메소드
    public User findByEmail(String email) {
        String q = """
                select * from user_tb where email = ?
                """;

        Query query = em.createNativeQuery(q, User.class);
        query.setParameter(1, email);

        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }

    }

    @Transactional
    public void updateById(int id, UserRequest.UpdateDTO requestDTO) {
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


    @Transactional
    public User updateImgFileName(String imgFileName, Integer id) {
        String q = "update user_tb set img_file_name = ? where id = ?";
        Query query = em.createNativeQuery(q);
        query.setParameter(1, imgFileName);
        query.setParameter(2, id);
        query.executeUpdate();

        return findById(id);
    }

}
