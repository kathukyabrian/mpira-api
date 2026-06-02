package tech.kitucode.mpira.api.domain;

import jakarta.persistence.*;
import lombok.Data;
import tech.kitucode.mpira.api.domain.enumerations.LeagueLevel;

@Data
@Entity
@Table(name = "tbl_leagues")
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private LeagueLevel level;

    @Column(name = "establishment_year")
    private Integer establishmentYear;
}
