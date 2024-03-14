package shop.mtcoding.blog.model.resume;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog.model.skill.Skill;

import java.sql.Timestamp;
import java.util.List;

public class ResumeResponse {

    @NoArgsConstructor
    @Data
    public static class ResumeUserDTO {
        private Integer id;
        private Integer userId;
        private String title;
        private String area;
        private String edu;
        private String career;
        private String introduce;
        private String portLink;
        private Timestamp createdAt;
        private String myName;
        private List<Skill> skillList;

        public ResumeUserDTO(Integer id, Integer userId, String title, String area, String edu, String career, String introduce, String portLink, Timestamp createdAt, String myName) {
            this.id = id;
            this.userId = userId;
            this.title = title;
            this.area = area;
            this.edu = edu;
            this.career = career;
            this.introduce = introduce;
            this.portLink = portLink;
            this.createdAt = createdAt;
            this.myName = myName;
        }
    }
}
