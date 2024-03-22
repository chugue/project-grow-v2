package shop.mtcoding.blog.model.help;

import lombok.Data;

public class HelpRequest {
    @Data
    private static class HelpDTO{
        private Integer id;
        private String title;
        private String content;
        private String username;
    }

}
