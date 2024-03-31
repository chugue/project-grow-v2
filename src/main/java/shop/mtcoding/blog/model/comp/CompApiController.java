package shop.mtcoding.blog.model.comp;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CompApiController {
    private final CompService compService;

    @PostMapping("/api/find-applicants")
    public List<CompResponse.RusaDTO> findApplicants (@RequestParam("jobsId") Integer jId){
        return compService.findApplicants(jId);
    }
}

