package spring.project.masterslave.master.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.project.masterslave.domain.entity.Master;

@Getter
@Setter
@NoArgsConstructor
public class MasterDTO {

    private Long id;
    private int randomInt;

    @Builder
    public MasterDTO(Master master){
        this.id = master.getId();
        this.randomInt = master.getRandomInt();
    }

}
