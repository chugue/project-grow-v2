package shop.mtcoding.blog.model.comp;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog.model.resume.Resume;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CompController {

    private final CompService compService;

    @GetMapping("/comp/{id}/comp-resume-detail")
    public String compResumeDetail(@PathVariable Integer id) {
        return "/comp/comp-resume-detail";
    }

    @PutMapping("/comp/{id}")
    public String updateForm(@PathVariable Integer id) {
        return "redirect:/comp/" + id + "/comphome";
    }

    @GetMapping("/comp/comp-index")
    public String compIndex() {
        return "comp/comp-index";
    }

    @GetMapping("/comp/{id}/comphome")
    public String compHome(@PathVariable Integer id) {
        return "/comp/comphome";
    }

    @PostMapping("/comp/{id}/apply")
    public String offer(@PathVariable Integer id) {
        return "redirect:/";
    }

    @GetMapping("/comp/join-form")
    public String compJoinForm() {

        return "/comp/join-form";
    }

    @PostMapping("/comp/join")
    public String compJoin(CompRequest.CompJoinDTO reqDTO) {
        compService.join(reqDTO);
        return "redirect:/comp/read-resume";
    }

    @GetMapping("/comp/profile-update-form")
    public String profileUpdateForm() {
        return "/comp/profile-update-form";
    }

    @GetMapping("/comp/read-resume")
    public String readResume(HttpServletRequest request) {

        // 기업이 볼 이력서 전체보기
        List<Resume> readResumeList = compService.findAll();
        request.setAttribute("readResumeList", readResumeList);

        return "/comp/read-resume";
    }

    @GetMapping("/comp/{id}/scrap")
    public String scrap(@PathVariable Integer id) {
        return "/comp/scrap";
    }

    @GetMapping("/comp/talent")
    public String talent() {
        return "/comp/talent";
    }

    @GetMapping("/comp/jobs-info")
    public String jobsInfo() {

        return null;
    }


}





