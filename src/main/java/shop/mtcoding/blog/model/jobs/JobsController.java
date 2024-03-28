package shop.mtcoding.blog.model.jobs;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog.model.resume.ResumeResponse;
import shop.mtcoding.blog.model.resume.ResumeService;
import shop.mtcoding.blog.model.user.User;

import java.util.List;


@RequiredArgsConstructor
@Controller
public class JobsController {
    private final JobsService jobsService;
    private final ResumeService resumeService;
    private final HttpSession session;

    @GetMapping("/jobs/jobs-detail/{jobsId}")
    public String jobsDetail(@PathVariable Integer jobsId, HttpServletRequest request){
        User sessionUser = (User)session.getAttribute("sessionUser");

        if (sessionUser == null){
            throw new Exception401("인증되지 않았습니다.");
        }

        //공고정보와 사용자정보를 가져오는 detailDTO
        JobsResponse.DetailDTO detailDTO = jobsService.DetailDTO(jobsId);
        System.out.println("detailDTO :"+detailDTO);

        //사용자 이력서 보유내역과 지원상태를 가져오는 ResumeApplyDTO
        List<ResumeResponse.ResumeApplyDTO> resumeApplyDTOList = resumeService.findAllResumeJoinApplyByUserIdAndJobsId(sessionUser.getId(), jobsId);
        System.out.println("resumeApplyDTOList: " + resumeApplyDTOList);
        request.setAttribute("resumeApplyDTOList", resumeApplyDTOList);
        request.setAttribute("detailDTO", detailDTO);
        return "jobs/jobs-detail";
    }

    @GetMapping("/jobs/info")
    public String jobsInfo (HttpServletRequest request) {
        List<JobsResponse.ListDTO> listDTOS = jobsService.listDTOS();
        request.setAttribute("listDTOS", listDTOS);
        return "/jobs/info";
    }
}
