package spring.project.masterslave.batch.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import spring.project.masterslave.common.constant.EnvType;
import spring.project.masterslave.common.constant.SpringConfigPrefix;
import spring.project.masterslave.domain.entity.Master;
import spring.project.masterslave.master.dto.MasterDTO;
import spring.project.masterslave.master.repository.MasterRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = SpringConfigPrefix.PROFILE, havingValue = EnvType.MASTER, matchIfMissing = false)
public class MasterTransmitJob implements MasterJob {

    private final MasterRepository masterRepository;

    @Override
    public void execute() throws IOException {
        log.info("MasterTransmitJob execute()");

        String offsetFilePath = "./offset.txt";
        Path filePath = Paths.get(offsetFilePath);
        String url = "http://localhost:9000/api/slave/random-list";

        List<Master> masterList = new ArrayList<>();

        if (Files.notExists(filePath)) {
            masterList = masterRepository.findAllByOrderByIdDesc();
        } else {
            Long id = Long.parseLong(Files.readString(filePath));
            masterList = masterRepository.findAllByIdGreaterThanOrderByIdDesc(id);
        }
        List<MasterDTO> masterDTOList = masterList.stream().map(MasterDTO::new).toList();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<MasterDTO>> httpEntity = new HttpEntity<>(masterDTOList, httpHeaders);

        restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        log.info("MasterTransmitJob end");
    }

    private String convertDtoListToJsonArray(List<MasterDTO> masterDTOList) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        return objectMapper.writeValueAsString(masterDTOList);

    }
}
