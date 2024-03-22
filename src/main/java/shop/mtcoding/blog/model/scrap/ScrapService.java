package shop.mtcoding.blog.model.scrap;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service // IoC 에 등록된다.
public class ScrapService {

    private final ScrapJPARepository scrapJPARepo;
}
