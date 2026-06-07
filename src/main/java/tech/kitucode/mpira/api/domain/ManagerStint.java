package tech.kitucode.mpira.api.domain;

import jakarta.persistence.*;
import lombok.Data;
import tech.kitucode.mpira.api.domain.enumerations.ContractType;

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

    @Column(name = "club_id")
    private Long clubId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "signing_fee")
    private Double signingFee;
}
