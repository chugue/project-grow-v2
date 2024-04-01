package shop.mtcoding.blog.model.user;

import lombok.Data;

import java.time.LocalDate;

public class UserRequest {

    @Data
    public static class UpdateDTO{
        private String password;
        private String myName;
        private LocalDate birth;
        private String phone;
        private String address;
    }
  
    @Data
    public static class JoinDTO{
        private String email;
        private String myName;
        private String password;
        private String phone;
        private LocalDate birth;
        private String address;
        private String imgFileName;


        public User toEntity (Integer role){
            return User.builder()
                    .email(email)
                    .myName(myName)
                    .password(password)
                    .phone(phone)
                    .birth(birth)
                    .address(address)
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
