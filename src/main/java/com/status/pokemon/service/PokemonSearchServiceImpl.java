package com.status.pokemon.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.github.oscar0812.pokeapi.models.pokemon.Pokemon;
import com.status.pokemon.model.PokemonPictorialBookModel;
import com.status.pokemon.model.PokemonStatusModel;
import com.status.pokemon.repository.PokemonRepository;
import com.status.pokemon.type.PokemonListEnum;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PokemonSearchServiceImpl implements PokemonSearchService {

    private final PokemonRepository pokemonRepository;

    @Override
    public PokemonStatusModel search(Integer pokemonId, String pokemonName) {
        Pokemon pokemon = pokemonRepository.findPokemonStatusById((pokemonId));
        return new PokemonStatusModel(pokemonName, pokemon);
    }

    @Override
    public List<PokemonPictorialBookModel> getPokemonPictorialBook() {

        return Arrays.stream(PokemonListEnum.values())
                .map(e -> new PokemonPictorialBookModel(e.getId(), e.getJapaneseName()))
                .collect(Collectors.toList());
    }
}
