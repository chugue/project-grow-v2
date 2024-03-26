package shop.mtcoding.blog.model.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog._core.util.ApiUtil;
import shop.mtcoding.blog.model.resume.ResumeJPARepository;
import shop.mtcoding.blog.model.resume.ResumeRequest;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJPARepository userRepo;
    private final ResumeJPARepository resumeJPARepo;

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

        // TODO : 여기 오류남
        List<ResumeRequest.UserViewDTO> resumeList = resumeJPARepo.findAllUserId(id);

        for (int i = 0; i < resumeList.size(); i++) {
            ResumeRequest.UserViewDTO dto = resumeList.get(i);
            dto.setSkillList(resumeJPARepo.findAllByResumeId(dto.getId()));
        }

        return resumeList;
    }
}
