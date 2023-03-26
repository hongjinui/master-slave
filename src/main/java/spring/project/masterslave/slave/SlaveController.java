package spring.project.masterslave.slave;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.project.masterslave.common.constant.EnvType;
import spring.project.masterslave.common.constant.SpringConfigPrefix;
import spring.project.masterslave.master.dto.MasterDTO;
import spring.project.masterslave.slave.service.SlaveService;

import java.util.List;

@Slf4j
@RestController
@ConditionalOnProperty(value = SpringConfigPrefix.PROFILE, havingValue = EnvType.SLAVE, matchIfMissing = false)
@RequestMapping("api/slave")
@RequiredArgsConstructor
public class SlaveController {

    private final SlaveService slaveService;
    /***
     * @description 랜덤 리스트 저장
     * @param httpServletRequest
     * @param masterDTOList
     * @return null
     */
    @PostMapping("random-list")
    public ResponseEntity<?> insertRandomList(HttpServletRequest httpServletRequest,@RequestBody List<MasterDTO> masterDTOList) {

        log.info("SlaveController insertAddress()");
        slaveService.insertRandomList(masterDTOList);

        return null;
    }

}