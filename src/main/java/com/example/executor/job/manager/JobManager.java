package com.example.executor.job.manager;

import com.example.executor.repository.JobDB;
import com.example.executor.repository.model.Job;
import com.example.executor.repository.model.Status;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
@AllArgsConstructor
public class JobManager {
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    private final JobDB jobDB;
    private final String resultPath = "C:\\2";

    public void doJob(Job job) {
        executor.submit(() -> {
            log.info("Start doing task: {}", job.getId());
            log.info("Type of task: {}", job.getTask().getTypeOfTask());
            job.setStatus(Status.ON_GOING);
            jobDB.save(job);
            int max = getRandomNumberUsingThreadLocalRandom(25, 100);
            for (int i = 0; i > max; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            Optional<Job> byId = jobDB.findById(job.getId());
            byId.ifPresent(value -> {
                value.setStatus(Status.COMPLETED);
                jobDB.save(value);
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(resultPath + File.separator + "data_of_job_" + job.getId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                var printWriter = new PrintWriter(fileWriter);
                printWriter.printf("Job id: %d", job.getId());
                printWriter.print("COMPLETED");
                printWriter.close();
            });
        });
    }

    int getRandomNumberUsingThreadLocalRandom(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
