package shop.mtcoding.blog.model.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.model.apply.Apply;
import shop.mtcoding.blog.model.apply.ApplyJPARepository;
import shop.mtcoding.blog.model.user.UserJPARepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJPARepository resumeJPARepo;
    private final ApplyJPARepository applyJPARepo;


    public List<ResumeResponse.ResumeApplyDTO> findAllResumeJoinApplyByUserIdAndJobsId(Integer userId, Integer jobsId) {
        List<Resume> resumeList = resumeJPARepo.findAllByUserId(userId);

        List<ResumeResponse.ResumeApplyDTO> resumeApplyDTOList = new ArrayList<>();

        for (int i = 0; i < resumeList.size(); i++) {
            Apply apply = applyJPARepo.findByResumeIdAndJobsId(resumeList.get(i).getId(), jobsId)
                    .orElseThrow(() -> new Exception404("지원 내역을 찾을 수 없습니다."));

            resumeApplyDTOList.add(ResumeResponse.ResumeApplyDTO.builder()
                    .apply(apply)
                    .resume(resumeList.get(i)).build());
            System.out.println(resumeApplyDTOList.get(i).getIsPass());
            System.out.println(resumeApplyDTOList.get(i).getIsApply());
        }
        return resumeApplyDTOList;
    }



    public List<ResumeResponse.ResumeDTO> findAll() {
       List<Resume> resumes = resumeJPARepo.findAll();

       return resumes.stream().map(resume -> resume.toDTO()).collect(Collectors.toList());
    }
}
