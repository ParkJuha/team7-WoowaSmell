package com.woowahan.smell.bazzangee.repository;

import com.woowahan.smell.bazzangee.domain.FoodCategory;
import com.woowahan.smell.bazzangee.domain.OrderFood;
import com.woowahan.smell.bazzangee.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderFoodRepository extends CrudRepository<OrderFood, Long> {
    Optional<List<OrderFood>> findByOrderedUser(User user);

    List<OrderFood> findAllByOrderedUserOrderByOrderTimeDesc(User user);

    List<OrderFood> findAllByOrderedUser(User user);

    @Query("SELECT o FROM OrderFood o WHERE o.orderedUser = :user ORDER BY SIZE (o.review.goods) DESC")
    List<OrderFood> findAllByOrderedUserOrderByGoodCountDesc(@Param("user") User user);

    @Query("SELECT o FROM OrderFood o WHERE o.orderedUser = :user AND o.review.foodCategory = :foodCategory ORDER BY SIZE (o.review.goods) DESC")
    List<OrderFood> findAllByCategoryOrderByGoodsCount(@Param("user") User user, @Param("foodCategory") FoodCategory foodCategory);
}