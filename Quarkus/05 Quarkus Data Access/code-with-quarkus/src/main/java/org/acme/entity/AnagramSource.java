package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Sort;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "anagram_source")
@Getter
@Setter
public class AnagramSource extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "anagram_source_id_seq")
    public Long id;

    @Column(name = "anagram_source_text", unique = true, nullable = false)
    public String anagramSourceText;

    @OneToMany(mappedBy = "anagramSource", fetch = FetchType.LAZY)
    public List<Anagram> anagrams;

    public static List<AnagramSource> findByValue(String value) {
        return PanacheEntityBase.<AnagramSource>findAll(Sort.ascending("anagram_source_text"))
                .list();
    }
}
