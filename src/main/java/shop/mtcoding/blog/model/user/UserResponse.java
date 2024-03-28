package shop.mtcoding.blog.model.user;

import lombok.Builder;
import lombok.Data;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.skill.Skill;

import java.util.List;
import java.util.stream.Collectors;

public class UserResponse {

    @Data
    public static class UserResumeSkillV2DTO{
        private Integer id;
        private String myName;
        private String address;
        private Integer resumeId;
        private String title; // resume
        private String career; // resume
        private List<Skill2DTO> skillList;

        @Builder
        public UserResumeSkillV2DTO(User user, Resume resume, List<Skill> skillList) {
            this.id = user.getId();
            this.myName = user.getMyName();
            this.address = user.getAddress();
            this.resumeId = resume.getId();
            this.title = resume.getTitle();
            this.career = resume.getCareer();
            this.skillList = skillList.stream()
                    .map(skill -> new Skill2DTO(skill))
                    .collect(Collectors.toList());
        }
    }

    @Data
    public static class Skill2DTO{
        private Integer id;
        private String name;
        private String color;

        public Skill2DTO(Skill skill) {
            this.id = skill.getId();
            this.name = skill.getName();
            this.color = skill.getColor();
        }
    }


    @Data
    public static class UserResumeSkillDTO {
        private Integer id;
        private String myName;
        private String career;
        private String resumeTitle;
        private List<SkillDTO> skillList;

        @Builder
        public UserResumeSkillDTO(User user, Resume resume, List<Skill> skillList) {
            this.id = user.getId();
            this.myName = user.getMyName();
            this.career = resume.getCareer();
            this.resumeTitle = resume.getTitle();
            this.skillList = skillList.stream()
                    .map(skill -> new SkillDTO(skill))
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
            }
            // 추가 양식
            // else if (this.name.equals("언어")){
            //      this.color = "badge 컬러 " ;

        }
    }

}
