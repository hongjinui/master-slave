package spring.project.masterslave.batch;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import spring.project.masterslave.batch.job.MasterJob;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class ScheduleRunnable implements Runnable{

    private final ApplicationContext applicationContext;
    private final String jobName;

    @Override
    public void run() {

        log.info("ScheduleRunnable run()");
        MasterJob masterJob = null;
        masterJob = applicationContext.getBean(jobName, MasterJob.class); // 잡의 각 구현객체 빈
        try {
            masterJob.execute();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
        log.info(jobName + " is started");

    }



}
