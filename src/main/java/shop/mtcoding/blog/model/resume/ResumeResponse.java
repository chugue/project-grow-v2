package shop.mtcoding.blog.model.resume;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.model.apply.Apply;
import shop.mtcoding.blog.model.comp.CompResponse;
import shop.mtcoding.blog.model.jobs.JobsResponse;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillResponse;
import shop.mtcoding.blog.model.user.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ResumeResponse {

    // 이력서 수정
    @Data
    public static class UpdateDTO {
        private String title;
        private String area;
        private String edu;
        private String career;
        private String introduce;
        private String portLink;
        private SkillResponse.SkillCheckedDTO skillChecked;

        @Builder
        public UpdateDTO(String title, String area, String edu, String career, String introduce, String portLink, SkillResponse.SkillCheckedDTO skillChecked) {
            this.title = title;
            this.area = area;
            this.edu = edu;
            this.career = career;
            this.introduce = introduce;
            this.portLink = portLink;
            this.skillChecked = skillChecked;

        }
    }


    //이력서 상세보기 DTO
    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String edu;
        private String introduce;
        private String imgFileName;
        private String myName;
        private LocalDate birth;
        private String phone;
        private String email;
        private String address;
        private String area;
        private String career;
        private String portLink;
        private Integer userId;
        private Integer role;
        private Boolean isComp;
        private Boolean isUser;
        private List<SkillDTO2> skills;

        @Builder
        public DetailDTO(Resume resume, User user, Integer role, List<Skill> skills) {
            this.id = resume.getId();
            this.title = resume.getTitle();
            this.edu = resume.getEdu();
            this.introduce = resume.getIntroduce();
            this.imgFileName = user.getImgFileName();
            this.myName = user.getMyName();
            this.birth = user.getBirth();
            this.phone = user.getPhone();
            this.email = user.getEmail();
            this.address = user.getAddress();
            this.area = resume.getArea();
            this.career = resume.getCareer();
            this.portLink = resume.getPortLink();
            this.userId = user.getId();
            this.role = role;
            this.isComp = false;
            this.isUser = false;
            this.skills = skills.stream()
                    .map(skill -> new SkillDTO2(skill))
                    .collect(Collectors.toList());

            if (this.role == 1) {
                this.isUser = true;
            } else if (this.role == 2) {
                this.isComp = true;
            }
        }
    }

    @Data
    public static class SkillDTO2 {
        private Integer id;
        private String name;
        private String color;

        public SkillDTO2(Skill skill) {
            this.id = skill.getId();
            this.name = skill.getName();

            // 혹시 언어 추가할게 있으면 else if랑 컬러, 같은 양식 맞춰서 추가가능
            if (this.name.equals("Jquery")) {
                this.color = "badge badge-pill bg-primary";
            } else if (this.name.equals("JavaScript")) {
                this.color = "badge badge-pill bg-secondary";
            } else if (this.name.equals("Spring")) {
                this.color = "badge badge-pill bg-success";
            } else if (this.name.equals("HTML/CSS")) {
                this.color = "badge badge-pill bg-danger";
            } else if (this.name.equals("JSP")) {
                this.color = "badge badge-pill bg-warning";
            } else if (this.name.equals("Java")) {
                this.color = "badge badge-pill bg-info";
            } else if (this.name.equals("React")) {
                this.color = "badge badge-pill bg-dark";
            } else if (this.name.equals("Vue.js")) {
                this.color = "badge badge-pill bg-Indigo";
            } else if (this.name.equals("Oracle")) {
                this.color = "badge badge-pill bg-brown";
            } else if (this.name.equals("MySql")) {
                this.color = "badge badge-pill bg-purple";
            }
            // 추가 양식
            // else if (this.name.equals("언어")){
            //      this.color = "badge 컬러 " ;

        }
    }

    @Builder
    @Data
    public static class ResumeDTO {
        private Integer id;
        private String title;
        private String area;
        private String edu;
        private String career;
        private String introduce;
        private String portLink;
        private UserDTO user;
        private List<SkillDTO> skillList;
        private LocalDate createdAt;
    }

    @Data
    @Builder
    public static class UserDTO {
        private Integer id;
        private String email;
        private String myName;
        private String phone;
        private String address;
        private LocalDate birth;
        private Integer role;
        private LocalDate createdAt;
        private String imgFileName;
    }

    @Data
    @Builder
    public static class SkillDTO {
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
            if (apply.getIsPass().equals("1")) {
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

