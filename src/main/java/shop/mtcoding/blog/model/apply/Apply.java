package shop.mtcoding.blog.model.apply;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Table(name = "apply_tb")
@Data
@Entity
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @ManyToOne(fetch = FetchType.LAZY)
    private Integer resumeId;

//    @ManyToOne(fetch = FetchType.LAZY)
    private Integer jobsId;

    // 유저 상태값 (1 : 대기중) (2 : 확인)  (3 : 탈락)
    @Column(nullable = false)
    private String isPass;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Apply(Integer id, Integer resumeId, Integer jobsId, String isPass, Timestamp createdAt) {
        this.id = id;
        this.resumeId = resumeId;
        this.jobsId = jobsId;
        this.isPass = isPass;
        this.createdAt = createdAt;
    }
}
