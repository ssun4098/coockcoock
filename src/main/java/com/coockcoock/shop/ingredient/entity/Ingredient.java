package com.coockcoock.shop.ingredient.entity;

import lombok.*;

import javax.persistence.*;

/**
 * 재로 Entity
 *
 * @since 23-06-06
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
