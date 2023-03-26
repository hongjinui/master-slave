package spring.project.masterslave.batch.job;

import jakarta.transaction.Transactional;

import java.io.IOException;

public interface MasterJob {

    @Transactional
    void execute() throws InterruptedException, IOException;

}
