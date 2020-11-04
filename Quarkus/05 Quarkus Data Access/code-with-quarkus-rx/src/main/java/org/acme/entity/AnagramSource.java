package org.acme.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "anagram_source")
@Getter
@Setter
public class AnagramSource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "anagram_source_id_seq")
    public Long id;

    @Column(name = "anagram_source_text", unique = true, nullable = false)
    public String anagramSourceText;

    @OneToMany(mappedBy = "anagramSource", fetch = FetchType.LAZY)
    public List<Anagram> anagrams;
}
