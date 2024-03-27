package shop.mtcoding.blog.model.user;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.resume.ResumeJPARepository;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillJPARepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Import(UserService.class)
@DataJpaTest
public class UserServiceTest {
    @Autowired
    private UserJPARepository userJPARepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ResumeJPARepository resumeRepo;
    @Autowired
    private SkillJPARepository skillRepo;


    @Test
    public void UserResumeSkillDTO_test() {
        //given
        int userId =1;
        //when
        List<UserResponse.UserResumeSkillDTO> ursList = new ArrayList<>();
        List<Resume> resumeList = resumeRepo.findAllByUserId(userId);
        User user = userJPARepository.findById(userId).orElseThrow(() -> new Exception401("sdfs"));

        for (int i = 0; i < resumeList.size(); i++) {
            List<Skill> skills = skillRepo.findAllByResumeId(resumeList.get(i).getId());
            ursList.add(UserResponse.UserResumeSkillDTO.builder()
                    .user(user)
                    .resume(resumeList.get(i))
                    .skillList(skills).build());
        }
        //then


    }
    @Test
    public void join_test(){
        // given
        Integer role = 1;
        UserRequest.JoinDTO reqDTO = new UserRequest.JoinDTO();
        reqDTO.setEmail("chugue@naver.com");
        reqDTO.setMyName("chugue");
        reqDTO.setPassword("1234");
        reqDTO.setPhone("01034220935");
        // when
        userService.join(reqDTO, role);
        // then

    }

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
