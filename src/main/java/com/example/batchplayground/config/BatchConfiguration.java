package com.example.batchplayground.config;

import com.example.batchplayground.batch.PokemonItemReader;
import com.example.batchplayground.batch.PokemonItemWriter;
import com.example.batchplayground.entity.Pokemon;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class BatchConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final PokemonItemWriter pokemonItemWriter;

    @Bean
    public PokemonItemReader pokemonItemReader() {
        return new PokemonItemReader();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Pokemon, Pokemon>chunk(10)
                .reader(pokemonItemReader())
                .writer(pokemonItemWriter)
                .build();
    }

    @Bean
    public Job importPokemonJob() {
        return jobBuilderFactory.get("importPokemonJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }

}
