package shop.mtcoding.blog.model.comp;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.model.apply.Apply;
import shop.mtcoding.blog.model.apply.ApplyJPARepository;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.jobs.JobsJPARepository;
import shop.mtcoding.blog.model.jobs.JobsRequest;
import shop.mtcoding.blog.model.jobs.JobsResponse;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.resume.ResumeJPARepository;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillJPARepository;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserJPARepository;
import shop.mtcoding.blog.model.user.UserRequest;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CompService {
    private final UserJPARepository userJPARepo;
    private final CompJPARepository compJPARepo;
    private final ResumeJPARepository resumeJPARepo;
    private final SkillJPARepository skillJPARepo;
    private final JobsJPARepository jobsJPARepo;
    private final ApplyJPARepository applyJPARepo;
//    private final HttpSession session;

    // 기업 회원가입
    @Transactional
    public User join(CompRequest.CompJoinDTO reqDTO) {
        compJPARepo.save(reqDTO.toEntity(2));

        // 전에거에 있던 이메일 찾아서 그걸로 세션저장해서 회원가입 직후 바로 로그인 되는거 구현하려고 만듬
        User comp = compJPARepo.findByEmail(reqDTO.getEmail());
        return comp;
    }

    public List<JobsResponse.ApplyResumeListDTO> findAllByJobsId(Integer jobsId) {
        List<Apply> applyList = applyJPARepo.findAllByJobsId(jobsId);
        List<JobsResponse.ApplyResumeListDTO> listDTOS = new ArrayList<>();

        for (int i = 0; i < applyList.size(); i++) {
            List<Skill> skillList = skillJPARepo.findAllByJoinResumeId(applyList.get(i).getResume().getId());
            listDTOS.add(JobsResponse.ApplyResumeListDTO.builder()
                    .resume(applyList.get(i).getResume())
                    .myName(applyList.get(i).getResume().getUser().getMyName())
                    .jobs(applyList.get(i).getJobs())
                    .isPass(applyList.get(i).getIsPass())
                    .skills(skillList)
                    .build());
        }

        return listDTOS;
    }

    public List<JobsResponse.ApplyJobsListDTO> findAllByUserId(User sessionComp) {
        List<Jobs> jobsList = jobsJPARepo.findAllByUserId(sessionComp.getId());
        List<JobsResponse.ApplyJobsListDTO> listDTOS = new ArrayList<>();

        for (int i = 0; i < jobsList.size(); i++) {
            List<Skill> skillList = skillJPARepo.findAllByJoinJobsId(jobsList.get(i).getId());
            listDTOS.add(JobsResponse.ApplyJobsListDTO.builder()
                    .jobs(jobsList.get(i))
                    .userId(sessionComp.getId())
                    .skills(skillList)
                    .build());
        }

        return listDTOS;
    }

    //기업 로그인하면 보여줄 채용 공고
    public List<CompResponse.JobsSkillDTO> jobsList() {
        List<Jobs> jobsList = jobsJPARepo.findAll();
        List<CompResponse.JobsSkillDTO> jobsSkillList = new ArrayList<>();

        for (int i = 0; i < jobsList.size(); i++) {
            User user = userJPARepo.findById(jobsList.get(i).getUser().getId())
                    .orElseThrow(() -> new Exception404("정보를 찾을 수 없습니다."));

            List<Skill> skillList = skillJPARepo.findAllByJobsId(jobsList.get(i).getId());
            jobsSkillList.add(CompResponse.JobsSkillDTO.builder()
                    .jobs(jobsList.get(i))
                    .user(user)
                    .skillList(skillList)
                    .build());
        }
        return jobsSkillList;

    }


    // 기업 로그인하면 보여줄 이력서 목록들
    public List<CompResponse.ResumeUserSkillDTO> findAllRusList() {
        List<Resume> resumeList = resumeJPARepo.findAll();
        List<CompResponse.ResumeUserSkillDTO> rusList = new ArrayList<>();

        for (int i = 0; i < resumeList.size(); i++) {
            User user = userJPARepo.findById(resumeList.get(i).getUser().getId())
                    .orElseThrow(() -> new Exception404("정보를 찾을 수 없습니다."));

            List<Skill> skillList = skillJPARepo.findAllByResumeId(resumeList.get(i).getId());

            rusList.add(CompResponse.ResumeUserSkillDTO.builder()
                    .resume(resumeList.get(i))
                    .user(user)
                    .skillList(skillList)
                    .build());
        }

        return rusList;
    }

    @Transactional
    public User updateById(User sessionUser, UserRequest.UpdateDTO requestDTO) {
        System.out.println(requestDTO);
        User user = compJPARepo.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception401("로그인이 필요한 서비스입니다."));

        user.update(requestDTO);
//        user.setPassword(requestDTO.getPassword());
//        user.setMyName(requestDTO.getMyName());
//        user.setBirth(requestDTO.getBirth());
//        user.setPhone(requestDTO.getPhone());
//        user.setAddress(requestDTO.getAddress());

        return user;
    }

    //유저 회원 정보 업데이트용 조회
    public User findById(Integer sessionUserId) {
        User user = compJPARepo.findById(sessionUserId)
                .orElseThrow(() -> new Exception401("로그인이 필요한 서비스입니다."));
        return user;

    }


}
