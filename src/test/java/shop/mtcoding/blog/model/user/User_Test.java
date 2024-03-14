
// package shop.mtcoding.blog.model.user;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import java.util.ArrayList;
// import java.util.List;

// @SpringBootTest
// public class User_Test {

//     @Autowired
//     UserRepository userRepository;


//   package shop.mtcoding.blog.model.user;
//
//   import org.junit.jupiter.api.Test;
//   import org.springframework.beans.factory.annotation.Autowired;
//   import org.springframework.boot.test.context.SpringBootTest;
//
//   import java.util.ArrayList;
//   import java.util.List;
//
//   @SpringBootTest
//   public class User_Test {
//
//    @Autowired
//    UserRepository userRepository;
//

//    @Test
//    public void user_test(){
//        List<User> userList = userRepository.findAll();
//        System.out.println(userList);
//        List<UserRequest.UserAllDTO> userDTOList = new ArrayList<>();
//
//        for (User user : userList){
//           userDTOList.add(UserRequest.UserAllDTO.builder()
//                   .id(user.getId())
//                   .userid(user.getUserid())
//                   .myName(user.getMyName())
//                   .password(user.getPassword())
//                   .email(user.getEmail())
//                   .phone(user.getPhone())
//                   .address(user.getAddress())
//                   .businessNumber(user.getBusinessNumber())
//                   .compName(user.getCompName())
//                   .homepage(user.getHomepage())
//                   .role(user.getRole())
//                   .createdAt(user.getCreatedAt())
//                   .build()
//
//           );
//
//            System.out.println(userDTOList);
//        }
//
//    }

//  }

//}