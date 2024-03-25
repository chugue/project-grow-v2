package shop.mtcoding.blog.model.user;

import lombok.Data;

import java.sql.Date;

public class UserRequest {

    @Data
    public static class JoinDTO{
        private String email;
        private String myName;
        private String password;
        private String phone;

        public User toEntity (Integer role){
            return User.builder()
                    .email(email)
                    .myName(myName)
                    .password(password)
                    .phone(phone)
                    .role(role)
                    .build();
        }
    }

    @Data
    public static class LoginDTO{
        private String email;
        private String password;
    }
}
