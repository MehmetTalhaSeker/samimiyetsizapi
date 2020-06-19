package com.samimiyetsiz.samimiyetsizapi.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Hot extends AbstractEntity {

    @ManyToOne
    private Post post;
}
