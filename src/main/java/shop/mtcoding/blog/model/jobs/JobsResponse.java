package shop.mtcoding.blog.model.jobs;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import shop.mtcoding.blog.model.resume.Resume;
import shop.mtcoding.blog.model.resume.ResumeRequest;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.skill.SkillRequest;
import shop.mtcoding.blog.model.skill.SkillResponse;
import shop.mtcoding.blog.model.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JobsResponse {

    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String area;
        private String edu;
        private String career;
        private String content;
        private UserDetailDTO user;
        private List<SkillDTO> skillList;

        @Builder
        public DetailDTO(Jobs jobs, User user, List<Skill> skillList) {
            this.id = jobs.getId();
            this.title = jobs.getTitle();
            this.area = jobs.getArea();
            this.edu = jobs.getEdu();
            this.career = jobs.getCareer();
            this.content = jobs.getContent();
            this.user = new UserDetailDTO(user);
            this.skillList = skillList.stream()
                    .map(skill -> new SkillDTO(skill))
                    .collect(Collectors.toList());
        }
    }

    @Data
    public static class UserDetailDTO {
        private Integer id;
        private String compName;
        private String phone;
        private String address;
        private String homepage;
        private String imgFileName;

        @Builder
        public UserDetailDTO(User user) {
            this.id = user.getId();
            this.compName = user.getCompName();
            this.phone = user.getPhone();
            this.address = user.getAddress();
            this.homepage = user.getHomepage();
            this.imgFileName = user.getImgFileName();
        }
    }


    // 공고 리스트를 뿌려야 되는 곳에 '회사이름''공고필요기술''공고정보'를 뿌릴 수 있는 DTO
    @Data
    public static class ListDTO { //이 DTO는 (/jobs/info) 에 사용된다.
        private Integer id;
        private String title;
        private String career;
        private String area;
        private LocalDate deadline;
        private UserDTO user;
        private List<SkillDTO> skills = new ArrayList<>();

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
    public static class JobsListDTO {
        private Integer id;
        private String title;
        private String career;
        private String task;
        private UserDTO user;
        private List<SkillDTO> skills;

        @Builder
        public JobsListDTO(Jobs jobs, User user, List<Skill> skills) {
            this.id = jobs.getId();
            this.title = jobs.getTitle();
            this.career = jobs.getCareer();
            this.task = jobs.getTask();
            this.user = new UserDTO(user);

            this.skills = skills.stream()
                    .map(skill -> new SkillDTO(skill))
                    .collect(Collectors.toList());
        }
    }

    @Data
    public static class UserDTO {
        private Integer id;
        private String compName;
        private String imgFileName;

        public UserDTO(User user) {
            this.id = user.getId();
            this.compName = user.getCompName();
            this.imgFileName = user.getImgFileName();
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
                this.color = "badge badge-pill bg-success";
            } else if (this.name.equals("Oracle")) {
                this.color = "badge badge-pill bg-info";
            } else if (this.name.equals("MySql")) {
                this.color = "badge badge-pill bg-success";
            }
            // 추가 양식
            // else if (this.name.equals("언어")){
            //      this.color = "badge 컬러 " ;

        }
    }

    @Data
    public static class JobUpdateDTO {
        private Integer id;
        private String compName;
        private String phone;
        private String businessNumber;
        private String homepage;
        private String title;
        private String edu;
        private String career;
        private String content;
        private String area;
        private LocalDate deadLine;
        private String task;
        private SkillResponse.SkillCheckedDTO skillChecked;

        @Builder
        public JobUpdateDTO(Integer id, String compName, String phone, String businessNumber, String homepage, String title, String edu, String career, String content, String area, LocalDate deadLine, String task, SkillResponse.SkillCheckedDTO skillChecked) {
            this.id = id;
            this.compName = compName;
            this.phone = phone;
            this.businessNumber = businessNumber;
            this.homepage = homepage;
            this.title = title;
            this.edu = edu;
            this.career = career;
            this.content = content;
            this.area = area;
            this.deadLine = deadLine;
            this.task = task;
            this.skillChecked = skillChecked;
        }
    }

    @Data
    public static class saveDTO {
        private Integer id;
        private String compName;
        private String phone;
        private String businessNumber;
        private String homepage;
        private String title;
        private String edu;
        private String career;
        private String content;
        private String area;
        private LocalDate deadLine;
        private String task;
        private List<String> skill;
    }
}


