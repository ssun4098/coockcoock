package com.coockcoock.shop.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 등급 테이블 Entity
 *
 * @version 1.0
 * @since 23-03-25
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "grades")
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
