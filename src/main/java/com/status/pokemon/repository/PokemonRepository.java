package com.status.pokemon.repository;

import java.util.Optional;
import com.github.oscar0812.pokeapi.models.pokemon.Pokemon;
import com.github.oscar0812.pokeapi.models.pokemon.PokemonSpecies;


public interface PokemonRepository {
    public Pokemon findPokemonStatusById(Integer pokemonId);
    public Optional<Integer> findPokemonIdByJapaneseName(String pokemonName);
    public PokemonSpecies findPokemonSpeciesById(Integer pokemonId);
}