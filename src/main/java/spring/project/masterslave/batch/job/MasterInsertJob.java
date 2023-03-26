package spring.project.masterslave.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import spring.project.masterslave.common.constant.EnvType;
import spring.project.masterslave.common.constant.SpringConfigPrefix;
import spring.project.masterslave.domain.entity.Master;
import spring.project.masterslave.master.repository.MasterRepository;

import java.util.Random;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = SpringConfigPrefix.PROFILE, havingValue = EnvType.MASTER, matchIfMissing = false)
public class MasterInsertJob implements MasterJob{

    private final MasterRepository masterRepository;

    @Override
    public void execute() {
        log.info("MasterInsertJob execute()");

        Random random = new Random();
        Master master = Master.builder()
                .randomInt(random.nextInt())
                .build();
        masterRepository.save(master);
        log.info("MasterInsertJob end");

    }
}
