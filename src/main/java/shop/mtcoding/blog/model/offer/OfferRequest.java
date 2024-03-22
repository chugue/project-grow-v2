package shop.mtcoding.blog.model.offer;

import lombok.Data;
import shop.mtcoding.blog.model.skill.SkillRequest;

import java.util.List;

public class OfferRequest {

    @Data
    public static class CompOfterDTO {
        private Integer id; // apply 의 키값
        private String myName;
        private String title;
        private String career;
        private Integer resumeId;
        private Integer status;
        private List<SkillRequest.ApplyskillDTO> skillList;

        public CompOfterDTO(Integer id, String myName, String title, String career, Integer resume_id, Integer status) {
            this.id = id;
            this.myName = myName;
            this.title = title;
            this.career = career;
            this.resumeId = resume_id;
            this.status = status;
        }
    }

    @Data
    public static class SaveDTO {
        private Integer jobsId;
        private Integer resumeId;
    }

    @Data
    public static class DeleteDTO {
        private Integer id;
        private Integer jobsId;
        private Integer resumeId;
        private Integer status;
    }
}