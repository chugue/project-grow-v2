package shop.mtcoding.blog.model.apply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service // IoC 에 등록된다.
public class ApplyService {

    private final ApplyJPARepository applyJPARepo;

}
