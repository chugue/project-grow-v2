package shop.mtcoding.blog.model.user;

import lombok.Builder;
import lombok.Data;
import shop.mtcoding.blog.model.skill.SkillRequest;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class UserRequest {

    @Data
    public static class ResumeOfterDTO {
        private Integer id; // apply 의 키값
        private String compName;
        private String title;
        private String career;
        private Integer jobsId;
        private Integer isPass;
        private List<SkillRequest.JobSkillDTO> skillList;


        public ResumeOfterDTO(Integer id, String compName, String title, String career, Integer jobsId, Integer isPass) {
            this.id = id;
            this.compName = compName;
            this.title = title;
            this.career = career;
            this.jobsId = jobsId;
            this.isPass = isPass;
        }
    }

    @Data
    public static class UserDto {
        private Integer id;
        private String email;
        private String myName;
        private String phone;
        private String address;
        private Date birth;
        private String businessNumber;
        private String photo;
        private String compName;
        private String homepage;
        private Integer role;
        private Timestamp createdAt;
    }

    @Data
    public static class UpdateDTO{
        private String password;
        private String myName;
        private String phone;
        private Date birth;
        private String address;
    }

    @Data
    public static class LoginDTO{
        private String email;
        private String password;
    }

    @Data
    public static class JoinDTO{
        private String email;
        private String myName;
        private String password;
        private Date birth;
        private String tel;
        private Integer role;
    }

    @Data
    @Builder
    public static class UserAllDTO{

        private Integer id;
        private String email;
        private String myName;
        private String password;
        private String phone;
        private String address;
        private Date birth;
        private String businessNumber;
        private String photo;
        private String compName;
        private String homepage;
        private Integer role;
        private Timestamp createdAt;
    }
    @Data
    public static class CompUpdateDTO{
        private String myName;
        private String password;
        private String phone;
        private String compName;
        private String homepage;
        private Date birth;
        private String address;

    }
}
