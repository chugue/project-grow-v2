package shop.mtcoding.blog.model.resume;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeJPARepository resumeJPARepo;

    public List<ResumeResponse.ResumeDTO> findAll() {
       List<Resume> resumes = resumeJPARepo.findAll();

       return resumes.stream().map(resume -> resume.toDTO()).collect(Collectors.toList());
    }
}
