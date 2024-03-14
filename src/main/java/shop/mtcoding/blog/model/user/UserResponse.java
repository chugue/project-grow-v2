package shop.mtcoding.blog.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillRequest;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class UserResponse {

    @NoArgsConstructor
    @Data
    public static class DTO {
        private Integer id;
        private Integer userId;
        private String area;
        private String title;
        private String edu;
        private String career;
        private String content;
        private Date deadLine;
        private String task;
        private Timestamp createdAt;
        private String compName;
        private List<Skill> skillList;

        public DTO(Integer id, Integer userId, String area, String title, String edu, String career, String content, Date deadLine, String task, Timestamp createdAt, String compName) {
            this.id = id;
            this.userId = userId;
            this.area = area;
            this.title = title;
            this.edu = edu;
            this.career = career;
            this.content = content;
            this.deadLine = deadLine;
            this.task = task;
            this.createdAt = createdAt;
            this.compName = compName;
        }
    }

    @Data
    public static class UserListByUserId{
        private Integer id;
        private String title;
        private String area;
        private String career;
        private List<SkillRequest.JobSkillDTO> skillList;

        public UserListByUserId(Integer id, String title, String area, String career) {
            this.id = id;
            this.title = title;
            this.area = area;
            this.career = career;
        }
    }
}
