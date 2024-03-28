package shop.mtcoding.blog.model.jobs;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.model.resume.ResumeResponse;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillJPARepository;
import shop.mtcoding.blog.model.skill.SkillService;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserJPARepository;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JobsService {
    private final JobsJPARepository jobsRepo;
    private final UserJPARepository userRepo;
    private final SkillJPARepository skillRepo;
    private final HttpSession session;

    public JobsResponse.DetailDTO DetailDTO (Integer jobsId){
        Jobs jobs = jobsRepo.findById(jobsId)
                .orElseThrow(() -> new Exception404("해당 공고를 찾을 수 없습니다."));
        User user = userRepo.findById(jobs.getUser().getId())
                .orElseThrow(() -> new Exception404("해당 사용자를 찾을 수 없습니다."));
        List<Skill> skillList = skillRepo.findAllById(jobs.getId());

        JobsResponse.DetailDTO detailDTO = new JobsResponse.DetailDTO(jobs, user, skillList);
        return detailDTO;
    }

    public List<JobsResponse.ListDTO> listDTOS() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Jobs> jobsList = jobsRepo.findAll(sort);

        List<JobsResponse.ListDTO> listDTOS = new ArrayList<>();

        for (int i = 0; i < jobsList.size(); i++) {
            User user = userRepo.findById(jobsList.get(i).getUser().getId())
                    .orElseThrow(() -> new Exception404("사용자를 찾을 수 없습니다."));

            List<Skill> skillList = skillRepo.findAllById(jobsList.get(i).getId());

            listDTOS.add(JobsResponse.ListDTO.builder()
                    .jobs(jobsList.get(i))
                    .user(user)
                    .skills(skillList).build());
        }
        return listDTOS;
    }

    @Transactional
    public void save(JobsRequest.JobWriterDTO reqDTO) {

        // 인증체크
        User sessionComp = (User) session.getAttribute("sessionComp");
        if (sessionComp == null) {
            throw new Exception401("로그인이 필요한 서비스입니다");
        }

        // 공고작성 하기
        Jobs jobs = reqDTO.toEntity(sessionComp);
        jobsRepo.save(jobs);

        // 스킬뿌리기
        reqDTO.getSkill().stream().map((skill) -> {
            return Skill.builder()
                    .name(skill)
                    .role(sessionComp.getRole())
                    .jobs(jobs)
                    .build();
        }).forEach(skill -> {
            // 반복문으로 스킬 돌면서 뿌림
            skillRepo.save(skill);
        });
    }
}

