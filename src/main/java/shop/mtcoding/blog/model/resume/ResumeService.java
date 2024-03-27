package shop.mtcoding.blog.model.resume;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.model.apply.Apply;
import shop.mtcoding.blog.model.apply.ApplyJPARepository;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillJPARepository;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserJPARepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJPARepository resumeJPARepo;
    private final ApplyJPARepository applyJPARepo;
    private final SkillJPARepository skillJPARepo;
    private final UserJPARepository userJPARepository;
    private final HttpSession session;

    public List<ResumeResponse.ResumeApplyDTO> findAllResumeJoinApplyByUserIdAndJobsId(Integer userId, Integer jobsId) {
        List<Resume> resumeList = resumeJPARepo.findAllByUserId(userId);

        List<ResumeResponse.ResumeApplyDTO> resumeApplyDTOList = new ArrayList<>();

        for (int i = 0; i < resumeList.size(); i++) {
            Apply apply = applyJPARepo.findByResumeIdAndJobsId(resumeList.get(i).getId(), jobsId)
                    .orElse(Apply.builder().isPass("1").build());

            resumeApplyDTOList.add(ResumeResponse.ResumeApplyDTO.builder()
                    .apply(apply)
                    .resume(resumeList.get(i)).build());
            System.out.println(resumeApplyDTOList.get(i).getIsPass());
            System.out.println(resumeApplyDTOList.get(i).getIsApply());
        }
        return resumeApplyDTOList;
    }

    //이력서 뿌리기
    public List<ResumeResponse.ResumeDTO> findAll() {
       List<Resume> resumes = resumeJPARepo.findAll();

       return resumes.stream().map(resume -> resume.toDTO()).collect(Collectors.toList());
    }

    //이력서 신청
    @Transactional
    public void save(ResumeRequest.SaveDTO saveDTO){
        //1. 인증처리 : 유저가 세션을가지고있는지 로그인상태 확인
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null){
            throw new Exception401("로그인이 필요한 서비스입니다");
        }
        //2. 이력서 작성
        Resume resume = saveDTO.toEntity(sessionUser);
        resumeJPARepo.save(resume);
        System.out.println("============================" + resume.getId());

        // 3. 스킬 작성
        saveDTO.getSkill().stream()
                .map((skilName)->{
                    return Skill.builder()
                            .name(skilName)
                            .role(sessionUser.getRole())
                            .resume(resume)
                            .build();
                })
                .forEach((skill) -> {
                    skillJPARepo.save(skill);
                });
    }

    //이력서 삭제
    @Transactional
    public void delete(Integer boardId){
        //1. 인증처리
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser.getId() == null){
            throw new Exception401("서비스가 필요한 서비스입니다.");
        }
        Resume resume = resumeJPARepo.findById(boardId)
                .orElseThrow(() -> new Exception404("해당 게시글을 찾을 수 없습니다"));
        //2.권한처리
        if (sessionUser.getId() != resume.getUser().getId()){
            throw new Exception403("해당 게시글을 삭제할 권한이 없습니다");
        }

        //3. 삭제하기
        resumeJPARepo.delete(resume);
    }

 }
