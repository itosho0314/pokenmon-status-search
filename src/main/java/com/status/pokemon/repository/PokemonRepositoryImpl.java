package com.status.pokemon.repository;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.github.oscar0812.pokeapi.models.pokemon.Pokemon;
import com.github.oscar0812.pokeapi.models.pokemon.PokemonSpecies;
import com.github.oscar0812.pokeapi.utils.Client;
import com.status.pokemon.type.PokemonListEnum;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class PokemonRepositoryImpl implements PokemonRepository{
    
    @Override
    public Pokemon findPokemonStatusById(Integer pokemonId){
        return Client.getPokemonById(pokemonId);
    }

    // フォルムチェンジも対応している->10,000代
    @Override
    public Optional<Integer> findPokemonIdByJapaneseName(String pokemonName){

        return Arrays.stream(PokemonListEnum.values())
            .filter(e->e.getJapaneseName().equals(pokemonName))
            .findFirst().map(x->x.getId());
    }

    @Override
    public PokemonSpecies findPokemonSpeciesById(Integer pokemonId){
        return Client.getPokemonSpeciesById(pokemonId);
    }

}
