package shop.mtcoding.blog.model.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception401;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJPARepository userRepo;

    public  User login (UserRequest.LoginDTO reqDTO){
        return userRepo.findByIdAndPassword(reqDTO.getEmail(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("회원 정보가 없습니다."));
    }
}
