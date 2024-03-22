package shop.mtcoding.blog.model.jobs;

import jakarta.persistence.*;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.model.skill.Skill;
import shop.mtcoding.blog.model.user.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.blog.model.apply.Apply;
import shop.mtcoding.blog.model.offer.Offer;
import shop.mtcoding.blog.model.scrap.Scrap;
import shop.mtcoding.blog.model.skill.Skill;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Table(name = "jobs_tb")
@Entity
public class Jobs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private String area;

    private String title;

    @Column(nullable = false)
    private String edu;

    @Column(nullable = false)
    private String career;


    @OneToMany(mappedBy = "jobs", fetch = FetchType.LAZY)
    private List<Skill> skillList = new ArrayList<>();

    @OneToMany(mappedBy = "jobs", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Apply> applyList = new ArrayList<>();

    @OneToMany(mappedBy = "jobs", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Scrap> scrapList = new ArrayList<>();

    @OneToMany(mappedBy = "jobs", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Offer> offerList = new ArrayList<>();


    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Date deadLine;

    @Column(nullable = false)
    private String task;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Jobs(Integer id, User user, String area, String title, String edu, String career,String content, Date deadLine, String task, Timestamp createdAt) {
        this.id = id;
        this.user = user;
        this.area = area;
        this.title = title;
        this.edu = edu;
        this.career = career;
        this.content = content;
        this.deadLine = deadLine;
        this.task = task;
        this.createdAt = createdAt;
    }
}

