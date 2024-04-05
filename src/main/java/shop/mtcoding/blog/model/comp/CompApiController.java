package shop.mtcoding.blog.model.comp;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.blog.model.resume.ResumeResponse;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CompApiController {
    private final CompService compService;

    @PostMapping("/api/find-all-jobs")
    public List<CompResponse.CompManageDTO> compManageDTO (@RequestParam(name = "userId") Integer userId){
        return compService.compManage(userId);
    }

    @PostMapping("/api/find-all-applicants")
    public List<ResumeResponse.CompManageDTO> findAllApplicants (@RequestParam(name = "userId") Integer userId){
        return compService.findAllAppli(userId);
    }

    @PostMapping("/api/find-applicants")
    public List<CompResponse.RusaDTO> findApplicants (@RequestParam(name = "jobsId") Integer jobsId){
        return compService.findApplicants(jobsId);
    }

    @PostMapping("/api/find-no-resp")
    public List<ResumeResponse.CompManageDTO> findNoResp(@RequestParam(name = "userId") Integer userId){
        return compService.findNoResp(userId);
    }

}

