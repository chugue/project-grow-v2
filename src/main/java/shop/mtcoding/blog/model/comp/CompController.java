package shop.mtcoding.blog.model.comp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog.dto.scrap.ScrapResponse;
import shop.mtcoding.blog.model.apply.ApplyRepository;
import shop.mtcoding.blog.model.apply.ApplyResponse;
import shop.mtcoding.blog.model.comp.CompRepository;
import shop.mtcoding.blog.model.comp.CompRequest;
import shop.mtcoding.blog.model.offer.OfferRepository;
import shop.mtcoding.blog.model.offer.OfferRequest;
import shop.mtcoding.blog.model.jobs.JobResponse;

import shop.mtcoding.blog.model.offer.OfferResponse;

import shop.mtcoding.blog.page.Page;
import shop.mtcoding.blog.page.Paging;

import shop.mtcoding.blog.model.apply.ApplyRequest;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.resume.ResumeRepository;
import shop.mtcoding.blog.model.resume.ResumeRequest;
import shop.mtcoding.blog.model.resume.ResumeResponse;
import shop.mtcoding.blog.model.scrap.ScrapRepository;
import shop.mtcoding.blog.model.jobs.JobsRepository;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillRepository;
import shop.mtcoding.blog.model.skill.SkillRequest;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserRepository;
import shop.mtcoding.blog.model.user.UserRequest;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompController {
    private final UserRepository userRepository;
    private final CompRepository compRepository;
    private final JobsRepository jobsRepository;
    private final ScrapRepository scrapRepository;
    private final ApplyRepository applyRepository;
    private final HttpSession session;
    private final SkillRepository skillRepository;
    private final ResumeRepository resumeRepository;
    private final OfferRepository offerRepository;
    private final Paging paging;


    @GetMapping("/comp/{id}/compResumeDetail")
    public String compResumeDetail(@PathVariable Integer id,@RequestParam("jobsId") Integer jobsId, HttpServletRequest request) {
        User sessionComp = (User) session.getAttribute("sessionComp");

        Object[] resume = resumeRepository.findByResumeId(id);

        ResumeRequest.resumeDetailDTO checked = ResumeRequest.resumeDetailDTO.builder()
                .id((Integer) resume[0])
                .myName(String.valueOf(resume[1]))
                .address(String.valueOf(resume[2]))
                .phone(String.valueOf(resume[3]))
                .email(String.valueOf(resume[4]))
                .birth(String.valueOf(resume[5]))
                .edu(String.valueOf(resume[6]))
                .career(String.valueOf(resume[7]))
                .introduce(String.valueOf(resume[8]))
                .title(String.valueOf(resume[9]))
                .portLink(String.valueOf(resume[10]))
                .area(String.valueOf(resume[11]))
                .build();
        request.setAttribute("resumeObject", checked);
        List<SkillRequest.ApplyskillDTO> skillList = skillRepository.resumeSkill(id);
        request.setAttribute("skillList", skillList);



        System.out.println(jobsId);
        Resume resumeDTO = resumeRepository.findById(id);
        ApplyRequest.ApplyResumeJobsDTO requestDTO = applyRepository.findByIdResumeJobs(id, jobsId);

        request.setAttribute("resume", resumeDTO);

        if (sessionComp == null) {
            ScrapResponse.DetailDTO scrapDetailDTO = scrapRepository.findScrap(id);
            request.setAttribute("scrap", scrapDetailDTO);
        } else {
            ScrapResponse.DetailDTO scrapDetailDTO = scrapRepository.findScrap(id, sessionComp.getId());
            request.setAttribute("scrap", scrapDetailDTO);
        }

        return "/comp/compResumeDetail";
    }

    @PostMapping("/comp/{id}/update")
    public String updateForm(@PathVariable Integer id, CompRequest.UpdateDTO requestDTO) {
        compRepository.updateById(id, requestDTO);

        return "redirect:/comp/"+ id +"/comphome";
    }
  
    @GetMapping("/comp/compIndex")
    public String compIndex(HttpServletRequest request, @RequestParam("role") int role, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "1") String page) {
        int currentPage = Integer.parseInt(page);
        int totalPosts = resumeRepository.findAllWithUserV2().size();
        boolean lastPage = paging.complastPage(currentPage, totalPosts);
        boolean firstPage = paging.compfirstPage(currentPage);
        int totalPages = paging.compTotalPages(totalPosts);
        List<Page> pageActive = new ArrayList<>();


        // 페이지 네이션에 현재페이지 active기능 넣기
        for (int i = 1; i <= totalPages; i++) {
            Page page1 = new Page();
            page1.setNumber(i);
            page1.setActive(currentPage == i);
            pageActive.add(page1);
        }

        // 한 페이지에 5개씩 출력 - 10개중 현재페이지가 1이면 10부터 6까지, 현재 페이지가 2면 5부터 1까지


        if (keyword.isBlank()) { //isBlank면 검색 안함
            // 직접 조인된 쿼리 결과를 받아오기 위한 DTO, 현재 List<Skill>는 비워져있다.
            List<ResumeResponse.ResumeUserDTO> pageList = paging.compShowPagesV2(currentPage, resumeRepository.findAllWithUserV2());

            // 이 코드가 한 pageList에 Skill을 객체로 저장하기 위해서 쓰는 코드
            pageList.forEach(dto -> {
                List<Skill> skillList = skillRepository.compfindAllV2(dto.getId());
                dto.setSkillList(skillList);
            });
            request.setAttribute("keyword", keyword);
            request.setAttribute("pages", pageActive);
            request.setAttribute("firstPage", firstPage);
            request.setAttribute("lastPage", lastPage);
            request.setAttribute("pageList", pageList);
            request.setAttribute("prevPage", Math.max(1, currentPage - 1));
            request.setAttribute("nextPage", Math.min(totalPages, currentPage + 1));
            if (role == 1){
                return "/user/loginForm";
            }
            return "comp/compIndex";

        } else {    //검색하면 키워드를 던져줌
            List<ResumeResponse.ResumeUserDTO> pageList = paging.compShowPagesV2(currentPage, resumeRepository.findAllWithUserV2(keyword));

            pageList.forEach(dto -> {
                List<Skill> skillList = skillRepository.compfindAllV2(dto.getId());
                dto.setSkillList(skillList);
            });
            request.setAttribute("keyword", keyword);
            request.setAttribute("pages", pageActive);
            request.setAttribute("firstPage", firstPage);
            request.setAttribute("lastPage", lastPage);
            request.setAttribute("pageList", pageList);
            request.setAttribute("prevPage", Math.max(1, currentPage - 1));
            request.setAttribute("nextPage", Math.min(totalPages, currentPage + 1));
            if (role == 1){
                return "/user/loginForm";
            }
            return "comp/compIndex";
        }
    }


