package shop.mtcoding.blog.model.jobs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.user.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Table(name = "jobs_tb")
@Getter
@Setter
@Entity
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    @Column(nullable = false)
    private String area;

    private String title;

    @Column(nullable = false)
    private String edu;

    @Column(nullable = false)
    private String career;

//    @OneToMany(mappedBy = "jobs", fetch = FetchType.LAZY)
//    private List<Skill> skillList = new ArrayList<>();

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Date deadLine;

    @Column(nullable = false)
    private String task;

    @Column(nullable = false)
    private Timestamp createdAt;
}

