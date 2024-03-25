package shop.mtcoding.blog.model.comp;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.resume.ResumeJPARepository;
import shop.mtcoding.blog.model.user.User;

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
}
