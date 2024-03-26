package shop.mtcoding.blog.model.resume;

import lombok.Builder;
import lombok.Data;
import shop.mtcoding.blog.model.apply.Apply;
import shop.mtcoding.blog.model.user.User;

import java.sql.Date;
import java.sql.Timestamp;
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
        private Date birth;
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

    @Data
    public static class ResumeApplyDTO {
        private Integer id;
        private String title;
        private Integer userId;
        private Boolean isPass;
        private Boolean isApply;

        @Builder
        public ResumeApplyDTO(Resume resume, Apply apply) {
            this.id = resume.getId();
            this.title = resume.getTitle();
            this.userId = resume.getUser().getId();

            // 1. 지원x 2.지원중 3.합격 4.불합격
            if (apply.getIsPass().equals("1")){
                this.isApply = false;
            } else if (apply.getIsPass().equals("2")) {
                this.isApply = true;
            } else if (apply.getIsPass().equals("3")) {
                this.isApply = true;
                this.isPass = true;
            } else if (apply.getIsPass().equals("4")) {
                this.isApply = true;
                this.isPass = false;
            }
        }
    }
}
