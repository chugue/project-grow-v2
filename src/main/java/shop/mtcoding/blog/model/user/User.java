package shop.mtcoding.blog.model.user;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.skill.Skill;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Table(name = "user_tb")
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String myName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String phone;
    private String address;
    private Date birth;
    @Column(unique = true)
    private String businessNumber;
    private String photo;
    private String compName;
    private String homepage;
    @Column(nullable = false)
    private Integer role;
    @Column(nullable = false)
    private Timestamp createdAt;


    @Column
    @ColumnDefault("'1e308313-4d3a-4997-b42c-d409e72034ec_noimage.png'")
    private String imgFileName;

    @Builder
    public User(Integer id, String email, String myName, String password, String phone, String address, Date birth, String businessNumber, String photo, String compName, String homepage, Integer role, Timestamp createdAt, String imgFileName) {
        this.id = id;
        this.email = email;
        this.myName = myName;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.birth = birth;
        this.businessNumber = businessNumber;
        this.compName = compName;
        this.homepage = homepage;
        this.role = role;
        this.createdAt = createdAt;
        this.imgFileName = imgFileName;
    }
}



