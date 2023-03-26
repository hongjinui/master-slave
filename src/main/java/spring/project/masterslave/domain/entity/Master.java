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
@Table(name = "TB_MASTER")
@Getter
public class Master extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MST_ID")
    private Long id;

    @Column(name = "RANDOM_INT")
    private int randomInt;

    @Builder
    public Master(int randomInt){
        this.randomInt = randomInt;
    }
    @Builder
    public Master(MasterDTO masterDTO){
        this.id = masterDTO.getId();
        this.randomInt = masterDTO.getRandomInt();
    }

}
