package shop.mtcoding.blog.model.skill;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.blog.model.comp.CompRequest;
import shop.mtcoding.blog.model.jobs.Jobs;
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


    @JoinColumn(name = "resume_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @JoinColumn(name = "jobs_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Jobs jobs;

    @Column(nullable = false)
    private String name;
    private String color;
    @Column(nullable = false) // 1: 유저, 2: 기업
    private Integer role;

    @Builder
    public Skill(Integer id, Resume resume, Jobs jobs, String name, String color, Integer role) {
        this.id = id;
        this.resume = resume;
        this.jobs = jobs;
        this.name = name;
        this.color = color;
        this.role = role;
    }
}
