package shop.mtcoding.blog.model.resume;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserService;


@RequiredArgsConstructor
@Controller
public class ResumeController {
    private final ResumeService resumeService;
    private final HttpSession session;
    private final UserService userService;

    @GetMapping("/comp/comp-resume-detail/{resumeId}")  // 기업이 이력서를 조회했을때 필요한 로직
    public String resumeDetail(@PathVariable Integer resumeId, @RequestParam(name = "jobsId") Integer jobsId, HttpServletRequest request) {
        User sessionComp = (User) session.getAttribute("sessionComp");
        User newSessionUser = userService.findById(sessionComp.getId());
        System.out.println(111111);

        ResumeResponse.CompDetailDTO respDTO =
                resumeService.compResumeDetail(resumeId, jobsId, newSessionUser);

        request.setAttribute("resume", respDTO);
        return "comp/resume-detail";
    }

    @GetMapping("/comp/resume-detail/{resumeId}")
    public String compResumeDetail(@PathVariable Integer resumeId, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.findById(sessionUser.getId());

        ResumeResponse.DetailDTO2 resume = resumeService.resumeDetail2(resumeId, newSessionUser);
        request.setAttribute("resume", resume);

        return "comp/resume-detail";
    }

    @GetMapping("/resume/resume-detail/{resumeId}") // 개인이 이력서를 조회했을 때 필요한 로직
    public String resumeDetail2(@PathVariable Integer resumeId, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.findById(sessionUser.getId());

        ResumeResponse.DetailDTO2 resume = resumeService.resumeDetail2(resumeId, newSessionUser);
        request.setAttribute("resume", resume);

        return "resume/resume-detail";
    }



    @GetMapping("/resume/{id}/manage-resume")
    public String manageResume(@PathVariable Integer id) {

        return "resume/manage-resume";
    }

    @GetMapping("/resume/write-resume-form")
    public String writeResumeForm(){

        return "resume/write-resume-form";
    }

    @GetMapping("/resume/{id}/update-resume-form")
    public String updateResumeForm(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        request.setAttribute("sessionU", sessionUser);

        ResumeResponse.UpdateDTO resume = resumeService.updateForm(id);
        request.setAttribute("resume", resume);

        return "resume/update-resume-form";
    }

    @PostMapping("/resume/{id}/update")
    public String update(@PathVariable Integer id, ResumeRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        //해당 부분 redirect 해보고 틀렸으면 본인이 수정
        resumeService.update(id, sessionUser.getId(), reqDTO);

        return "redirect:/user/" + sessionUser.getId() + "/user-home";
    }

    @PostMapping("/resume/save")
    public String save(ResumeRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.save(reqDTO);
        return "redirect:/user/" + sessionUser.getId() + "/user-home";
    }

    @PostMapping("/resume/{id}/delete")
    public String delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.delete(id);

        return "redirect:/user/" + sessionUser.getId() + "/user-home";

    }
}
