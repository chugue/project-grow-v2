package shop.mtcoding.blog.model.resume;

import lombok.Data;
import shop.mtcoding.blog.model.user.User;

import java.util.List;

public class ResumeRequest {


    @Data
    public static class SaveDTO{
        private String title;
        private String area;
        private String edu;
        private String career;
        private String introduce;
        private String portLink;
        private List<String> skill;

        public Resume toEntity(User user){
            return Resume.builder()
                    .title(title)
                    .area(area)
                    .edu(edu)
                    .career(career)
                    .introduce(introduce)
                    .portLink(portLink)
                    .user(user)
                    .build();
        }
    }
}
