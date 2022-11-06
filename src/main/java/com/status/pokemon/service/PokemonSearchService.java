package com.status.pokemon.service;

import java.util.List;

import com.status.pokemon.model.PokemonPictorialBookModel;
import com.status.pokemon.model.PokemonStatusModel;

public interface PokemonSearchService {
    public PokemonStatusModel search(Integer pokemonId, String pokemonName);

    public List<PokemonPictorialBookModel> getPokemonPictorialBook();
}