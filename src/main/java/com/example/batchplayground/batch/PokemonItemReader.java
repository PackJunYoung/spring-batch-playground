package com.example.batchplayground.batch;

import com.example.batchplayground.dto.PokemonDto;
import com.example.batchplayground.dto.PokemonResponseDto;
import com.example.batchplayground.entity.Pokemon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
public class PokemonItemReader implements ItemReader<Pokemon> {

    private static final String API_URL = "https://pokeapi.co/api/v2/pokemon?limit=10000&offset=0";

    private RestTemplate restTemplate;
    private int nextIndex;
    private List<PokemonDto> pokemonList;

    public PokemonItemReader() {
        restTemplate = new RestTemplate();
        nextIndex = 0;
        pokemonList = fetchPokemonData();
    }

    private List<PokemonDto> fetchPokemonData() {
        PokemonResponseDto response = restTemplate.getForObject(API_URL, PokemonResponseDto.class);
        if (response == null) return List.of();

        return response.getResults();
    }

    @Override
    public Pokemon read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (nextIndex < pokemonList.size()) {
            return pokemonList.get(nextIndex++).toEntity();
        } else {
            return null;
        }
    }

}
