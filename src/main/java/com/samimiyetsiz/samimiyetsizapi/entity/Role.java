package com.samimiyetsiz.samimiyetsizapi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class Role extends AbstractEntity {


    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;

    public Role(RoleName name) {
        this.name = name;
    }


    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
