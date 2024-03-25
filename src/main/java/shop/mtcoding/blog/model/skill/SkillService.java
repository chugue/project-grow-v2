package shop.mtcoding.blog.model.skill;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SkillService {
    private final SkillJPARepository skillJPARepository;

}
