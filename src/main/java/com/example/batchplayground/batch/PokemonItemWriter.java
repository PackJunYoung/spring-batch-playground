package com.example.batchplayground.batch;

import com.example.batchplayground.entity.Pokemon;
import com.example.batchplayground.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PokemonItemWriter implements ItemWriter<Pokemon> {

    private final PokemonRepository pokemonRepository;

    @Override
    public void write(List<? extends Pokemon> items) throws Exception {
        pokemonRepository.saveAll(items);
    }

}
