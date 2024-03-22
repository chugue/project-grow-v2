package shop.mtcoding.blog.model.resume;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.model.apply.Apply;
import shop.mtcoding.blog.model.offer.Offer;
import shop.mtcoding.blog.model.scrap.Scrap;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.user.User;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Table(name = "resume_tb")
@Data
@Entity
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String title;
    private String area;
    private String edu;
    private String career;
    private String introduce;
    private String portLink;

    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Skill> skillList = new ArrayList<>();

    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Apply> applyList = new ArrayList<>();

    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Scrap> scrapList = new ArrayList<>();

    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Offer> offerList = new ArrayList<>();

    @CreationTimestamp //pc -> db 날짜주입
    private Timestamp createdAt;

    @Builder
    public Resume(Integer id, User user, String title, String area, String edu, String career, String introduce, String portLink, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.area = area;
        this.edu = edu;
        this.career = career;
        this.introduce = introduce;
        this.portLink = portLink;
        this.createdAt = createdAt;
    }
}