//    @GetMapping("/comp/{id}/apply")
//    public String apply(OfferRequest.CompOfterDTO compOfterDTO, HttpServletRequest request) {
//        User sessionUser = (User) session.getAttribute("sessionComp");
//        request.setAttribute("id", sessionUser.getId());
//
//        return "/comp/apply";
//    }
//


    @GetMapping("/comp/{id}/comphome")
    public String compHome(@PathVariable Integer id, @RequestParam(required = false, defaultValue = "0") Integer jobsId, HttpServletRequest request) {

        User user = (User) session.getAttribute("sessionComp");
        System.out.println(user);

        // 내 공고리스트에 지원한 이력서 리스트

        List<ApplyResponse.ApplyByJobsDTO> applyList = applyRepository.findAllByJobsId(jobsId);



//        applyList.forEach(dto -> {
//            dto.setSkillList(applyRepository.findAllSkillById(dto.getId()));
//        }); 람다식 ->

        for (int i = 0; i < applyList.size(); i++) {
            ApplyResponse.ApplyByJobsDTO dto = applyList.get(i);
            dto.setSkillList(applyRepository.findAllSkillById(dto.getId()));
        }


        request.setAttribute("compResumeList", applyList);

//--------------------------------------------------------------------------------------------------------
        // 기업이 올린 공고 리스트
        List<JobResponse.JobListByUserId> jobList = jobsRepository.findAllByUserId(id);

//        System.out.println(jobList);
//        jobList.forEach(job ->{
//            job.setSkillList(jobsRepository.findAllSkillById(job.getId());
//        });
//

        for (int i = 0; i < jobList.size(); i++) {
            JobResponse.JobListByUserId dto = jobList.get(i);

            dto.setSkillList(jobsRepository.findAllSkillById(dto.getId()));
        }


        request.setAttribute("jobList", jobList);


//        session.setAttribute("jobsId", jobsId);


//        System.out.println(jobList);

//
//        List<Object[]> jobsList = jobsRepository.findAllByUserId(id);
//        List<CompRequest.JobsViewDTO> viewDTOList = new ArrayList<>();

//        Integer nextNumber = 1;
//        CompRequest.JobsViewDTO prevViewDTO = new CompRequest.JobsViewDTO();
//
//        for (int i = 0; i < jobsList.size(); i++) {
//
//            Object[] job = jobsList.get(i);
//            if(prevViewDTO.getId() == job[0]){
//                // 스킬 색깔 생성
//                String color = "";
//                if (((String)job[6]).equals("jQuery")){
//                    color = "badge bg-primary";
//
//                }
//                else if(((String)job[6]).equals("javaScript")){
//                    color = "badge bg-secondary";
//                }
//                else if(((String)job[6]).equals("Spring")){
//                    color = "badge bg-success";
//                }
//                else if(((String)job[6]).equals("HTML/CSS")){
//                    color = "badge bg-danger";
//                }
//                else if(((String)job[6]).equals("JSP")){
//                    color = "badge bg-warning";
//                }
//                else if(((String)job[6]).equals("java")){
//                    color = "badge bg-info";
//                }
//                else if(((String)job[6]).equals("React")){
//                    color = "badge bg-dark";
//                }
//
//                SkillRequest.JobSkillDTO skillDTO = SkillRequest.JobSkillDTO.builder().name((String) job[6]).color(color).build();
//
//                // 이전에 있던 viewDOT.skillList에 add
//                prevViewDTO.getSkillList().add(skillDTO);
//
//            }else{
//                // 스킬 리스트 생성
//                List<SkillRequest.JobSkillDTO> skillList = new ArrayList<>();
//
//                String color = "";
//                if (((String)job[6]).equals("jQuery")){
//                    color = "badge bg-primary";
//
//                }
//                else if(((String)job[6]).equals("javaScript")){
//                    color = "badge bg-secondary";
//                }
//                else if(((String)job[6]).equals("Spring")){
//                    color = "badge bg-success";
//                }
//                else if(((String)job[6]).equals("HTML/CSS")){
//                    color = "badge bg-danger";
//                }
//                else if(((String)job[6]).equals("JSP")){
//                    color = "badge bg-warning";
//                }
//                else if(((String)job[6]).equals("java")){
//                    color = "badge bg-info";
//                }
//                else if(((String)job[6]).equals("React")){
//                    color = "badge bg-dark";
//                }
//
//                // 스킬 이름 set
//                skillList.add(SkillRequest.JobSkillDTO.builder().name((String) job[6]).color(color).build());
//
//                // 새로운 DTO 생성
//                prevViewDTO = new CompRequest.JobsViewDTO();
//
//                prevViewDTO.setId((Integer) job[0]);
//                prevViewDTO.setUserId((Integer) job[1]);
//                prevViewDTO.setCompName((String) job[2]);
//                prevViewDTO.setTitle((String) job[3]);
//                prevViewDTO.setTask((String) job[4]);
//                prevViewDTO.setCareer((String) job[5]);
//                prevViewDTO.setSkillList(skillList);
//                prevViewDTO.setNumber(nextNumber++);
//                viewDTOList.add(prevViewDTO);
//            }
//
//         }

        return "/comp/comphome";
    }

    @GetMapping("/comp/{id}/apply")
    public String offer(@PathVariable Integer id,
                        @RequestParam(required = false, defaultValue = "0") Integer jobsId,
                        HttpServletRequest request) {

        User sessionComp = (User) session.getAttribute("sessionComp");
        request.setAttribute("userId", sessionComp.getId());
        List<ApplyResponse.ApplyByJobsDTO> applyList = applyRepository.findAllByJobsId(jobsId);

        for (int i = 0; i < applyList.size(); i++) {
            ApplyResponse.ApplyByJobsDTO dto = applyList.get(i);
            dto.setSkillList(applyRepository.findAllSkillById(dto.getId()));
        }
        request.setAttribute("applyList", applyList);


        List<OfferResponse.OfferListByUserId> jobList = offerRepository.findAllByUserId(id);

        for (int i = 0; i < jobList.size(); i++) {
            OfferResponse.OfferListByUserId dto = jobList.get(i);
            dto.setSkillList(offerRepository.findAllSkillById(dto.getId()));
        }
        request.setAttribute("jobList", jobList);

        return "/comp/apply";
    }

    @GetMapping("/comp/joinForm")
    public String compJoinForm() {

        return "/comp/joinForm";
    }

    @PostMapping("/comp/join")
    public String compJoin(@RequestParam("role") int role, UserRequest.UserAllDTO requestDTO) {
        requestDTO.setRole(role);
        userRepository.save(requestDTO);
        User user = userRepository.findByEmail(requestDTO.getEmail());
        session.setAttribute("sessionComp", user);
        return "redirect:/comp/compIndex";

    }

    @GetMapping("/comp/profileUpdateForm")
    public String profileUpdateForm(HttpServletRequest request) {
        User user = userRepository.findById(((User) session.getAttribute("sessionComp")).getId());
        request.setAttribute("imgFileName", user.getImgFileName());


        return "/comp/profileUpdateForm";
    }


    @GetMapping("/comp/readResume")
    public String readResume(HttpServletRequest request) {
        List<Object[]> resumeViewList = compRepository.findAllByUserId();
        List<CompRequest.ResumeViewDTO> viewDTOList = new ArrayList<>();// 담는리스트
        CompRequest.ResumeViewDTO prevViewDTO = new CompRequest.ResumeViewDTO(); // 담는 로우하나

        for (int i = 0; i < resumeViewList.size(); i++) {

            Object[] job = resumeViewList.get(i);
            if (prevViewDTO.getId() == job[0]) {
                // 스킬 색깔 생성
                String color = "";
                if (((String) job[7]).equals("jQuery")) {
                    color = "badge bg-primary";
                } else if (((String) job[7]).equals("javaScript")) {
                    color = "badge bg-secondary";
                } else if (((String) job[7]).equals("Spring")) {
                    color = "badge bg-success";
                } else if (((String) job[7]).equals("HTML/CSS")) {
                    color = "badge bg-danger";
                } else if (((String) job[7]).equals("JSP")) {
                    color = "badge bg-warning";
                } else if (((String) job[7]).equals("java")) {
                    color = "badge bg-info";
                } else if (((String) job[7]).equals("React")) {
                    color = "badge bg-dark";
                }

                SkillRequest.CompskillDTO skillDTO = SkillRequest.CompskillDTO.builder().name((String) job[7]).color(color).build();

                // 이전에 있던 viewDOT.skillList에 add
                prevViewDTO.getSkillList().add(skillDTO);
            } else {
                // 스킬 리스트 생성
                List<SkillRequest.CompskillDTO> skillList = new ArrayList<>();

                String color = "";
                if (((String) job[7]).equals("jQuery")) {
                    color = "badge bg-primary";
                } else if (((String) job[7]).equals("javaScript")) {
                    color = "badge bg-secondary";
                } else if (((String) job[7]).equals("Spring")) {
                    color = "badge bg-success";
                } else if (((String) job[7]).equals("HTML/CSS")) {
                    color = "badge bg-danger";
                } else if (((String) job[7]).equals("JSP")) {
                    color = "badge bg-warning";
                } else if (((String) job[7]).equals("java")) {
                    color = "badge bg-info";
                } else if (((String) job[7]).equals("React")) {
                    color = "badge bg-dark";
                }

                // 스킬 이름 set
                skillList.add(SkillRequest.CompskillDTO.builder().name((String) job[7]).color(color).build());

                // 새로운 DTO 생성
                prevViewDTO = new CompRequest.ResumeViewDTO();
                prevViewDTO.setId((Integer) job[0]);
                prevViewDTO.setUserId((Integer) job[1]);
                prevViewDTO.setMyName((String) job[2]);
                prevViewDTO.setTitle((String) job[3]);
                prevViewDTO.setEdu((String) job[4]);
                prevViewDTO.setCareer((String) job[5]);
                prevViewDTO.setArea((String) job[6]);
                prevViewDTO.setSkillList(skillList);
                viewDTOList.add(prevViewDTO);
            }

        }

        //테스트용 주석
        System.out.println(viewDTOList);

        session.setAttribute("readResumeList", viewDTOList);

        return "/comp/readResume";
    }


    @GetMapping("/comp/{id}/scrap")
    public String scrap(@PathVariable Integer id, HttpServletRequest request) {

        List<Resume> resumeList = scrapRepository.findByUserIdWithResume(id);

        request.setAttribute("resumeList", resumeList);

        request.setAttribute("compId", id);

        return "/comp/scrap";
    }
  
    @GetMapping("/comp/talent")
    public String talent(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionComp");
        request.setAttribute("id", sessionUser.getId());

        return "/comp/talent";
    }

    @GetMapping("/comp/{id}/updateForm")
    public String updateForm(@PathVariable int id, HttpServletRequest request) {

        User user = (User) userRepository.findById(id);

        request.setAttribute("user", user);
        return "/comp/updateForm";
    }

    @GetMapping("/comp/jobsInfo")
    public String jobsInfo() {

        return "/comp/jobsInfo";
    }


}
