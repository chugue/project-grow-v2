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
    private final HttpSession session;
    private final UserJPARepository userRepo;


    //이력서 상세보기
    public Resume resumeDetail(Integer resumeId, User sessionUser) {
        Resume resume = resumeJPARepo.findByIdJoinUser(resumeId);

        List<ResumeResponse.DetailDTO> resumeDetailDTO = new ArrayList<>();
        for (int i = 0; i < resumeDetailDTO.size(); i++) {
            List<Skill> skills = skillJPARepo.findAllByResumeId(resume.getId());
            resumeDetailDTO.add(ResumeResponse.DetailDTO.builder()
                    .skillList(skills).build());
        }

        //이력서 상세보기 버튼 기업이면 합격이 보이고, 유저면 안보임
        Integer isUserOwnerNum = 1;
        Boolean isUserOwner = false;
        if (sessionUser != null) {
            if (sessionUser.getRole().equals(1) == isUserOwnerNum.equals(1)) {
                isUserOwner = true;
            }
        }
        resume.setResumeOwner(isUserOwner);

        return resume;
    }
    // 지원하기 수정 서비스 ===========================================================================
    public List<ResumeResponse.ResumeApplyDTO> findAllResumeJoinApplyByUserIdAndJobsId(Integer userId, Integer jobsId) {
        List<Resume> resumeList = resumeJPARepo.findAllByUserId(userId);

        List<ResumeResponse.ResumeApplyDTO> resumeApplyDTOList = new ArrayList<>();

//        boolean allEnabled = resumeApplyDTOList.stream()
//                .allMatch(resumeApplyDTO -> resumeApplyDTO.getIsApply().equals("1"));  // 1 가지고 있으면 true 아니면 false
        for (int i = 0; i < resumeList.size(); i++) {
            Apply apply = applyJPARepo.findByResumeIdAndJobsId(resumeList.get(i).getId(), jobsId)
                    .orElse(Apply.builder().isPass("1").build());

            resumeApplyDTOList.add(ResumeResponse.ResumeApplyDTO.builder()
                    .apply(apply)
                    .resume(resumeList.get(i)).build());

            System.out.println(resumeList.get(i).getId());
            System.out.println(resumeList.get(i).getUser().getId());
            System.out.println(resumeApplyDTOList.get(i).getIsPass());
//            System.out.println(resumeApplyDTOList.get(i).getIsApply());

        }


        boolean disabled = resumeList.stream()
                .anyMatch(resumeApplyDTO -> resumeApplyDTO.getApplyList().equals("1"));

        // isPass  = 지원을 했냐 안했냐 상태?
        // 맞으면 true 아니면 false

        return resumeApplyDTOList;
    }

    //이력서 뿌리기
    public List<ResumeResponse.ResumeDTO> findAll() {
        List<Resume> resumes = resumeJPARepo.findAll();

        return resumes.stream().map(resume -> resume.toDTO()).collect(Collectors.toList());
    }

    public Resume updateForm (int id) {
        Resume resume = resumeJPARepo.findById(id)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));
        return resume;
    }


    @Transactional
    public void update(int id, int sessionUserId, ResumeRequest.UpdateDTO reqDTO){
        // 1. 조회 및 예외처리
        Resume resume = resumeJPARepo.findById(id)
                .orElseThrow(() -> new Exception404("해당 이력서를 찾을 수 없습니다"));
        // 2. 권한 처리
        if (sessionUserId != resume.getUser().getId()) {
            throw new Exception403("이력서를 수정할 권한이 없습니다");
        }
        // 3. 이력서 수정하기
        resume.setResumeUpdate(reqDTO);

        // 3. 스킬 작성


        System.out.println("수정된 데이터 : " +reqDTO);
    } // 더티체킹


    //이력서 신청
    @Transactional
    public void save(ResumeRequest.SaveDTO saveDTO) {
        //1. 인증처리 : 유저가 세션을가지고있는지 로그인상태 확인
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new Exception401("로그인이 필요한 서비스입니다");
        }

        //2. 이력서 작성
        Resume resume = saveDTO.toEntity(sessionUser);
        resumeJPARepo.save(resume);
        System.out.println("------------------" + resume.getId());

        // 3. 스킬 작성



        saveDTO.getSkill().stream()
                .map((skillName) -> {
                    return Skill.builder()
                            .name(skillName)
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
        resumeJPARepo.deleteById(resume.getId());
    }

}
