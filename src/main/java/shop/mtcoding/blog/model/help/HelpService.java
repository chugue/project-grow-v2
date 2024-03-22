package shop.mtcoding.blog.model.help;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HelpService {
    private  HelpJPARepository helpJPARepository;
}
