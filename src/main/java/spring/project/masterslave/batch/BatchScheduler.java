package spring.project.masterslave.batch;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import spring.project.masterslave.common.constant.EnvType;
import spring.project.masterslave.common.constant.SpringConfigPrefix;
import java.util.concurrent.ScheduledFuture;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = SpringConfigPrefix.PROFILE, havingValue = EnvType.MASTER, matchIfMissing = false)
public class BatchScheduler {

    private final TaskScheduler taskScheduler;
    private final ApplicationContext applicationContext;

    public void executeJob(Runnable tasklet, long intervalTimeMills){
        log.info("Scheduling task with job start and interval time mills is " + intervalTimeMills);
        ScheduledFuture<?> scheduledTask = taskScheduler.scheduleAtFixedRate(tasklet, intervalTimeMills);
    }

    @PostConstruct
    public void init() {

        // 0.1초 마다 데이터 저장
        ScheduleRunnable scheduleRunnable = new ScheduleRunnable(applicationContext,"masterInsertJob"); // job 구현 객체 빈 이름
        executeJob(scheduleRunnable,100L);

        // 1초마다 데이터 slave 전달
        scheduleRunnable = new ScheduleRunnable(applicationContext,"masterTransmitJob"); // job 구현 객체 빈 이름
        executeJob(scheduleRunnable,1000L);

    }

}
