package shop.mtcoding.blog.model.jobs;

import lombok.Builder;
import lombok.Data;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.user.User;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JobsResponse {

    @Data
    public static class ListDTO {
        private Integer id;
        private String title;
        private String career;
        private String area;
        private Date deadline;
        private UserDTO user;
        private List<SkillDTO> skillList;

        @Builder
        public ListDTO(Integer id, String title, String career, String area, Date deadline, User user, List<Skill> skills) {
            this.id = id;
            this.title = title;
            this.career = career;
            this.area = area;
            this.deadline = deadline;
            this.user = new UserDTO(user.getId(), user.getMyName());

            this.skillList = skills.stream()
                    .map(skill -> new SkillDTO(skill.getId(), skill.getName(), skill.getColor()))
                    .collect(Collectors.toList());
        }
    }

    @Data
    public static class UserDTO {
        private Integer id;
        private String myName;

        public UserDTO(Integer id, String myName) {
            this.id = id;
            this.myName = myName;
        }
    }

    @Data
    public static class SkillDTO {
        private Integer id;
        private String name;
        private String color;

        public SkillDTO(Integer id, String name, String color) {
            this.id = id;
            this.name = name;
            this.color = color;
        }
    }

}
