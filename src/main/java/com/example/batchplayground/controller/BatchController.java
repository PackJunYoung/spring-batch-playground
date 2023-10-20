package com.example.batchplayground.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BatchController {

    private final JobLauncher jobLauncher;
    private final Job importPokemonJob;

    @PostMapping("/runBatchJob")
    public BatchStatus runBatchJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        JobExecution jobExecution = jobLauncher.run(importPokemonJob, jobParameters);
        return jobExecution.getStatus();
    }

}
