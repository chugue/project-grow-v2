package shop.mtcoding.blog.model.offer;

import lombok.AllArgsConstructor;
import lombok.Data;
import shop.mtcoding.blog.model.skill.SkillRequest;

import java.util.List;

public class OfferResponse {
    @AllArgsConstructor
    @Data
    public static class OfferDetailDTO {
        private Integer id;
        private Boolean isScrap;
    }

    @Data
    public static class OfferListByUserId{
        private Integer id;
        private String title;
        private String compName;
        private String task;
        private String career;
        private List<SkillRequest.JobSkillDTO> skillList;

        public OfferListByUserId(Integer id, String title, String compName, String task, String career) {
            this.id = id;
            this.title = title;
            this.compName = compName;
            this.task = task;
            this.career = career;
        }
    }
}