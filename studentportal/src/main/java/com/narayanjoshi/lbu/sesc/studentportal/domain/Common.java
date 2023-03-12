package com.narayanjoshi.lbu.sesc.studentportal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.*;

@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@NoArgsConstructor
public abstract class Common implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private long id;

}
