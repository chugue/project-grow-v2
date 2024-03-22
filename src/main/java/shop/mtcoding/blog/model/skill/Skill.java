package shop.mtcoding.blog.model.skill;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.blog.model.comp.CompRequest;
import shop.mtcoding.blog.model.resume.Resume;

@NoArgsConstructor
@Table(name = "skill_tb")
@Getter
@Setter
@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;
//    private Integer resumeId;
//    private Integer jobsId;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false) // 1: 유저, 2: 기업
    private Integer role;
    private String color;

    @Builder
    public Skill(Integer id, Resume resume, String name, Integer role, String color) {
        this.id = id;
        this.resume = resume;
        this.name = name;
        this.role = role;
        this.color = color;
    }
}
