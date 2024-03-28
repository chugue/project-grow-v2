package shop.mtcoding.blog.model.apply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.jobs.JobsJPARepository;
import shop.mtcoding.blog.model.jobs.JobsService;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.resume.ResumeJPARepository;

@RequiredArgsConstructor
@Service // IoC 에 등록된다.
public class ApplyService {
    private final ApplyJPARepository applyJPARepo;
    private final JobsJPARepository jobsJPARepo;
    private final ResumeJPARepository resumeJPARepo;

    public void JobsUserDTO (Integer jobsId) {

    }

    //이력서로 공고에 막 지원했을때 사용되는 메소드
    public void newApply(Integer jobsId, Integer resumeId) {
        Jobs jobs = jobsJPARepo.findById(jobsId).orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다."));
        Resume resume = resumeJPARepo.findById(resumeId).orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다."));

        // isPass = 2는 지원한 상태
        Apply apply = Apply.builder().jobs(jobs).resume(resume).isPass("2").build();
        applyJPARepo.save(apply);
    }
}
