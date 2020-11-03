package org.acme.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Anagram {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "anagram_anagram_id_seq")
    @Column(name = "anagram_id", nullable = false)
    public Long id;

    @Column(name = "anagram_text")
    public String anagramText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anagram_source_id")
    public AnagramSource anagramSource;
}
