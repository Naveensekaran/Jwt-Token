package com.example.JwtToken.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.example.JwtToken.common.Constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "User", schema = "tok")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private String isActive;

    @Column(name = "user_type")
    private String userType = Constant.USER_TYPE.NORMAL;

    @Column(name = "login_count")
    private String loginCount = "0";

    @Column(name = "login_at")
    private LocalDateTime loginAt;

    @Column(name = "sso_type")
    private String ssoType;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onSave(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        this.createdAt = currentDateTime;
    }

    @PostPersist
    public void onUpdate(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        this.updatedAt = currentDateTime;
    }
}
