package shop.mtcoding.blog.model.offer;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Table(name = "offer_tb")
@Data
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer resumeId;

    @Column(nullable = false)
    private Integer jobsId;
    private Integer status;

    @Column(nullable = false)
    private Timestamp createdAt;

}
