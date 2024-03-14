package shop.mtcoding.blog.model.jobs;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.mtcoding.blog.model.skill.SkillRequest;
import shop.mtcoding.blog.model.skill.SkillResponse;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class JobRequest {

    @Data
    public static class jobsId {
        private Integer jobsId;
    }

    @Data
    public static class JobsViewDTO {
        private Integer id;
        private Integer userId;
        private String title;
        private String edu;
        private String career;
        private String area;
        private Date deadLine;
        private List<SkillRequest.JobsSkillDTO> skillList = new ArrayList<>();
    }

    @Data
    public static class JobWriterDTO{
        private Integer id;
        private Integer userId;
        private String title;
        private String area;
        private String edu;
        private String career;
        private String content;
        private Date deadLine;
        private String task;
        private Timestamp createdAt;
        private List<String> skill;
    }

    @Data
    public static class JobUpdateDTO{
        private Integer id;
        private Integer userId;
        private String title;
        private String area;
        private String edu;
        private String career;
        private String content;
        private Date deadLine;
        private String task;
        private Timestamp createdAt;
        private List<String> skill;
    }


    @Data
    @Builder
    public static class JobJoinDTO{
        private Integer id;
        private Integer userId;
        private String compName;
        private String phone;
        private String area;
        private String edu;
        private String content;
        private String task;
        private String title;
        private String homepage;
        private String career;
        private String deadLine;
        private String businessNumber;
        private SkillResponse.SkillCheckedDTO skillChecked;
    }

    @Builder
    @Data
    public static class JobDeleteDTO{
        private Integer userId;
    }

    @Data
    @Builder
    public static class JobsJoinDTO {
        private String compName;
        private Integer userId;
        private String address;
        private String phone;
        private String area;
        private String edu;
        private String career;
        private String content;
        private String title;
        private String homepage;
        private String task;
        private String deadLine;
        private String businessNumber;
        private String photo;
    }
}
