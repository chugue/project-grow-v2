package shop.mtcoding.blog.model.comp;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog.model.user.User;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CompService {

    private final CompJPARepository compJPARepo;
    private final HttpSession session;

    @Transactional
    public void join(CompRequest.CompJoinDTO reqDTO) {
        compJPARepo.save(reqDTO.toEntity(2));

        // 전에거에 있던 이메일 찾아서 그걸로 세션저장해서 회원가입 직후 바로 로그인 되는거 구현하려고 만듬
        User comp = compJPARepo.findByEmail(reqDTO.getEmail());
        session.setAttribute("comp", comp);
    }
}
