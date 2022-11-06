package com.status.pokemon.model;

import java.util.Objects;

import com.github.oscar0812.pokeapi.models.pokemon.Pokemon;
import com.github.oscar0812.pokeapi.models.pokemon.PokemonStat;

import lombok.Getter;

@Getter
public class PokemonStatusModel {

    private final Integer pokemonId;
    private final String pokemonName;
    private final String pokemonImage;
    private Integer hitPoint = 0;
    private Integer attack = 0;
    private Integer defense = 0;
    private Integer specialAttack = 0;
    private Integer specialDefense = 0;
    private Integer speed = 0;

    public PokemonStatusModel(String pokemonName, Pokemon pokemon) {
        this.pokemonId = pokemon.getId();
        this.pokemonName = pokemonName;
        this.pokemonImage = pokemon.getSprites().getFrontDefault();

        for (PokemonStat status : pokemon.getStats()) {
            if (Objects.equals("hp", status.getStat().getName())) {
                this.hitPoint = status.getBaseStat();
            }
            if (Objects.equals("attack", status.getStat().getName())) {
                this.attack = status.getBaseStat();
            }
            if (Objects.equals("defense", status.getStat().getName())) {
                this.defense = status.getBaseStat();
            }
            if (Objects.equals("special-attack", status.getStat().getName())) {
                this.specialAttack = status.getBaseStat();
            }
            if (Objects.equals("special-defense", status.getStat().getName())) {
                this.specialDefense = status.getBaseStat();
            }
            if (Objects.equals("speed", status.getStat().getName())) {
                this.speed = status.getBaseStat();
            }
        }
    }

    public Integer getMaxSpeed() {
        return (int) Math.floor((((this.speed * 2 + 31 +252 / 4 ) * 0.5) + 5) * 1.1);
    }

    public Integer getSecondSpeed() {
        return (int) Math.floor((((this.speed * 2 + 31 +252 / 4 ) * 0.5) + 5) * 1.0);
    }

    public Integer getNormalSpeed() {
        return (int) Math.floor((((this.speed * 2 + 31 + 0 / 4 ) * 0.5) + 5) * 1.0);
    }

    public Integer getSlowestSpeed() {
        return (int) Math.floor((((this.speed * 2 + 0 + 0 / 4 ) * 0.5) + 5) * 0.9);
    } 

}
