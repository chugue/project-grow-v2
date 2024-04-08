package shop.mtcoding.blog.model.comp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog.model.jobs.JobsResponse;
import shop.mtcoding.blog.model.jobs.JobsService;
import shop.mtcoding.blog.model.resume.ResumeResponse;
import shop.mtcoding.blog.model.resume.ResumeService;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CompController {
    private final CompService compService;
    private final HttpSession session;
    private final ResumeService resumeService;
    private final UserService userService;
    private final JobsService jobsService;

    @GetMapping("/comp/{id}/comp-manage")
    public String compManage (@PathVariable Integer id, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionComp");
        CompResponse.MainCountDTO mainCountDTO = compService.mainCountByUid(sessionUser.getId());
        List<CompResponse.CompManageDTO> compManageDTOList = compService.compManage(sessionUser.getId());
        request.setAttribute("mainCount", mainCountDTO);
        request.setAttribute("compManageList",compManageDTOList );
        return "/comp/comp-manage";
    }

    @GetMapping("/comp/resume-detail/{id}")  // 기업이 이력서를 조회했을때 필요한 로직
    public String resumeDetail(@PathVariable Integer id, @RequestParam(name = "jobsId") Integer jobsId, HttpServletRequest request) {
        User sessionComp = (User) session.getAttribute("sessionComp");
        User newSessionUser = userService.findById(sessionComp.getId());
        System.out.println(111111);
        ResumeResponse.CompDetailDTO resume = resumeService.compResumeDetail(id, jobsId, newSessionUser);
        request.setAttribute("resume", resume);

        return "/comp/resume-detail";
    }

//    @PostMapping("/comp/{id}/update")
//    public String update(@PathVariable Integer id, CompRequest.UpdateDTO requestDTO) {
//        User sessionComp = (User) session.getAttribute("sessionComp");
//        User user = compService.updateById(sessionComp, requestDTO);
//        session.setAttribute("sessionComp", user);
//        return "redirect:/comp/" + id + "/comp-home";
//    }

    @PostMapping("/comp/{id}/update")
    public String update(@PathVariable Integer id, CompRequest.UpdateDTO requestDTO) {
        User sessionComp = (User) session.getAttribute("sessionComp");
        User user = compService.updateById(sessionComp, requestDTO);
        session.setAttribute("sessionComp", user);
        return "redirect:/comp/" + id + "/comp-home";
    }

    @GetMapping("/comp/{id}/update-form")
    public String updateForm(@PathVariable int id, HttpServletRequest request) {
        User sessionComp = (User) session.getAttribute("sessionComp");
        User newSessionUser = compService.findById(sessionComp.getId());
        request.setAttribute("user", newSessionUser);

        return "/comp/update-form";
    }

    @GetMapping("/comp/comp-index")
    public String compIndex(HttpServletRequest request) {
        List<CompResponse.ResumeUserSkillDTO> rusList = compService.findAllRusList();
        request.setAttribute("rusList", rusList);
        return "comp/comp-index";
    }

    @GetMapping("/comp/read-resume")
    public String readResume(HttpServletRequest request,
                             @RequestParam(required = false, defaultValue = "") String area,
                             @RequestParam(required = false, defaultValue = "") String skill,
                             @RequestParam(required = false, defaultValue = "") String career) {


            List<CompResponse.ResumeUserSkillDTO> rusList = resumeService.getResumeWithOption(area,skill,career);
            request.setAttribute("rusList", rusList);



        request.setAttribute("selected", ResumeResponse.resumeSearchDTO.builder()
                .area(area)
                .skill(skill)
                .career(career)
                .build());

        return "/comp/read-resume";
    }

    @GetMapping("/comp/{id}/comp-home")
    public String compHome(@PathVariable Integer id, @RequestParam(required = false, defaultValue = "0") Integer jobsId, HttpServletRequest request) {
        User sessionComp = (User) session.getAttribute("sessionComp");
        List<CompResponse.ComphomeDTO> comphomeDTOList = compService.findAllByUserId(sessionComp.getId());
        request.setAttribute("jobsList", comphomeDTOList);
        request.setAttribute("sessionC", sessionComp);
        return "/comp/comp-home";
    }

    @GetMapping("/comp/{id}/apply")
    public String offer(@PathVariable Integer id) {

        return "/comp/apply";
    }

    @GetMapping("/comp/join-form")
    public String compJoinForm() {

        return "/comp/join-form";
    }

    @PostMapping("/comp/join")
    public String compJoin(@RequestParam(name = "role") Integer role, CompRequest.CompJoinDTO reqDTO) {
        User user = compService.join(role, reqDTO);
        session.setAttribute("sessionComp", user);
        return "redirect:/comp/read-resume";
    }

    @GetMapping("/comp/profile-update-form")
    public String profileUpdateForm(HttpServletRequest request) {

        User sessionUser = (User) session.getAttribute("sessionComp");
        request.setAttribute("imgFileName", sessionUser.getImgFileName());
        return "comp/profile-update-form";
    }


    @GetMapping("/comp/{id}/scrap")
    public String scrap(@PathVariable Integer id) {
        return "/comp/scrap";
    }

    @GetMapping("/comp/talent")
    public String talent() {
        return "/comp/talent";
    }


    @GetMapping("/comp/jobs-info")
    public String jobsInfo(HttpServletRequest request,
                           @RequestParam(required = false, defaultValue = "") String area,
                           @RequestParam(required = false, defaultValue = "") String skill,
                           @RequestParam(required = false, defaultValue = "") String task) {


        List<JobsResponse.ListDTO> listDTOS = jobsService.getJobsWithOption(area, task, skill);
        request.setAttribute("listDTOS", listDTOS);

        request.setAttribute("selected", JobsResponse.searchDTO.builder()
                .area(area)
                .skill(skill)
                .task(task)
                .build());

        return "comp/jobs-info";
    }
}





