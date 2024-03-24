package shop.mtcoding.blog.model.user;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.mtcoding.blog._core.errors.exception.Exception401;

import java.util.Optional;

@Import(UserService.class)
@DataJpaTest
public class UserServiceTest {
    @Autowired
    private UserJPARepository userJPARepository;

    @Test
    public void login_test(){
        // given
        UserRequest.LoginDTO reqDTO = new UserRequest.LoginDTO();
        reqDTO.setEmail("bluepig4b@naver.com");
        reqDTO.setPassword("1234");
        // when
        User user = userJPARepository.findByIdAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("회원 정보가 없습니다."));
        // then
        System.out.println(user.getEmail());
    }
}
