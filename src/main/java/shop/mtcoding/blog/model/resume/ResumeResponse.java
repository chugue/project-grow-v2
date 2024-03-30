package shop.mtcoding.blog.model.resume;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.model.apply.Apply;
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
    public static class UpdateDTO{
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
        private String career;
        private String portLink;
        private Integer userId;
        private List<SkillDTO2> skillList = new ArrayList<>();
//        private Boolean isOwner;


        @Builder
        public DetailDTO(Resume resume, User sessionUser, List<Skill> skillList) {
            this.id = resume.getId();
            this.title = resume.getTitle();
            this.edu = resume.getEdu();
            this.introduce = resume.getIntroduce();
            this.career = resume.getCareer();
            this.portLink = resume.getPortLink();
            this.userId = resume.getUser().getId();
            this.skillList = resume.getSkillList().stream().map(skill ->
                    new SkillDTO2(skill)).toList();
//            this.isOwner = resume.isOwner();
//
//            if (sessionUser != null) {
//                if (sessionUser.getRole() == 2) {
//                    isOwner = true;
//                }
//            }

        }


        @Data
        public static class SkillDTO2 {
            private Integer id;
            private String name;
            private String color;

            @Builder
            public SkillDTO2(Skill skill) {
                this.id = skill.getId();
                this.name = skill.getName();
                this.color = skill.getColor();
            }
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
    public static class ResumeStateDTO{
        private Boolean isApply;
        private List<ResumeApplyDTO> applys;

    }

    @Data
    public static class ResumeApplyDTO {
        private Integer id;
        private String title;
        private Integer userId;
        private String isPass;

        @Builder
        public ResumeApplyDTO(Resume resume, Apply apply) {
            this.id = resume.getId();
            this.title = resume.getTitle();
            this.userId = resume.getUser().getId();
            this.isPass = apply.getIsPass();
        }
    }

}


