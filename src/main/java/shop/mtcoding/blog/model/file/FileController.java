package shop.mtcoding.blog.model.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class FileController {
    private final FileService fileService;

    @PostMapping("/upload")
    public String upload(FileInfoRequest.UploadDTO reqDTO){
        fileService.upload(reqDTO);

        return "redirect:/";
    }
}
