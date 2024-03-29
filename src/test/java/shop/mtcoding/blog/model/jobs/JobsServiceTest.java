package shop.mtcoding.blog.model.jobs;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.model.apply.Apply;
import shop.mtcoding.blog.model.apply.ApplyJPARepository;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.resume.ResumeJPARepository;
import shop.mtcoding.blog.model.resume.ResumeResponse;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillJPARepository;
import shop.mtcoding.blog.model.skill.SkillService;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserJPARepository;

import java.util.ArrayList;
import java.util.List;

@Import(JobsService.class)
@DataJpaTest
public class JobsServiceTest {
    @Autowired
    private JobsJPARepository jobsRepo;
    @Autowired
    private UserJPARepository userRepo;
    @Autowired
    private SkillJPARepository skillRepo;
    @Autowired
    private ResumeJPARepository resumeJPARepo;
    @Autowired
    private ApplyJPARepository applyJPARepo;


    @Test
    public void test_test() {


        int userId = 1;
        int jobsId = 3;
        List<Resume> resumeList = resumeJPARepo.findAllByUserId(userId);

        List<ResumeResponse.ResumeApplyDTO> resumeApplyDTOList = new ArrayList<>();

        for (int i = 0; i < resumeList.size(); i++) {
            Apply apply = applyJPARepo.findByResumeIdAndJobsId(resumeList.get(i).getId(), jobsId)
                    .orElse(Apply.builder().isPass("1").build());

            resumeApplyDTOList.add(ResumeResponse.ResumeApplyDTO.builder()
                    .apply(apply)
                    .resume(resumeList.get(i)).build());


            System.out.println("이력서 번호 : " + resumeList.get(i).getId()); //  이력서 번호
            System.out.println("사용자 번호 : " +resumeList.get(i).getUser().getId()); // 이력서 주인  id
            System.out.println("공고 번호 : " + jobsId); // 이력서 주인  id
            System.out.println(resumeApplyDTOList.get(i).getIsPass());
            System.out.println(resumeApplyDTOList.get(i).getIsApply());
        }
    }

    @Test
    public void detailDTO_test(){
        // given
        int jobsId = 1;
        // when
        Jobs jobs = jobsRepo.findById(jobsId)
                .orElseThrow(() -> new Exception404("해당 공고를 찾을 수 없습니다."));
        User user = userRepo.findById(jobs.getUser().getId())
                .orElseThrow(() -> new Exception404("해당 사용자를 찾을 수 없습니다."));
        List<Skill> skillList = skillRepo.findAllById(jobs.getId());

        JobsResponse.DetailDTO detailDTO = new JobsResponse.DetailDTO(jobs, user, skillList);
        // then
        System.out.println(detailDTO.toString());
    }

    @Test
    public void listDTOS_test(){
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
    }
}
