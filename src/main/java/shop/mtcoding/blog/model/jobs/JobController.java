package shop.mtcoding.blog.model.jobs;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class JobController {
    private final JobService service;


    @GetMapping("jobs/info")
    public String findAll(HttpServletRequest request){
//        List<JobResponse.DTO> jobs = service.findAll2();
//        System.out.println(jobs);
//        request.setAttribute("pageList",jobs);
        return "/jobs/info";
    }

    @PutMapping("")
    public String update(){
        return null;
    }


    @PutMapping("")
    public String save(){
        return null;
    }

    @DeleteMapping("")
    public String delete(){

        return null;
    }
}
