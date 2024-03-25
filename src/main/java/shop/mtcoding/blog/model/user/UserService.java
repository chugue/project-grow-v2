package shop.mtcoding.blog.model.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.util.ApiUtil;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJPARepository userRepo;

    public User join (UserRequest.JoinDTO reqDTO, Integer role){
        return userRepo.save(reqDTO.toEntity(role));
    }

    public  User login (UserRequest.LoginDTO reqDTO){
        return userRepo.findByIdAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("회원 정보가 없습니다."));
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
