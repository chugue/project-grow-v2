package shop.mtcoding.blog.model.apply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class ApplyController {

    private final ApplyService applyService;

    @PostMapping("/jobs/apply")
    public String applySave() {

        return "redirect:/";
    }

    @PutMapping("/apply/pass/{id}")
    public String applyPassUpDate(@PathVariable Integer id) {
        return "redirect:/";
    }

    @PutMapping("/apply/fail/{id}")
    public String applyFailUpDate(@PathVariable Integer id) {
        return "redirect:/";
    }

    @PutMapping("/apply/pass2/{id}")
    public String applyPassUpDate2(@PathVariable Integer id) {

        return "redirect:/";
    }

    @PutMapping("/apply/fail2/{id}")
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

