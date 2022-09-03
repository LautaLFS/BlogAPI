package com.lautalfs.blogapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "post")
@Getter
@Setter
@NoArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "post_tile", length = 100, nullable = false)
    private String title;

    @Column(length = 10000)
    private String content;

    private String imageName;

    private Date addedDate;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "postEntity", cascade = CascadeType.ALL)
    private Set<CommentEntity> comments = new HashSet<>();

}
