package shop.mtcoding.blog.model.apply;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog.model.apply.ApplyRepository;
import shop.mtcoding.blog.model.apply.ApplyRequest;
import shop.mtcoding.blog.model.jobs.JobRequest;
import shop.mtcoding.blog.model.jobs.JobsRepository;
import shop.mtcoding.blog.model.resume.ResumeRepository;
import shop.mtcoding.blog.model.resume.ResumeRequest;
import shop.mtcoding.blog.model.skill.SkillRepository;
import shop.mtcoding.blog.model.skill.SkillRequest;
import shop.mtcoding.blog.model.user.User;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class ApplyController {
    private final ApplyRepository applyRepository;
    private final HttpSession session;
    private final JobsRepository jobsRepository;
    private final SkillRepository skillRepository;
    private final ResumeRepository resumeRepository;

    @PostMapping("/jobs/apply/save")
    public String applySave(ApplyRequest.saveDTO requestDTO) {

        applyRepository.save(requestDTO);
        return "redirect:/jobs/jobsDetail/" + requestDTO.getJobsId();
    }

    @PostMapping("/apply/pass/update/{id}")
    public String applyPassUpDate(@PathVariable Integer id, @RequestParam("jobsId") Integer jobsId) {
        User sessionComp = (User) session.getAttribute("sessionComp");
        applyRepository.passUpdate(id, jobsId);
        return "redirect:/comp/" + sessionComp.getId() + "/comphome?jobsId=" + jobsId;
    }

    @PostMapping("/apply/fail/update/{id}")
    public String applyFailUpDate(@PathVariable Integer id, @RequestParam("jobsId") Integer jobsId) {
        User sessionComp = (User) session.getAttribute("sessionComp");
        applyRepository.failUpdate(id, jobsId);
        return "redirect:/comp/" + sessionComp.getId() + "/comphome?jobsId=" + jobsId;
    }

    @PostMapping("/apply/pass2/update/{id}")
    public String applyPassUpDate2(@PathVariable Integer id, @RequestParam("jobsId") Integer jobsId) {
        applyRepository.passUpdate(id, jobsId);
        return "redirect:/resume/resumeDetail/" + id + "?jobsId=" + jobsId;
    }

    @PostMapping("/apply/fail2/update/{id}")
    public String applyFailUpDate2(@PathVariable Integer id, @RequestParam("jobsId") Integer jobsId) {
        applyRepository.failUpdate(id, jobsId);
        return "redirect:/resume/resumeDetail/" + id + "?jobsId=" + jobsId;

    }


    @GetMapping("/resume/{jobId}/apply")
    public String apply(@PathVariable Integer jobId, @RequestParam("resumeId") Integer resumeId, HttpServletRequest
            request) {
        boolean applySuccess = false;

        applyRepository.saveResumeJobsApply(resumeId, jobId);
        Object[] status = applyRepository.findStatusByResumeJobs(resumeId, jobId);
        if (status != null) {
            applySuccess = true;
            request.setAttribute("applySuccess", applySuccess);
        }
        Object[] job = jobsRepository.findByJobId(jobId);

        JobRequest.JobsJoinDTO Checked = JobRequest.JobsJoinDTO.builder()
                .compName(String.valueOf(job[0]))
                .userId((Integer) job[1])
                .address(String.valueOf(job[2]))
                .phone(String.valueOf(job[3]))
                .area(String.valueOf(job[4]))
                .edu(String.valueOf(job[5]))
                .career(String.valueOf(job[6]))
                .content(String.valueOf(job[7]))
                .title(String.valueOf(job[8]))
                .homepage(String.valueOf(job[9]))
                .task(String.valueOf(job[10]))
                .deadLine(job[11] != null ? String.valueOf(job[11]) : null)  // job[11]이 deadLine에 해당합니다.
                .businessNumber(String.valueOf(job[12]))
                .photo(String.valueOf(job[13]))
                .build();

        List<SkillRequest.ApplyskillDTO> skillList = skillRepository.JobsSkill(jobId);
        User user = (User) session.getAttribute("sessionUser");
        List<ResumeRequest.UserViewDTO> resumeList = resumeRepository.findAllUserId((user.getId()));
        // row 세션에 담아
        request.setAttribute("jobsId", jobId);
        request.setAttribute("jobs", Checked);
        request.setAttribute("skillList", skillList);
        request.setAttribute("resumeList", resumeList);
        System.out.println(11111);
        return "/jobs/jobsDetail";
    }

    @GetMapping("/resume/{jobId}/applyList")
    public String resumeApplyList(@PathVariable Integer jobId, @RequestParam("resumeId") Integer
            resumeId, HttpServletRequest request) {

        List<ApplyRequest.ApplyResumeJobsDTO2> applyResumeJobsDTOList = applyRepository.findAllByResumeId(resumeId);

        User user = (User) session.getAttribute("sessionUser");
        List<ResumeRequest.UserViewDTO> resumeList = resumeRepository.findAllUserId((user.getId()));
        List<SkillRequest.ApplyskillDTO> skillList = skillRepository.JobsSkill(jobId);
        // row 세션에 담아
        request.setAttribute("jobsId", jobId);
        request.setAttribute("sessionUserId", user.getId());
        request.setAttribute("jobs", applyResumeJobsDTOList);
        request.setAttribute("skillList", skillList);
        request.setAttribute("resumeList", resumeList);
        System.out.println(11111);
        return "/user/apply";
    }
}
