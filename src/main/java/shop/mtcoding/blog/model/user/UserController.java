package shop.mtcoding.blog.model.user;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;
import shop.mtcoding.blog.model.apply.ApplyRepository;
import shop.mtcoding.blog.model.apply.ApplyRequest;
import shop.mtcoding.blog.model.offer.OfferRepository;
import shop.mtcoding.blog.model.profile.ProfileRepository;
import shop.mtcoding.blog.model.profile.ProfileRequest;
import shop.mtcoding.blog.model.resume.ResumeRepository;
import shop.mtcoding.blog.model.resume.ResumeRequest;
import shop.mtcoding.blog.model.skill.SkillRepository;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserRepository;

import shop.mtcoding.blog.model.user.UserRequest;
import shop.mtcoding.blog.model.user.UserResponse;

import shop.mtcoding.blog.util.ApiUtil;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserRepository userRepository;
    private final OfferRepository offerRepository;
    private final ApplyRepository applyRepository;
    private final HttpSession session;
    private final ResumeRepository resumeRepository;
    private final SkillRepository skillRepository;

    //아이디 중복체크 용. 이메일로 회원가입해서 email을 해줌
    @GetMapping("/api/user/username-same-check")
    public @ResponseBody ApiUtil<?> usernameSameCheck(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return new ApiUtil<>(true);
        } else {
            return new ApiUtil<>(false);
        }

    }


    @PostMapping("/user/join")
    public String join(@RequestParam ("role") int role, UserRequest.UserAllDTO requestDTO) {
        requestDTO.setRole(role);
        userRepository.save(requestDTO);

        User user = userRepository.findByEmail(requestDTO.getEmail());
        session.setAttribute("sessionUser", user);
        return "redirect:/";
    }

    @PostMapping("/user/login")
    public String login(UserRequest.LoginDTO requestDTO) {
        User user = (User) userRepository.findByEmailAndPassword(requestDTO);
        int role;
        if (user == null) {
            return "errors/401";
        } else {
            role = user.getRole();
            if (role == 1) {
                session.setAttribute("sessionUser", user);
                return "redirect:/";
            } else if (role == 2) {
                session.setAttribute("sessionComp", user);
                return  "redirect:/comp/jobsInfo";
            }
        }
        return "/user/loginForm";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }


    @GetMapping("/user/joinForm")
    public String joinForm() {
        return "/user/joinForm";
    }

    @GetMapping("/user/loginForm")
    public String loginForm() {
        return "/user/loginForm";
    }


