package spring.project.masterslave.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.project.masterslave.domain.common.BaseEntity;
import spring.project.masterslave.master.dto.MasterDTO;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_SLAVE")
@Getter
public class Slave extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SLV_ID")
    private Long id;

    @Column(name = "RANDOM_INT")
    private int randomInt;

    @Column(name = "MST_ID")
    private Long msTid;

    @Builder
    public Slave(MasterDTO masterDTO){
        this.msTid = masterDTO.getId();
        this.randomInt = masterDTO.getRandomInt();
    }
}
