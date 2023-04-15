package com.coockcoock.shop.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * 회원 테이블 Entity
 *
 * @version 1.0
 * @since 23-03-25
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "members")
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id", unique = true, length = 32)
    private String loginId;

    @Column(name = "password", length = 256)
    private String password;

    @Column(name = "sign_up_date")
    private LocalDate signUpDate;

    @Column(name = "ban")
    private boolean ban;

    @Column(name = "ban_reason", length = 256)
    private String banReason;

    @Column(name = "secession")
    private boolean secession;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;

    public void changePassword(String password) {
        this.password = password;
    }
}
