package shop.mtcoding.blog.model.comp;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.model.apply.Apply;
import shop.mtcoding.blog.model.apply.ApplyJPARepository;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.jobs.JobsJPARepository;
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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CompService {
    private final UserJPARepository userJPARepo;
    private final CompJPARepository compJPARepo;
    private final ResumeJPARepository resumeJPARepo;
    private final SkillJPARepository skillJPARepo;
    private final JobsJPARepository jobsJPARepo;
    private final ApplyJPARepository applyJPARepo;


    public List<CompResponse.RusaDTO> findApplicants(Integer jobsId) {
        //공고에 지원한 이력서를 전부 찾는데, 그 중 지원안한 상태는 제외해서 조회
        List<Apply> applyList = applyJPARepo.findAllByJidAn1(jobsId);
        // DTO를 받을 그릇 준비
        List<CompResponse.RusaDTO> rusaDTOList = new ArrayList<>();

        /*
        * 원래 for문으로 조회해서 주입하였으나 그 방식은 서버에 부담이 크다.
        * 쿼리를 for문으로 날린다는게...
        */
        applyList.stream().map(apply -> {
            // apply에 Resume가 매핑되어있어서 가져옴
            Resume resume = apply.getResume();
            /*
            * 근데 apply안에 resume안에 user는 잘 가져올수있을까??
            * 그래서 sout찍어보기 테스트
            */
            User user = apply.getResume().getUser();
            System.out.println(resume.getUser().toString());

            return rusaDTOList.add(CompResponse.RusaDTO.builder()
                    .user(user).resume(resume).apply(apply).build());
        }).collect(Collectors.toList());

        for (int i = 0; i < rusaDTOList.size(); i++) {
            rusaDTOList.get(i).setId(i+1);
        }
        return rusaDTOList;
    }

    // 기업 회원가입
    @Transactional
    public User join(CompRequest.CompJoinDTO reqDTO) {
        compJPARepo.save(reqDTO.toEntity(2));

        // 전에거에 있던 이메일 찾아서 그걸로 세션저장해서 회원가입 직후 바로 로그인 되는거 구현하려고 만듬
        User comp = compJPARepo.findByEmail(reqDTO.getEmail());
        return comp;
    }

    public List<JobsResponse.JobsListDTO> findAllJobsId(Integer id) {

        List<Jobs> jobsList = jobsJPARepo.findAllByJobsId(id);
        List<JobsResponse.JobsListDTO> listDTOS = new ArrayList<>();

        jobsList.stream().map(jobs -> {
            User user = userJPARepo.findById(jobs.getUser().getId())
                    .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다."));
            List<Skill> skillList = skillJPARepo.findAllByJobsId(jobs.getId());
            return listDTOS.add(JobsResponse.JobsListDTO.builder()
                    .jobs(jobs)
                    .user(user)
                    .skills(skillList).build());
        }).collect(Collectors.toList());

        // 조회된 DTO에 목록 번호 붙이기
        for (int i = 0; i < listDTOS.size(); i++) {
            listDTOS.get(i).setId(i + 1);
        }

        return listDTOS;
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
