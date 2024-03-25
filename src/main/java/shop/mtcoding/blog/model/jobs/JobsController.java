package shop.mtcoding.blog.model.jobs;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import shop.mtcoding.blog.model.skill.SkillService;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class JobsController {
    private final JobsService jobsService;
    private final SkillService skillService;

//    @GetMapping("/jobs/info")
//    public String jobsInfo (HttpServletRequest request) {
//        List<Jobs> jobsList = jobsService.jobsList();
//        request.setAttribute("jobsList", jobsList);
//        return "/jobs/info";
//    }
}
