package shop.mtcoding.blog.model.comp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog.model.jobs.JobsResponse;
import shop.mtcoding.blog.model.resume.ResumeResponse;
import shop.mtcoding.blog.model.resume.ResumeService;
import shop.mtcoding.blog.model.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CompController {
    private final CompService compService;
    private final HttpSession session;
    private final ResumeService resumeService;

    @GetMapping("/comp/{id}/comp-resume-detail")
    public String compResumeDetail(@PathVariable Integer id) {
        return "/comp/comp-resume-detail";
    }

    @PostMapping("/comp/{id}/update")
    public String updateForm(@PathVariable Integer id) {
        return "redirect:/comp/" + id + "/comphome";
    }

    @GetMapping("/comp/{id}/update-form")
    public String updateForm(@PathVariable int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = compService.findById(sessionUser.getId());
        request.setAttribute("user", newSessionUser);

        return "/user/update-form";
    }

    @GetMapping("/comp/comp-index")
    public String compIndex(HttpServletRequest request) {
        List<CompResponse.ResumeUserSkillDTO> rusList = compService.findAllRusList();
        request.setAttribute("rusList", rusList);
        return "comp/comp-index";
    }

    @GetMapping("/comp/{id}/comp-home")
    public String compHome(@PathVariable Integer id, HttpServletRequest request) {
        List<JobsResponse.JobsListDTO> jobsList = compService.findAllJobsId(id);
        request.setAttribute("jobList", jobsList);
        request.setAttribute("sessionC", id);
        System.out.println(jobsList.toString());

        return "/comp/comp-home";
    }

    @GetMapping("/comp/{id}/apply")
    public String offer(@PathVariable Integer id) {

        return "/comp/apply";
    }

    @GetMapping("/comp/join-form")
    public String compJoinForm() {

        return "/comp/join-form";
    }

    @PostMapping("/comp/join")
    public String compJoin(CompRequest.CompJoinDTO reqDTO) {
        User user = compService.join(reqDTO);
        session.setAttribute("sessionComp", user);
        return "redirect:/comp/read-resume";
    }

    @GetMapping("/comp/profile-update-form")
    public String profileUpdateForm(HttpServletRequest request) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        request.setAttribute("imgFileName",sessionUser.getImgFileName());
        return "/comp/profile-update-form";
    }

    @GetMapping("/comp/read-resume")
    public String readResume(HttpServletRequest request) {

        List<ResumeResponse.ResumeDTO> resumes = resumeService.findAll();
        request.setAttribute("readResumeList", resumes);

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

        return "/comp/jobs-info";
    }


}





