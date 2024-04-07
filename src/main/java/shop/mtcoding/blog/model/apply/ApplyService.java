package shop.mtcoding.blog.model.apply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.jobs.JobsJPARepository;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.resume.ResumeJPARepository;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserJPARepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service // IoC 에 등록된다.
public class ApplyService {
    private final ApplyJPARepository applyJPARepo;
    private final JobsJPARepository jobsJPARepo;
    private final ResumeJPARepository resumeJPARepo;
    private final UserJPARepository userJPARepo;

    @Transactional
    public void cancel (Integer jobsId, Integer resumeId) {
        Apply apply = applyJPARepo.findByJidRid(jobsId, resumeId)
                .orElseThrow(() -> new Exception404("정보를 찾을 수 없습니다."));
        applyJPARepo.delete(apply);
    }

    //이력서로 공고에 막 지원했을때 사용되는 메소드
    public void newApply(Integer jobsId, Integer resumeId) {
        Jobs jobs = jobsJPARepo.findById(jobsId).orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다."));
        Resume resume = resumeJPARepo.findById(resumeId).orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다."));

        // isPass = 2는 지원한 상태
        Apply apply = Apply.builder().jobs(jobs).resume(resume).isPass("2").build();
        applyJPARepo.save(apply);
    }


    // 신청테이블 뿌리기
    public ApplyResponse.stateViewDTO findAll(Integer id){

        //1. 지원테이블 뿌리기 (뿌리기 완성)
        //2. sessionUser만 지원한 지원테이블 뿌리기(뿌리기 완성)
        //3. 지원한 테이블 카운트(완성)

        User sessionUser = userJPARepo.findById(id)
                .orElseThrow(() -> new Exception401("로그인이 필요한 서비스 입니다"));
        ApplyResponse.stateViewDTO stateDTO = new ApplyResponse.stateViewDTO();

        List<Apply> applies =  applyJPARepo.findAll();
        List<Resume> resumes = resumeJPARepo.findAll();

        //신청 이력석 카운트
        Integer resumeCount = Math.toIntExact(resumes.stream()
                .filter(resume -> resume.getUser().getId().equals(sessionUser.getId()))
                .count());

        //대기중 카운트
        Integer waitCount = Math.toIntExact(applies.stream()
                .filter(apply -> apply.getResume().getUser().getId().equals(sessionUser.getId()))
                .filter(apply -> apply.getIsPass().equals("2"))
                .count());

        //결과 카운트
        Integer resultCount = Math.toIntExact(applies.stream()
                .filter(apply -> apply.getResume().getUser().getId().equals(sessionUser.getId()))
                .filter(apply -> "3".equals(apply.getIsPass()) || "4".equals(apply.getIsPass()))
                .count());
        
        stateDTO.setResumeCount(resumeCount);
        stateDTO.setWaitCount(waitCount);
        stateDTO.setResultCount(resultCount);



        return stateDTO;
    }

    @Transactional
    public void pass(Integer resumeId, Integer jobsId) {
        Apply apply = applyJPARepo.findByResumeIdAndJobsId(resumeId, jobsId)
                .orElseThrow(() -> new Exception400("잘못된 요청입니다."));

        apply.setIsPass("3");
    }

    @Transactional
    public void fail(Integer resumeId, Integer jobsId) {
        Apply apply = applyJPARepo.findByResumeIdAndJobsId(resumeId, jobsId)
                .orElseThrow(() -> new Exception400("잘못된 요청입니다."));

        apply.setIsPass("4");
    }
}
