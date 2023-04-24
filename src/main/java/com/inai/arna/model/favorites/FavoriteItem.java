package com.inai.arna.model.favorites;

import com.inai.arna.model.Item;
import com.inai.arna.model.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "favourite_items")
@Data
@NoArgsConstructor
@IdClass(FavoriteItemId.class)
public class FavoriteItem {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Id
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column(name = "news_id")
    private Integer itemId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "news_id", insertable = false, updatable = false)
    private Item item;
}
