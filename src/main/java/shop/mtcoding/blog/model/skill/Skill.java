package shop.mtcoding.blog.model.skill;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
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

    @Builder
    public Skill(Integer id, Integer resumeId, Integer jobsId, String name, Integer role, String color) {
        this.id = id;
        this.resumeId = resumeId;
        this.jobsId = jobsId;
        this.name = name;
        this.role = role;
        this.color = color;
    }
}
