package shop.mtcoding.blog.model.user;

import lombok.Data;

public class UserRequest {


    @Data
    public static class LoginDTO{
        private String email;
        private String password;
    }
}
