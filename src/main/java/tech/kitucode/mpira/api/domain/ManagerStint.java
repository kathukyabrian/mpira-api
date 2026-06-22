package tech.kitucode.mpira.api.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tbl_manager_stints")
public class ManagerStint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "manager_id")
    private Long managerId;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "signing_fee")
    private Double signingFee;

    @Column(name = "active")
    private Boolean active;
}
