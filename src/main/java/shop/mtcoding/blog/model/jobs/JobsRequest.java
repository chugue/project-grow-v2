package shop.mtcoding.blog.model.jobs;

import lombok.Data;
import shop.mtcoding.blog.model.user.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class JobsRequest {

    // 공고작성 DTO
    @Data
    public static class JobWriterDTO{
        private String title;
        private String area;
        private String edu;
        private String career;
        private String content;
        private LocalDate deadLine;
        private String task;
        private List<String> skill;

        public Jobs toEntity(User user) {
            return Jobs.builder()
                    .title(title)
                    .area(area)
                    .edu(edu)
                    .career(career)
                    .content(content)
                    .deadline(deadLine)
                    .task(task)
                    .user(user)
                    .build();
        }
    }
}
