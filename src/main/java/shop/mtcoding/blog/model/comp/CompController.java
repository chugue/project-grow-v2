package shop.mtcoding.blog.model.comp;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog.model.skill.SkillRequest;
import shop.mtcoding.blog.model.user.User;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class CompController {

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
    public String compJoin(@RequestParam("role") Integer role) {
        return "redirect:/comp/comp-index";
    }

    @GetMapping("/comp/profile-update-form")
    public String profileUpdateForm() {
        return "/comp/profile-update-form";
    }

    @GetMapping("/comp/read-resume")
    public String readResume() {
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





