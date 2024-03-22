package shop.mtcoding.blog.model.apply;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

public class ApplyRequest {

    @AllArgsConstructor
    @Data
    public static class ApplyResumeJobsDTO {
        private Integer resumeId;
        private Integer userId;
        private Timestamp createdAt;
        private String area;
        private String career;
        private String edu;
        private String introduce;
        private String portLink;
        private String title;
        private String isPass;
        private Integer jobsId;
    }

    @Data
    public static class saveDTO {
        private Integer resumeId;
        private Integer jobsId;
        private String isPass;

        public saveDTO() {
            this.isPass = "대기중";
        }
    }


    @AllArgsConstructor
    @Data
    public static class JobsIdAndResumeIdDTO {
        private String isPass;
    }

    @AllArgsConstructor
    @Data
    public static class ApplyResumeJobsDTO2 {
        private Date deadLine;
        private Integer id;
        private Integer userId;
        private Timestamp createdAt;
        private String area;
        private String career;
        private String content;
        private String edu;
        private String task;
        private String title;
        private Integer resumeId;
        private Integer status;
    }
}