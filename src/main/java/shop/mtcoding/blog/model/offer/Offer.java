package shop.mtcoding.blog.model.offer;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.mtcoding.blog.model.jobs.Jobs;

import java.sql.Timestamp;

@NoArgsConstructor
@Table(name = "offer_tb")
@Data
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer resumeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Jobs jobs;

    private Integer status;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Builder
    public Offer(Integer id, Integer resumeId, Jobs jobs, Integer status, Timestamp createdAt) {
        this.id = id;
        this.resumeId = resumeId;
        this.jobs = jobs;
        this.status = status;
        this.createdAt = createdAt;
    }
}
