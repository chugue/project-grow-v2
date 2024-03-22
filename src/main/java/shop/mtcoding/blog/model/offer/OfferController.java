package shop.mtcoding.blog.model.offer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @PostMapping("/resume/offer")
    public String save() {

        return "redirect:/";
    }

    @DeleteMapping("/resume/offer/{id}")
    public String delete1(@PathVariable Integer id) {

        return "redirect:/";
    }
}