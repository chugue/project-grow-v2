package shop.mtcoding.blog.model.jobs;

import lombok.Builder;
import lombok.Data;
import org.apache.catalina.util.ToStringUtil;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JobsResponse {

    // 공고 리스트를 뿌려야 되는 곳에 '회사이름''공고필요기술''공고정보'를 뿌릴 수 있는 DTO
    @Data
    public static class ListDTO { //이 DTO는 (/jobs/info) 에 사용된다.
        private Integer id;
        private String title;
        private String career;
        private String area;
        private LocalDate deadline;
        private UserDTO user;
        private List<SkillDTO> skills;

        @Builder
        public ListDTO(Jobs jobs, User user, List<Skill> skills) {
            this.id = jobs.getId();
            this.title = jobs.getTitle();
            this.career = jobs.getCareer();
            this.area = jobs.getArea();
            this.deadline = jobs.getDeadline();
            this.user = new UserDTO(user);

            this.skills = skills.stream()
                    .map(skill -> new SkillDTO(skill))
                    .collect(Collectors.toList());
        }
    }

    @Data
    public static class UserDTO {
        private Integer id;
        private String myName;

        public UserDTO(User user) {
            this.id = user.getId();
            this.myName = user.getMyName();
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
                this.color = "badge bg-primary";
            } else if (this.name.equals("JavaScript")) {
                this.color = "badge bg-secondary";
            } else if (this.name.equals("Spring")) {
                this.color = "badge bg-success";
            } else if (this.name.equals("HTML/CSS")) {
                this.color = "badge bg-danger";
            } else if (this.name.equals("JSP")) {
                this.color = "badge bg-warning";
            } else if (this.name.equals("Java")) {
                this.color = "badge bg-info";
            } else if (this.name.equals("React")) {
                this.color = "badge bg-dark";
            }
            // 추가 양식
            // else if (this.name.equals("언어")){
            //      this.color = "badge 컬러 " ;

        }
    }
}


