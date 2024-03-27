package shop.mtcoding.blog.model.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Controller
public class ResumeController {
    private final ResumeService resumeService;

    @GetMapping("/resume/resume-detail/{id}")
    public String resumeDetail(@PathVariable Integer id) {

        return "/resume/resume-detail";
    }

    @GetMapping("/resume/{id}/manage-resume")
    public String manageResume(@PathVariable Integer id) {

        return "/resume/manage-resume";
    }

    @GetMapping("/resume/write-resume-form")
    public String writeResumeForm() {

        return "/resume/write-resume-form";
    }

    @GetMapping("/resume/{id}/update-resume-form")
    public String updateResumeForm(@PathVariable int id) {

        return "/resume/update-resume-form";
    }

    @PostMapping("/resume/{id}/update")
    public String update(@PathVariable Integer id) {

        //해당 부분 redirect 해보고 틀렸으면 본인이 수정
        return "redirect:/board/" + id;
    }

    @PostMapping("/resume/save")
    public String save(ResumeRequest.SaveDTO reqDTO) {

        resumeService.save(reqDTO);
        return "redirect:/";
    }

    @PostMapping("/resume/{id}/delete")
    public String delete(@PathVariable int id) {

        resumeService.delete(id);

        return "redirect:/user/user-home";

    }

}
