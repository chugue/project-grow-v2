package shop.mtcoding.blog.model.offer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service // IoC 에 등록된다.
public class OfferService {
    private final OfferJPARepository offerJPARepo;
}
