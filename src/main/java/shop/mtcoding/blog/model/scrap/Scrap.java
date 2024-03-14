package shop.mtcoding.blog.model.scrap;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Table(name="scrap_tb")
@Data
@Entity
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer userId;

    private Integer resumeId;
    private Integer jobsId;

    @Column(nullable = false)
    private Timestamp createdAt;
}
