package shop.mtcoding.blog.model.jobs;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JobService {
    private final JobJPARepository jobJPARepository;

    //글 목록조회
    public List<JobResponse.DTO> findAll2(){
//
//        Sort sort = Sort.by(Sort.Direction.DESC,"id");
//        List<Jobs> jobsList = jobJPARepository.findAll(sort);

        return null;
    }

}
