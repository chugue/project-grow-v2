package shop.mtcoding.blog.model.skill;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SkillService {
    private final SkillJPARepository skillJPARepository;

    public List<Skill> skillListForJobs (Integer jobsId, Integer role){
        List<Skill> skillList = skillJPARepository.findByJobsIdAndRole(jobsId,role);
        return skillList;
    }

}
