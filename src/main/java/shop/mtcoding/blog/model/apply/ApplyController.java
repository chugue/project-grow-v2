package shop.mtcoding.blog.model.apply;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog.model.user.User;

@RequiredArgsConstructor
@Controller
public class ApplyController {
    private final ApplyService applyService;
    private final HttpSession session;

    @PostMapping("/apply/cancel")
    public ResponseEntity<?> applyCancel (@RequestParam("jobsId") Integer jobsId, @RequestParam("resumeId") Integer resumeId) {
        applyService.cancel(jobsId, resumeId);
        return ResponseEntity.ok().body("Cancelled successfully");
    }


    @PostMapping("/jobs/apply")
    public String applySave(@RequestParam(name = "jobsId") Integer jobsId, @RequestParam(name = "resumeId") Integer resumeId) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        applyService.newApply(jobsId, resumeId);

        return "redirect:/user/" + sessionUser.getId() + "/user-home";
    }

    @PostMapping("/apply/pass/{resumeId}")
    public String applyPassUpDate(@PathVariable Integer resumeId, @RequestParam("jobsId")Integer jobsId) {
        return "redirect:/";
    }

    @PostMapping("/apply/fail/{resumeId}")
    public String applyFailUpDate(@PathVariable Integer resumeId, @RequestParam("jobsId")Integer jobsId) {
        return "redirect:/";
    }

    @PostMapping("/apply/pass2/{id}")
    public String applyPassUpDate2(@PathVariable Integer id) {

        return "redirect:/";
    }

    @PostMapping("/apply/fail2/{id}")
    public String applyFailUpDate2(@PathVariable Integer id) {

        return "redirect:/";
    }

    @PostMapping("/resume/{jobId}/apply")
    public String apply(@PathVariable Integer jobId) {

        return "redirect:/";
    }

    @GetMapping("/resume/{jobId}/applyList")
    public String resumeApplyList(@PathVariable Integer jobId) {

        return "/user/apply";
    }
}

