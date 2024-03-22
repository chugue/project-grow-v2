package shop.mtcoding.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import shop.mtcoding.blog.model.jobs.JobJPARepository;
import shop.mtcoding.blog.model.jobs.JobResponse;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.jobs.JobsRepository;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillRepository;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class MainController {
    private final JobsRepository jobsRepository;
    private final SkillRepository skillRepository;
    private final JobJPARepository jobJPARepository;

    @GetMapping("/")
    public String index(HttpServletRequest request) {




        return "index";
    }
}

