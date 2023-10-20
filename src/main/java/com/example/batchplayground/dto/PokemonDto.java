package com.example.batchplayground.dto;

import com.example.batchplayground.entity.Pokemon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonDto {

    private String name;
    private String url;

    public Pokemon toEntity() {
        return Pokemon.builder()
                .name(name)
                .url(url)
                .build();
    }

}