//    @GetMapping("/user/{id}/applyList")
//    public String apply(@PathVariable Integer id,
//                        @RequestParam("resumeId") Integer resumeId,
//                        HttpServletRequest request) {
//
//        User sessionUser = (User) session.getAttribute("sessionUser");
//        request.setAttribute("userId", sessionUser.getId());
//
//        List<ApplyRequest.ApplyResumeJobsDTO2> applyList = applyRepository.findAllByResumeId(resumeId);
//
//        for (int i = 0; i < applyList.size(); i++) {
//            ApplyRequest.ApplyResumeJobsDTO2 dto = applyList.get(i);
//            dto.setSkillList(offerRepository.findAllSkillById(dto.getId()));
//        }
//        request.setAttribute("applyList", applyList);
//
//
//        List<UserResponse.UserListByUserId> resumeList = offerRepository.findAllByResumeId(id);
//
//        for (int i = 0; i < resumeList.size(); i++) {
//            UserResponse.UserListByUserId dto = resumeList.get(i);
//            dto.setSkillList(offerRepository.findAllSkillById(dto.getId()));
//        }
//
//        request.setAttribute("resumeList", resumeList);
//
//        return "/user/apply";
//    }

    @GetMapping("/user/{id}/applyList")
    public String offer(@PathVariable Integer id,
                        @RequestParam(required = false, defaultValue = "1") Integer resumeId,
                        HttpServletRequest request) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        request.setAttribute("userId", sessionUser.getId());

        List<UserRequest.ResumeOfterDTO> offerList = offerRepository.findAllByJobsId2(resumeId);

        for (int i = 0; i < offerList.size(); i++) {
            UserRequest.ResumeOfterDTO dto = offerList.get(i);
            dto.setSkillList(offerRepository.findAllSkillById(dto.getId()));
            if (dto.getIsPass() == 1) {
                String passOrFail = "대기중입니다.";
                request.setAttribute("wait", passOrFail);
            } else if (dto.getIsPass() == 2) {
                String passOrFail = "불합격 입니다.";
                request.setAttribute("fail", passOrFail);
            } else if (dto.getIsPass() == 3) {
                String passOrFail = "합격 입니다!";
                request.setAttribute("pass", passOrFail);
            }
        }
        request.setAttribute("offerList", offerList);


        List<UserResponse.UserListByUserId> resumeList = offerRepository.findAllByResumeId(id);

        for (int i = 0; i < resumeList.size(); i++) {
            UserResponse.UserListByUserId dto = resumeList.get(i);
            dto.setSkillList(offerRepository.findAllSkillById(dto.getId()));
        }

        request.setAttribute("resumeList", resumeList);

        return "/user/apply";
    }



    @GetMapping("/user/{id}/scrap")
    public String scrap(@PathVariable Integer id) {
        return "/user/scrap";
    }


    @PostMapping("/user/{id}/update")
    public String updateForm(@PathVariable Integer id, UserRequest.UpdateDTO requestDTO, HttpServletRequest request) {
        userRepository.updateById(id, requestDTO);

        return "redirect:/user/"+ id +"/userHome";
    }

    @GetMapping("/user/{id}/updateForm")
    public String updateForm(@PathVariable int id, HttpServletRequest request) {
        User user = userRepository.findById(id);
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == user){
            return "redirect:/loginForm";
        }
        request.setAttribute("user", user);
        return "/user/updateForm";
    }

    @GetMapping("/user/{id}/userHome")
    public String userHome(@PathVariable Integer id, HttpServletRequest request) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        List<ResumeRequest.UserViewDTO> resumeList = resumeRepository.findAllUserId(id);
        System.out.println(request); // 이거 스킬 안넣었을때 리스트

        for (int i = 0; i < resumeList.size(); i++) {
            //우리가 아까만든 생성자에 resumeList 값들이 들어간다
            ResumeRequest.UserViewDTO dto = resumeList.get(i);
            dto.setSkillList(resumeRepository.findAllByResumeId(dto.getId()));
        }
        int count = resumeList.size();
        request.setAttribute("count", count);
        request.setAttribute("sessionUserId", sessionUser.getId());
        request.setAttribute("resumeList", resumeList);
        System.out.println(request); // 이건 스킬추카하고 나서 리스트
//        List<SkillResponse.ResumeSkillDTO> resumeSkillList = resumeRepository.findAllByResumeId(id);
        return "/user/userHome";
    }

    // 이미지업로드용
    @PostMapping("/user/profileUpload")
    public String profileUpload(ProfileRequest.ProfileDTO requestDTO) {
        // 1. 데이터 전달 받기
        MultipartFile imgFile = requestDTO.getPhoto();
        String imgFileName = imgFile.getOriginalFilename();

        // 2. 파일저장 위치 설정해서 파일을 저장 (UUID 붙여서 롤링)
        // 랜덤한 UUID값에 _붙이고 뒤에 오리지널 파일 이름 붙여줌 그러고 저장소에 저장 (롤링)
        // String imgFilename = UUID.randomUUID() + "_" +imgFile.getOriginalFilename();

        // 저장소에 롤링한 이름의 이미지를 업로드
        // Path imgPath = Paths.get("./upload/" + imgFile);
        Path imgPath = Paths.get("./src/main/resources/photos/" + imgFileName);

        try {
            Files.write(imgPath, imgFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/user/profileUpdateForm";
    }

    // 이미지업로드용
    @GetMapping("/user/profileUpdateForm")
    public String profileUpdateForm(HttpServletRequest request) {

        // User user = profileRepository.findById(여기다 유저 키값 DTO로 받아오면 됨);

        // request.setAttribute("image", user);

        return "/user/profileUpdateForm";
    }
}
