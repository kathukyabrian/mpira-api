package tech.kitucode.mpira.api.domain;

import jakarta.persistence.*;
import lombok.Data;
import tech.kitucode.mpira.api.domain.enumerations.ContractType;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tbl_player_stints")
public class PlayerStint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "club_id")
    private Long clubId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "signing_fee")
    private Double signingFee;

    @Enumerated(EnumType.STRING)
    @Column(name = "contract_type")
    private ContractType contractType;

    @Column(name = "active")
    private Boolean active;
}
