package spring.project.masterslave.slave.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.project.masterslave.common.constant.EnvType;
import spring.project.masterslave.common.constant.SpringConfigPrefix;
import spring.project.masterslave.domain.entity.Slave;
import spring.project.masterslave.master.dto.MasterDTO;
import spring.project.masterslave.slave.repository.SlaveRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@Service
@ConditionalOnProperty(value = SpringConfigPrefix.PROFILE, havingValue = EnvType.SLAVE, matchIfMissing = false)
@RequiredArgsConstructor
public class SlaveService {

    private final SlaveRepository slaveRepository;

    @Transactional
    public void insertRandomList(List<MasterDTO> masterDTOList) {

        try {

            log.info("SlaveRepository insertRandomList()");

            // 랜덤 리스트 저장
            List<Slave> slaveList = masterDTOList.stream().map(Slave::new).toList();
            slaveRepository.saveAll(slaveList);

            // mstId가 가장 큰 수를 파일 offset 저장/수정
            byte[] offsetId = slaveList.get(0).getMsTid().toString().getBytes();
            offsetWriteOrUpdate(offsetId);

        } catch (Exception e) {
            log.info("SlaveService Exception");
            e.getStackTrace();
        }

    }

    private void offsetWriteOrUpdate(byte[] offsetId) throws IOException {
        String offsetFilePath = "./offset.txt";
        Path filePath = Paths.get(offsetFilePath);
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
        Files.write(filePath, offsetId);

    }
}
