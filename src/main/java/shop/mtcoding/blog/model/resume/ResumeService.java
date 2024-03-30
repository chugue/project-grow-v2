package shop.mtcoding.blog.model.resume;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.model.apply.Apply;
import shop.mtcoding.blog.model.apply.ApplyJPARepository;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillJPARepository;
import shop.mtcoding.blog.model.skill.SkillResponse;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserJPARepository;
import shop.mtcoding.blog.model.user.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJPARepository resumeJPARepo;
    private final ApplyJPARepository applyJPARepo;
    private final SkillJPARepository skillJPARepo;
    private final SkillJPARepository skillRepo;
    private final HttpSession session;


    //이력서 상세보기
    public ResumeResponse.DetailDTO resumeDetail(Integer resumeId, Integer jobsId, User sessionUser, User sessionComp) {
        Resume resume = resumeJPARepo.findByIdJoinUser(resumeId);

        boolean isOwner = resume.getUser().equals(sessionUser);
        resume.setOwner(isOwner);

        List<Skill> skills = skillJPARepo.findAllByResumeId(resume.getId());
        Apply apply = applyJPARepo.findByResumeIdAndJobsId(resumeId, jobsId)
                .orElseThrow(() -> new Exception400("잘못된 요청입니다."));
        if (sessionUser != null) {
            ResumeResponse.DetailDTO resumeDetailDTO = new ResumeResponse.DetailDTO(resume , apply.getIsPass(), resume.getUser(), sessionUser.getRole(), skills);

            return resumeDetailDTO;
        } else if (sessionComp != null) {
            ResumeResponse.DetailDTO resumeDetailDTO = new ResumeResponse.DetailDTO(resume, apply.getIsPass(), resume.getUser(), sessionComp.getRole(), skills);

            return resumeDetailDTO;
        }

        return null;
    }

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

    // 이력서 수정
    public ResumeResponse.UpdateDTO updateForm(Integer id) {
        Resume resume = resumeJPARepo.findById(id)
                .orElseThrow(() -> new Exception404("이력서를 찾을 수 없습니다"));

        List<Skill> skill = skillRepo.findAllByResumeId(id);
        ResumeResponse.UpdateDTO reqDTO = ResumeResponse.UpdateDTO.builder()
                .title(resume.getTitle())
                .area(resume.getArea())
                .edu(resume.getEdu())
                .career(resume.getCareer())
                .introduce(resume.getIntroduce())
                .portLink(resume.getPortLink())
                .skillChecked(new SkillResponse.SkillCheckedDTO(skill)).build();
        return reqDTO;
    }


    @Transactional
    public void update(Integer id, Integer sessionUserId, ResumeRequest.UpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        // 1. 조회 및 예외처리
        // 주어진 resumeId로 이력서를 찾습니다.
        Resume resume = resumeJPARepo.findById(id)
                .orElseThrow(() -> new Exception404("해당 이력서를 찾을 수 없습니다"));
        // 2. 권한 처리
        if (sessionUserId != resume.getUser().getId()) {
            throw new Exception403("이력서를 수정할 권한이 없습니다");
        }
        // 3. 이력서 수정하기
        resume.setResumeUpdate(reqDTO); // 요청으로부터 받은 정보로 이력서를 업데이트합니다.

        skillRepo.deleteByresumeId(id);

        // 스킬뿌리기
        reqDTO.getSkill().stream().map((skill) -> {
            return Skill.builder()
                    .name(skill)
                    .role(sessionUser.getRole())
                    .resume(resume)
                    .build();
        }).forEach(skill -> {
            // 반복문으로 스킬 돌면서 뿌림
            skillRepo.save(skill);
        });

        System.out.println("수정된 데이터 : " + reqDTO);
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
    public void delete(Integer boardId) {
        //1. 인증처리
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser.getId() == null) {
            throw new Exception401("서비스가 필요한 서비스입니다.");
        }
        Resume resume = resumeJPARepo.findById(boardId)
                .orElseThrow(() -> new Exception404("해당 게시글을 찾을 수 없습니다"));
        //2.권한처리
        if (sessionUser.getId() != resume.getUser().getId()) {
            throw new Exception403("해당 게시글을 삭제할 권한이 없습니다");
        }

        //3. 삭제하기
        resumeJPARepo.deleteById(resume.getId());
    }

}
