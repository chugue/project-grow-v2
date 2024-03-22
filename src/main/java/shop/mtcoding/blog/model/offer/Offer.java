package shop.mtcoding.blog.model.offer;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.mtcoding.blog.model.jobs.Jobs;
import shop.mtcoding.blog.model.resume.Resume;

import java.sql.Timestamp;

@NoArgsConstructor
@Table(name = "offer_tb")
@Data
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jobs_id")
    private Jobs jobs;

    private Integer status;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Builder
    public Offer(Integer id, Resume resume, Jobs jobs, Integer status, Timestamp createdAt) {
        this.id = id;
        this.resume = resume;
        this.jobs = jobs;
        this.status = status;
        this.createdAt = createdAt;
    }
}
