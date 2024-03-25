package shop.mtcoding.blog.model.jobs;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JobsService {
    private final JobsJPARepository jobsRepo;

    public List<Jobs> jobsList () {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        List<Jobs> jobsList = jobsRepo.findAll(sort);
        return jobsList;
    }
}
