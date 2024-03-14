package shop.mtcoding.blog.model.skill;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.resume.Resume;

@Table(name = "skill_tb")
@Getter
@Setter
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer resumeId;

    private Integer jobsId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false) // 1: 유저, 2: 기업
    private Integer role;

    private String color;

}
