package shop.mtcoding.blog.model.comp;

import lombok.Data;
import shop.mtcoding.blog.model.skill.SkillRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
public class CompRequest {


    @Data
    public static class JobsViewDTO {
        private Integer id;
        private Integer userId;
        private String compName;
        private String title;
        private String task;
        private String career;
        private List<SkillRequest.JobSkillDTO> skillList = new ArrayList<>();
        private Integer number;
    }

    @Data
    public static class ResumeViewDTO {
        private Integer id;
        private Integer userId;
        private String myName;
        private String title;
        private String edu;
        private String career;
        private String area;
        private String imgFileName;
        private List<SkillRequest.CompskillDTO> skillList = new ArrayList<>();
    }

    @Data
    public static class UserApplyDTO {
        private Integer id;
        private Integer resumeId;
        private String name;
        private String title;
        private String career;
        private List<SkillRequest.JobSkillDTO> skillList = new ArrayList<>();
    }

    @Data
    public static class UpdateDTO{
        private String myName;
        private String password;
        private String compName;
        private String phone;
        private String address;
        private Date birth;
        private String businessNumber;
        private String homepage;

    }
}