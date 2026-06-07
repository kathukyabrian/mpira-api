package tech.kitucode.mpira.api.domain;

import jakarta.persistence.*;
import lombok.Data;
import tech.kitucode.mpira.api.domain.enumerations.PlayerPosition;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tbl_players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "icon_url")
    private String iconUrl;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "country")
    private String country;

    @ManyToOne
    @JoinColumn(name = "current_club")
    private Club club;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private PlayerPosition position;

    @Column(name = "jersey_number")
    private Integer jerseyNumber;

    @Column(name = "alumni")
    private String alumni;
}
