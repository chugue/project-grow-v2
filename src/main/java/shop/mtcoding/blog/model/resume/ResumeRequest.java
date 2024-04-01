package shop.mtcoding.blog.model.resume;


import lombok.Builder;
import lombok.Data;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResumeRequest {
    @Data
    public static class UpdateDTO{
        private String title;
        private String area;
        private String edu;
        private String career;
        private String introduce;
        private String portLink;
        private List<String> skill;
    }

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

    @Data
    public static class UserViewDTO{
        private Integer id;
        private String title;
        private String edu;
        private String area;
        private String career;

        private List<SkillDTO> skills;

        @Builder
        public UserViewDTO(Resume resume, List<Skill> skills) {
            this.id = resume.getId();
            this.title = resume.getTitle();
            this.edu = resume.getEdu();
            this.area = resume.getArea();
            this.career = resume.getCareer();

            this.skills = skills.stream().map(skill -> new SkillDTO(skill))
                    .collect(Collectors.toList());
        }
    }

    @Data
    public static class SkillDTO {
        private Integer id;
        private String name;
        private String color;

        public SkillDTO(Skill skill) {
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
            } else if (this.name.equals("Oracle")) {
                this.color = "badge badge-pill bg-brown";
            } else if (this.name.equals("Vue.js")) {
                this.color = "badge badge-pill bg-indigo";
            } else if (this.name.equals("MySql")) {
                this.color = "badge badge-pill bg-purple";
            }

        }
    }
}
