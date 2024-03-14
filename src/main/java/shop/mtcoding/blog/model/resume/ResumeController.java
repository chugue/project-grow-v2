package shop.mtcoding.blog.model.resume;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.dto.scrap.ScrapResponse;

import shop.mtcoding.blog.model.jobs.JobResponse;
import shop.mtcoding.blog.model.jobs.JobsRepository;

import shop.mtcoding.blog.model.offer.OfferRepository;
import shop.mtcoding.blog.model.offer.OfferResponse;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.resume.ResumeRepository;
import shop.mtcoding.blog.model.resume.ResumeRequest;
import shop.mtcoding.blog.model.scrap.ScrapRepository;
import shop.mtcoding.blog.model.skill.SkillRepository;
import shop.mtcoding.blog.model.skill.SkillRequest;
import shop.mtcoding.blog.model.skill.SkillResponse;
import shop.mtcoding.blog.model.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ResumeController {

    private final HttpSession session;
    private final ResumeRepository resumeRepository;
    private final ScrapRepository scrapRepository;
    private final OfferRepository offerRepository;
    private final SkillRepository skillRepository;
    private final JobsRepository jobsRepository;


    @GetMapping("/resume/resumeDetail/{id}")
    public String resumeDetail (@PathVariable Integer id, HttpServletRequest request) {
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


        request.setAttribute("resume", checked);

        List<SkillRequest.ApplyskillDTO> skillList = skillRepository.resumeSkill(id);
        request.setAttribute("skillList", skillList);


        if(sessionComp == null) {
            ScrapResponse.DetailDTO scrapDetailDTO = scrapRepository.findScrap(id);
            request.setAttribute("scrap", scrapDetailDTO);
            OfferResponse.OfferDetailDTO offerDetailDTO = offerRepository.findOffer(id);
            request.setAttribute("offer", offerDetailDTO);
        } else {
            ScrapResponse.DetailDTO scrapDetailDTO = scrapRepository.findScrap(id, sessionComp.getId());
            request.setAttribute("scrap", scrapDetailDTO);
            OfferResponse.OfferDetailDTO offerDetailDTO = offerRepository.findOffer(id, session.getAttribute("jobsId"));
            request.setAttribute("offer", offerDetailDTO);

//            request.setAttribute("jobsId2", session.getAttribute("jobsId"));

            List<JobResponse.JobListByUserId> jobsList = jobsRepository.findAllByUserId(sessionComp.getId());
            request.setAttribute("jobsList", jobsList);
        }
        return "/resume/resumeDetail";
    }

    // 수정관리 페이지 ----------

    @PostMapping("/resume/{id}/manageResume")
    public String manageResume(@PathVariable Integer id,HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        request.setAttribute("userId", sessionUser.getId());
        List<ResumeRequest.UserViewDTO> resumeList = resumeRepository.findAllUserId(id);
        System.out.println(request); // 이거 스킬 안넣었을때 리스트

        for (int i = 0; i < resumeList.size(); i++) {
            //우리가 아까만든 생성자에 resumeList 값들이 들어간다
            ResumeRequest.UserViewDTO dto = resumeList.get(i);
            dto.setSkillList(resumeRepository.findAllByResumeId(dto.getId()));
        }

        request.setAttribute("resumeList", resumeList);
        System.out.println(request); // 이건 스킬추카하고 나서 리스트
//        List<SkillResponse.ResumeSkillDTO> resumeSkillList = resumeRepository.findAllByResumeId(id);

        return "/resume/manageResume";
    }

    @GetMapping("/resume/{id}/writeResumeForm")
    public String writeResumeForm() {

        return "/resume/writeResumeForm";
    }

    // 업데이트 -------------

    // yz/0305 수정하기
    @GetMapping("/resume/{id}/updateResumeForm")
    public String updateResumeForm(@PathVariable int id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) { // 401
            return "redirect:/loginForm";
        }

        Object[] resume = resumeRepository.findByResumeId(id);

        List<String> skillNames = skillRepository.findALLNameByResumeId(id);


        ResumeRequest.ResumeJoinDTO skillChecked = ResumeRequest.ResumeJoinDTO.builder()
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
                .skillChecked(new SkillResponse.SkillCheckedDTO(skillNames))
                .build();

        // row 세션에 담아
        request.setAttribute("resume", skillChecked);
        skillChecked.getSkillChecked().isJava();



        // 머스테치에 세션 데이터값 넣어주기

        return "/resume/updateResumeForm";
    }


    // 수정 업데이트 지우지 마세요
    // yz/0305 수정하기
    @PostMapping("/resume/{id}/update")
    public String update(@PathVariable Integer id, ResumeRequest.ResumeUpdateDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        // 업데이트 메서드 실행
        resumeRepository.updateById(requestDTO, id);

        return "redirect:/user/" + sessionUser.getId() + "/userHome";
    }

    // 글쓰기 --------------
    @PostMapping("/resume/save")
    public String save(ResumeRequest.ResumeWriterDTO requestDTO, HttpServletRequest request) {

        resumeRepository.save(requestDTO);
        Resume resume = (Resume) resumeRepository.findById(requestDTO.getId());
        request.setAttribute("resumeList", resume);


        return "/resume/manageResume";
    }


    // yz/0305 삭제하기
    @PostMapping("/resume/{id}/delete")
    public String delete(@PathVariable int id) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) { // 401
            return "redirect:/loginForm";
        }
        //Resume resume = resumeRepository.findById(id);
        resumeRepository.deleteById(id);

        //request.setAttribute("resume", resumeDTO);

        return "redirect:/resume/"+ sessionUser.getId() +"/manageResume";

    }

}