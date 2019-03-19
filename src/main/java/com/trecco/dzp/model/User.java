package com.trecco.dzp.model;

import lombok.Data;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
public class User implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    //提供主键的生成策略
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//主键

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String idCard;

    public User() {
    }

    public User(Integer id, String userName, String idCard) {
        this.id = id;
        this.userName = userName;
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + userName + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}