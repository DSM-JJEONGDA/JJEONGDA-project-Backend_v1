package com.example.loginpractice.entity.diary;

import com.example.loginpractice.entity.user.User;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name = "diary")
public class DiaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String title;

    @Column(nullable = false, length = 20)
    private String weather;

    @Column(nullable = false, length = 200)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public DiaryEntity updateDiary(String title, String contents, String weather){
        this.contents = contents;
        this.title = title;
        this.weather = weather;
        return this;
    }
}