package com.samimiyetsiz.samimiyetsizapi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Vote extends AbstractEntity {

    @Column(nullable = false)
    private boolean up;
    @ManyToOne
    private Post post;
    @ManyToOne
    private Comment comment;
    @ManyToOne
    private Memer memer;

}
