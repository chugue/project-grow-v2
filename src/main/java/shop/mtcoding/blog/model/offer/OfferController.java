package shop.mtcoding.blog.model.offer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.mtcoding.blog.dto.scrap.ScrapRequest;
import shop.mtcoding.blog.dto.scrap.ScrapResponse;
import shop.mtcoding.blog.model.apply.ApplyRepository;
import shop.mtcoding.blog.model.comp.CompRepository;
import shop.mtcoding.blog.model.jobs.JobResponse;
import shop.mtcoding.blog.model.jobs.JobsRepository;
import shop.mtcoding.blog.model.offer.OfferRepository;
import shop.mtcoding.blog.model.offer.OfferRequest;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.resume.ResumeRepository;
import shop.mtcoding.blog.model.scrap.ScrapRepository;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserRepository;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OfferController {
    private final OfferRepository offerRepository;
    private final HttpSession session;

    @PostMapping("/resume/offer/save")
    public String save(OfferRequest.SaveDTO saveDTO) {
        offerRepository.save(saveDTO, 1);

        return "redirect:/resume/resumeDetail/" + saveDTO.getResumeId();
    }

    @PostMapping("/resume/offer/{id}/delete")
    public String delete1(@PathVariable Integer id, OfferRequest.DeleteDTO DeleteDTO) {
        offerRepository.deleteById(session.getAttribute("jobsId2"), DeleteDTO);
        return "redirect:/resume/resumeDetail/" + DeleteDTO.getResumeId();
    }
}