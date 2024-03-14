package shop.mtcoding.blog.model.profile;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class ProfileRequest {

    @Data
    public static class ProfileDTO{
        private MultipartFile photo;
    }
}
