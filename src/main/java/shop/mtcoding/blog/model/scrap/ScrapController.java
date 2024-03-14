package shop.mtcoding.blog.model.scrap;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.dto.scrap.ScrapRequest;
import shop.mtcoding.blog.model.scrap.ScrapRepository;
import shop.mtcoding.blog.model.user.User;

@Controller
@RequiredArgsConstructor
public class ScrapController {
    private final ScrapRepository scrapRepository;
    private final HttpSession session;

    @PostMapping("/resume/scrap/save")
    public String save1(ScrapRequest.SaveDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionComp");
        scrapRepository.save(requestDTO, sessionUser.getId());

        return "redirect:/resume/resumeDetail/" + requestDTO.getResumeId();
    }

    @PostMapping("/comp/scrap/save")
    public String save2(ScrapRequest.SaveDTO requestDTO) {
        User sessionUser = (User) session.getAttribute("sessionComp");
        scrapRepository.save(requestDTO, sessionUser.getId());

        return "redirect:/comp/compResumeDetail/" + requestDTO.getResumeId();
    }

    @PostMapping("/resume/scrap/{id}/delete")
    public String delete1(@PathVariable Integer id, ScrapRequest.SaveDTO saveDTO) {
        scrapRepository.deleteById(id);

        return "redirect:/resume/resumeDetail/" + saveDTO.getResumeId();
    }

    @PostMapping("/comp/scrap/{id}/delete")
    public String delete2(@PathVariable Integer id, ScrapRequest.SaveDTO saveDTO) {
        scrapRepository.deleteById(id);

        return "redirect:/comp/compResumeDetail/" + saveDTO.getResumeId();
    }
}
