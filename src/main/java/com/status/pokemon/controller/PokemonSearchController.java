package com.status.pokemon.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.status.pokemon.service.PokemonSearchService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@AllArgsConstructor
@CrossOrigin
public class PokemonSearchController {

	private final PokemonSearchService pokemonSearchService;


	@GetMapping(value = "/search")
	public Mono search(@RequestParam Integer id,@RequestParam String pokemonName) {
		return Mono.defer(() -> {
			return Mono.just(pokemonSearchService.search(id,pokemonName));
		}).subscribeOn(Schedulers.boundedElastic());

	}

	@GetMapping(value = "/list")
	public Mono getPokemonListEnum() {
		return Mono.defer(() -> {
			return Mono.just(pokemonSearchService.getPokemonPictorialBook());
		}).subscribeOn(Schedulers.boundedElastic());
	}

}