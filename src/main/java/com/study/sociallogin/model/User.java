package com.study.sociallogin.model;

import com.study.sociallogin.type.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User extends BaseEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String userId;

    @Column(length = 50)
    private String userName;

    @Column(length = 50)
    private String userEmail;

    @Column(columnDefinition = "ENUM('KAKAO', 'NAVER', 'FACEBOOK', 'GOOGLE', 'PAYCO', 'NORMAL') DEFAULT 'NORMAL'")
    @Enumerated(EnumType.STRING)
    private UserType userType;

}
