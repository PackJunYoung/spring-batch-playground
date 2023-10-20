package com.example.batchplayground.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonResponseDto {

    private int count;
    private int next;
    private List<PokemonDto> results;

}
