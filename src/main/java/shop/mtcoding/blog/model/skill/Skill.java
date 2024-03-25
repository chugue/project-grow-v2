package shop.mtcoding.blog.model.skill;


import jakarta.persistence.*;
import lombok.*;
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

 // 스킬테이블은 이력서와 공고 중 하나랑만? 어쩌구 하기 때문에 이력서와 공고 테이블ㅇ 조인컬럼에 널어블 트루를 해줘야해요. 왜냐면 폴스가 디폴트거든요
    @JoinColumn(name = "resume_id", nullable=true)
    @ManyToOne(fetch = FetchType.LAZY)
    private Resume resume;

    @JoinColumn(name = "jobs_id" , nullable=true) // nullable 스펠링 틀린가요
    @ManyToOne(fetch = FetchType.LAZY)
    private Jobs jobs; // 얘가 공고죠

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
