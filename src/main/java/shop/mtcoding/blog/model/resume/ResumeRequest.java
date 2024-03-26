package shop.mtcoding.blog.model.resume;

import lombok.Data;
import shop.mtcoding.blog.model.skill.SkillRequest;

import java.util.ArrayList;
import java.util.List;

public class ResumeRequest {

    @Data
    public static class UserViewDTO{
        private Integer id;
        private String title;
        private String edu;
        private String area;
        private String career;

        public UserViewDTO(Integer id, String title, String edu, String area, String career) {
            this.id = id;
            this.title = title;
            this.edu = edu;
            this.area = area;
            this.career = career;
        }
        private List<SkillRequest.ResumeSkillDTO> skillList = new ArrayList<>();

    }
}
