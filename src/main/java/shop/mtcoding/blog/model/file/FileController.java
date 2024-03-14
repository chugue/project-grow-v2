package shop.mtcoding.blog.model.file;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import shop.mtcoding.blog.model.file.FileInfo;
import shop.mtcoding.blog.model.file.FileInfoRepository;
import shop.mtcoding.blog.model.file.FileInfoRequest;
import shop.mtcoding.blog.model.user.User;
import shop.mtcoding.blog.model.user.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class FileController {
    private final HttpSession session;
    private final FileInfoRepository fileInfoRepository;
    private final UserRepository userRepository;

    @PostMapping("/upload")
    public String upload(FileInfoRequest.UploadDTO requestDTO){
        // 1. 데이터 전달 받고
        String title = requestDTO.getTitle();
        MultipartFile imgFile = requestDTO.getImgFile();

        // 2. 파일저장 위치 설정해서 파일을 저장 (UUID 붙여서 롤링)
        String imgFilename = UUID.randomUUID()+"_"+imgFile.getOriginalFilename();
        Path imgPath = Paths.get("./upload/"+imgFilename);
        try {
            // 이미지 파일 저장
            Files.write(imgPath, imgFile.getBytes());

            // 3. DB에 저장 (title, realFileName)
            fileInfoRepository.insert(title, imgFilename);
            // User imgFileName update
            User comp = (User) session.getAttribute("sessionComp");
            // user db 에 저장
            User newComp  = userRepository.updateImgFileName(imgFilename, comp.getId());
            // session에 저장
            session.setAttribute("sessionComp",newComp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "redirect:/";
    }

    @GetMapping("/uploadForm")
    public String uploadForm(){
        return "uploadForm";
    }

    @GetMapping("/uploadCheck")
    public String uploadCheck(HttpServletRequest request){
        FileInfo pic = fileInfoRepository.findById(1);
        request.setAttribute("pic", pic);
        return "uploadCheck";
    }
}