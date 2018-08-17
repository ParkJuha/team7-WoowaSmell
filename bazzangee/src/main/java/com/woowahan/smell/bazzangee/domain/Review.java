package com.woowahan.smell.bazzangee.domain;

import com.woowahan.smell.bazzangee.dto.ReviewDto;
import com.woowahan.smell.bazzangee.exception.NotMatchException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Slf4j
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "user_id"))
    private User user;

    @Column
    @Lob
    private String contents;

    @Column
    private String imageUrl;

    @Column
    private double starPoint;
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Good> goods;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_review_food_category"))
    private FoodCategory foodCategory;
    @Column
    private boolean isDeleted;

    public Review(User user, String contents, String imageUrl, double starPoint) {
        this.user = user;
        this.contents = contents;
        this.imageUrl = imageUrl;
        this.starPoint = starPoint;
    }

    public void delete(User loginUser) {
        if(!loginUser.equals(this.user))
            throw new NotMatchException("글쓴이가 아닙니다.");
        this.isDeleted = true;
    }

    public void update(ReviewDto reviewDto, User loginUser) {
//        if(!this.user.equals(loginUser))
        log.info("loginUser : {}", loginUser);
        log.info("user : {}", this.user);
        if(!loginUser.equals(this.user))
            throw new NotMatchException("타인의 리뷰는 수정할 수 없습니다.");

        this.contents = reviewDto.getContents();
        log.info("contents : {}", contents);
        this.imageUrl = reviewDto.getImage().getOriginalFilename();
        log.info("contents : {}", imageUrl);
        this.starPoint = reviewDto.getStarPoint();
        log.info("contents : {}", starPoint);
    }
}

