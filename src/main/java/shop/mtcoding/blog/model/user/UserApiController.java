package shop.mtcoding.blog.model.user;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;
    private final HttpSession session;


    @PostMapping("/api/find-jobs-resume")
    public List<UserResponse.UrsDTO> findAllJobsByResumeId(@RequestParam(name = "resumeId") Integer resumeId, HttpServletRequest request){
        List<UserResponse.UrsDTO> ursDTOList = userService.ursDTOS(resumeId);
        request.setAttribute("ursDTOList", ursDTOList);
        return ursDTOList;
    }

}
