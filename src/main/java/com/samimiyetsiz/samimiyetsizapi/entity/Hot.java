package com.samimiyetsiz.samimiyetsizapi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Hot extends AbstractEntity {

    @ManyToOne
    private Post post;
}
