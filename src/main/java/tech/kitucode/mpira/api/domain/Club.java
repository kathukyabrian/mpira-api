package tech.kitucode.mpira.api.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tbl_clubs")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "establishment_year")
    private Integer establishmentYear;

    @ManyToOne
    @JoinColumn(name = "home_ground")
    private Stadium homeGround;

    @Column(name = "icon_url")
    private String iconUrl;

    @ManyToOne
    @JoinColumn(name = "league_id")
    private League league;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager managerId;
}
