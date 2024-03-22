package shop.mtcoding.blog.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import shop.mtcoding.blog.model.jobs.JobResponse;
import shop.mtcoding.blog.model.jobs.JobsRepository;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillRepository;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class MainController {
    private final JobsRepository jobsRepository;
    private final SkillRepository skillRepository;

    @GetMapping("/")
    public String index(HttpServletRequest request) {

        List<JobResponse.DTO> pageList = jobsRepository.findAllWithUserV2();

        pageList.forEach(dto -> {
            List<Skill> skillList = skillRepository.findAllV2(dto.getId());
            dto.setSkillList(skillList);
        });

        pageList.forEach(dto -> {
            List<Skill> skillList = skillRepository.findAllV2(dto.getId());
            dto.setSkillList(skillList);
        });

        request.setAttribute("pageList", pageList);
        return "index";
    }
}

