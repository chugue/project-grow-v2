package shop.mtcoding.blog.model.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.resume.ResumeJPARepository;
import shop.mtcoding.blog.model.resume.ResumeRequest;
import shop.mtcoding.blog.model.resume.ResumeResponse;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillJPARepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJPARepository userRepo;
    private final ResumeJPARepository resumeJPARepo;
    private final SkillJPARepository skillJPARepo;

    @Transactional
    public User join (UserRequest.JoinDTO reqDTO, Integer role){
        return userRepo.save(reqDTO.toEntity(role));
    }

    public User login (UserRequest.LoginDTO reqDTO){
        return userRepo.findByIdAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("회원 정보가 없습니다."));
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public List<ResumeRequest.UserViewDTO> userHome(Integer id) {
        List<Resume> resumeList = resumeJPARepo.findAllUserId(id);
        List<ResumeRequest.UserViewDTO> listDTO = new ArrayList<>();

        for (int i = 0; i < resumeList.size(); i++) {
            User user = userRepo.findById(resumeList.get(i).getUser().getId())
                    .orElseThrow(() -> new Exception404("사용자를 찾을 수 없습니다."));

            List<Skill> skillList = skillJPARepo.findAllById(resumeList.get(i).getId());

            listDTO.add(ResumeRequest.UserViewDTO.builder()
                    .resume(resumeList.get(i))
                    .skills(skillList)
                    .build());
        }
        System.out.println(listDTO.toString());
        return listDTO;
    }
}
