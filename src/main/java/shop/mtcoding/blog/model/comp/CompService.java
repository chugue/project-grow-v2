package shop.mtcoding.blog.model.comp;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.resume.ResumeJPARepository;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserRequest;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CompService {

    private final CompJPARepository compJPARepo;
    private final ResumeJPARepository resumeJPARepo;
    private final HttpSession session;

    // 기업 회원가입
    @Transactional
    public void join(CompRequest.CompJoinDTO reqDTO) {
        compJPARepo.save(reqDTO.toEntity(2));

        // 전에거에 있던 이메일 찾아서 그걸로 세션저장해서 회원가입 직후 바로 로그인 되는거 구현하려고 만듬
        User comp = compJPARepo.findByEmail(reqDTO.getEmail());
        session.setAttribute("comp", comp);
    }

    // 기업 로그인하면 보여줄 이력서 목록들
    public List<Resume> findAll() {
        List<Resume> resumeList = resumeJPARepo.findAllJoinUser();
        return resumeList;
    }

    @jakarta.transaction.Transactional
    public User updateById(User sessionUser, UserRequest.UpdateDTO requestDTO) {
        System.out.println(requestDTO);
        User user = compJPARepo.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception401("로그인이 필요한 서비스입니다."));

        user.update(requestDTO);
//        user.setPassword(requestDTO.getPassword());
//        user.setMyName(requestDTO.getMyName());
//        user.setBirth(requestDTO.getBirth());
//        user.setPhone(requestDTO.getPhone());
//        user.setAddress(requestDTO.getAddress());

        return user;
    }

    //유저 회원 정보 업데이트용 조회
    public User findById(Integer sessionUserId) {
        User user = compJPARepo.findById(sessionUserId)
                .orElseThrow(() -> new Exception401("로그인이 필요한 서비스입니다."));
        return user;

    }

}
