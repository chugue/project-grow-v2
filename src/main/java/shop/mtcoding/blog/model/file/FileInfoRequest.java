package shop.mtcoding.blog.model.file;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class FileInfoRequest {
    @Data
    public static class UploadDTO{
        private String title;
        private MultipartFile imgFile;
    }
}