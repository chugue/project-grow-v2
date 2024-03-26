package shop.mtcoding.blog.model.resume;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class ResumeResponse {

    @Builder
    @Data
    public static class ResumeDTO{
        private Integer id;
        private UserDTO user;
        private String title;
        private String area;
        private String edu;
        private String career;
        private String introduce;
        private String portLink;
        private List<SkillDTO> skillList;
        private Timestamp createdAt;
    }

    @Data
    @Builder
    public static class UserDTO{
        private Integer id;
        private String email;
        private String myName;
        private String phone;
        private String address;
        private LocalDate birth;
        private Integer role;
        private Timestamp createdAt;
        private String imgFileName;
    }

    @Data
    @Builder
    public static class SkillDTO{
        private Integer id;
        private String name;
        private Integer role;
        private String color;
    }
}
