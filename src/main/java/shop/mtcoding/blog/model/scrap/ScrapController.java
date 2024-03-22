package shop.mtcoding.blog.model.scrap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ScrapController {

    @PostMapping("/resume/scrap")
    public String save1() {

        return "redirect:/";
    }

    @PostMapping("/comp/scrap")
    public String save2() {

        return "redirect:/";
    }

    @DeleteMapping("/resume/scrap/{id}")
    public String delete1(@PathVariable Integer id) {

        return "redirect:/";
    }

    @DeleteMapping("/comp/scrap/{id}")
    public String delete2(@PathVariable Integer id) {

        return "redirect:/";
    }
}
