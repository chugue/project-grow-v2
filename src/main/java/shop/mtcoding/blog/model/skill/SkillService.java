package shop.mtcoding.blog.model.skill;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SkillService {
    private final SkillJPARepository skillJPARepository;

}
