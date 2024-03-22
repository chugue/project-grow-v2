package shop.mtcoding.blog.model.jobs;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class JobController {
    private final JobService service;

    @GetMapping("/jobs/{id}/interest")
    public String interest(@PathVariable Integer id){
        return "/jobs/interest";
    }

    @GetMapping("/jobs/jobs-detail/{id}")
    public String jobsDetail(@PathVariable Integer id){
        return "/jobs/jobs-detail";
    }

    @GetMapping("/jobs/info")
    public String findAll(HttpServletRequest request){
//        List<JobResponse.DTO> jobs = service.findAll2();
//        System.out.println(jobs);
//        request.setAttribute("pageList",jobs);
        return "/jobs/info";
    }

    @GetMapping("/jobs/{id}/update-form")
    public String updateForm(@PathVariable Integer id){


        return"/jobs/update-form";
    }

    @PutMapping("/jobs/{id}/update")
    public String update(@PathVariable Integer id){
        return "redirect:/";
    }

    @GetMapping("/jobs/write-jobs-form")
    public String writeForm(){

        return "/jobs/write-jobs-from";
    }


    @PostMapping("/jobs/save")
    public String save(){
        return "redirect:/";
    }

    @DeleteMapping("/jobs/{id}/delete")
    public String delete(@PathVariable Integer id){

        return "redirect:/";
    }
}
