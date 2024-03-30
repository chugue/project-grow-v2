package shop.mtcoding.blog.model.comp;

import lombok.Builder;
import lombok.Data;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompResponse {

    @Data
    public static class JobsSkillDTO {
        private Integer id;
        private String compName;
        private String title;
        private String career;
        private String area;
        private LocalDate deadline;
        private String imgFileName;
        private List<SkillDTO> skillList = new ArrayList<>();

        @Builder
        public JobsSkillDTO(Jobs jobs, User user, List<Skill> skillList) {
            this.id = jobs.getId();
            this.compName = user.getCompName();
            this.title = jobs.getTitle();
            this.career = jobs.getCareer();
            this.area = jobs.getArea();
            this.deadline = jobs.getDeadline();
            this.imgFileName = user.getImgFileName();
            this.skillList = skillList.stream().map(skill -> new SkillDTO(skill)).collect(Collectors.toList());
        }
    }


    @Data
    public static class ResumeUserSkillDTO {
        private Integer id;//resume
        private String title;//resume
        private String edu;//resume
        private String career; //resume
        private String area; //resume
        private String myName;//user
        private Integer userId;//user
        private String imgFileName; //user
        private List<SkillDTO> skillList = new ArrayList<>();

        @Builder
        public ResumeUserSkillDTO(Resume resume, User user, List<Skill> skillList) {
            this.id = resume.getId();
            this.title = resume.getTitle();
            this.edu = resume.getEdu();
            this.career = resume.getCareer();
            this.area = resume.getArea();
            this.myName = user.getMyName();
            this.userId = user.getId();
            this.imgFileName = user.getImgFileName();
            this.skillList = skillList.stream()
                    .map(skill -> new SkillDTO(skill))
                    .collect(Collectors.toList());;
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

}
