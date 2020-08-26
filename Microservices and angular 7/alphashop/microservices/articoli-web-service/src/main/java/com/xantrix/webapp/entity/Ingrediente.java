package com.xantrix.webapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "INGREDIENTI")
@Data
public class Ingrediente implements Serializable {
    private static final long serialVersionUID = -6597932485001138522L;

    @Id
    @Column(name = "CODART")
    private String codArt;

    @Column(name = "INFO")
    private String info;

    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonBackReference
    private Articolo articolo;
}
