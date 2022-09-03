package com.lautalfs.blogapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name="comment")
@Getter
@Setter
public class CommentEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String content;

    @ManyToOne
    private PostEntity postEntity;
}
