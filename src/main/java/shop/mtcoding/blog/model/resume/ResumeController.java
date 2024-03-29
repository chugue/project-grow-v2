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

    @GetMapping("/resume/resume-detail/{id}")
    public String resumeDetail(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userService.findById(sessionUser.getId());

        Resume resume = resumeService.resumeDetail(id, newSessionUser);
        request.setAttribute("resume", resume);

        return "resume/resume-detail";
    }

    @GetMapping("/resume/{id}/manage-resume")
    public String manageResume(@PathVariable Integer id) {

        return "/resume/manage-resume";
    }

    @GetMapping("/resume/{id}/write-resume-form")
    public String writeResumeForm() {

        return "/resume/write-resume-form";
    }


    @GetMapping("/resume/{id}/update-resume-form")
    public String updateResumeForm(@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Resume resume = resumeService.updateForm(sessionUser.getId());
        request.setAttribute("resume", resume);

        return "resume/update-resume-form";
    }

    @PostMapping("/resume/{id}/update")
    public String update(@PathVariable Integer id, ResumeRequest.UpdateDTO reqDTO, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        //해당 부분 redirect 해보고 틀렸으면 본인이 수정
        resumeService.update(id, sessionUser.getId(), reqDTO);
        return "redirect:/resume/"+id+"/manage-resume";
    }

    @PostMapping("/resume/save")
    public String save(ResumeRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.save(reqDTO);
        return "redirect:/user/"+sessionUser.getId()+"/user-home";
    }

    @PostMapping("/resume/{id}/delete")
    public String delete(@PathVariable Integer id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        resumeService.delete(id);

        return "redirect:/user/"+sessionUser.getId()+"/user-home";

    }


}
