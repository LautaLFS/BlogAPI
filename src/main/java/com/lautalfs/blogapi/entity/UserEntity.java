package com.lautalfs.blogapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@Getter
@Setter
@SQLDelete(sql = "UPDATE users SET soft_delete = true WHERE id = ?")
@Where(clause = "soft_delete = false")
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "user_name", nullable = false, length = 255)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    private String about;

    @Column(name = "soft_delete")
    private boolean softDelete;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PostEntity> posts =new ArrayList<>();
}

