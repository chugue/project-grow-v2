package shop.mtcoding.blog.model.jobs;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.user.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class JobResponse {

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
        private List<SkillDTO> skillList = new ArrayList<>();


        public DTO(Jobs jobs) {
            this.id = jobs.getId();
            this.userId = jobs.getUser().getId();
            this.area = jobs.getArea();
            this.title = jobs.getTitle();
            this.edu = jobs.getEdu();
            this.career = jobs.getCareer();
            this.content = jobs.getContent();
            this.deadLine = jobs.getDeadLine();
            this.task = jobs.getTask();
            this.createdAt = jobs.getCreatedAt();
            this.compName = jobs.getUser().getCompName();
            this.skillList = jobs.getSkillList().stream().map(skill -> new SkillDTO(skill)).toList();
        }

        @Data
        public static class SkillDTO{
            private String name;
            private String color;

            public SkillDTO(Skill skill) {

                String colorClass ="";
                if (name.equals("Jquery")){
                    colorClass = "badge bg-primary";
                }
                else if(name.equals("JavaScript")){
                    colorClass = "badge bg-secondary";
                }
                else if(name.equals("Spring")){
                    colorClass = "badge bg-success";
                }
                else if(name.equals("HTML/CSS")){
                    colorClass = "badge bg-danger";
                }
                else if(name.equals("JSP")){
                    colorClass = "badge bg-warning";
                }
                else if(name.equals("Java")){
                    colorClass = "badge bg-info";
                }
                else if(name.equals("React")){
                    colorClass = "badge bg-dark";
                }

                this.name = skill.getName();
                this.color = colorClass;
            }

        }

    }

    @Data
    public static class JobListByUserId{
        private Integer id;
        private String title;
        private String compName;
        private String task;
        private String career;

        public JobListByUserId(Integer id, String title, String compName, String task, String career) {
            this.id = id;
            this.title = title;
            this.compName = compName;
            this.task = task;
            this.career = career;
        }
    }

}
