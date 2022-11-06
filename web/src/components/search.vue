<template>
  <v-container>
    <v-row>
      <v-col>
        <auto-complete
          :list="candidateList"
        />
      </v-col>
      <v-col>
        <v-btn :disabled="false" color="success" class="mr-4" @click="clear()">
          クリア
        </v-btn>
      </v-col>
    </v-row>
    <template>
      <div class="d-flex flex-column justify-space-between align-center">
        <v-img
          :src="pokemonStatus.pokemonImage"
          max-height="200"
          max-width="200"
        />
      </div>
    </template>
    <v-simple-table>
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left">H</th>
            <th class="text-left">A</th>
            <th class="text-left">B</th>
            <th class="text-left">C</th>
            <th class="text-left">D</th>
            <th class="text-left">S</th>
          </tr>
        </thead>
        <tbody>
          <td class="text-center">{{ pokemonStatus.hitPoint }}</td>
          <td class="text-center">{{ pokemonStatus.attack }}</td>
          <td class="text-center">{{ pokemonStatus.defense }}</td>
          <td class="text-center">{{ pokemonStatus.specialAttack }}</td>
          <td class="text-center">{{ pokemonStatus.specialDefense }}</td>
          <td class="text-center">{{ pokemonStatus.speed }}</td>
        </tbody>
      </template>
    </v-simple-table>
    <v-simple-table>
      <template v-slot:default>
        <thead>
          <tr>
            <th class="text-left">最速</th>
            <th class="text-left">準速</th>
            <th class="text-left">補正無し無振り</th>
            <th class="text-left">最遅</th>
          </tr>
        </thead>
        <tbody>
          <td class="text-center">{{ pokemonStatus.maxSpeed }}</td>
          <td class="text-center">{{ pokemonStatus.secondSpeed }}</td>
          <td class="text-center">{{ pokemonStatus.normalSpeed }}</td>
          <td class="text-center">{{ pokemonStatus.slowestSpeed }}</td>
        </tbody>
      </template>
    </v-simple-table>
  </v-container>
</template>

<script>
import axios from "axios";
import autoComplete from "./autocomplete.vue";

export default {
  name: "pokemonSearch",

  components: {
    autoComplete,
  },

  data: () => ({
    pokemonName: "",
    headers: [
      { text: "画像", value: "pokemonImage", sortable: false },
      { text: "H", value: "hitPoint", sortable: false },
      { text: "A", value: "attack", sortable: false },
      { text: "B", value: "defense", sortable: false },
      { text: "C", value: "specialAttack", sortable: false },
      { text: "D", value: "specialDefense", sortable: false },
      { text: "S", value: "speed", sortable: false },
    ],
    pokemonStatus: {},
    pokemonList: [],
    pokemonImage: null,
    candidateList: [],
  }),
  methods: {
    search() {
      let pokemonData = this.pokemonList.filter(
        (e) =>
          e.pokemonName == this.pokemonName);
      if (pokemonData.length != 0 && this.pokemonStatus.pokemonId != pokemonData[0].pokemonId) {
        axios
          .get("/search", {
            params: {
              id: pokemonData[0].pokemonId,
              pokemonName: this.pokemonName,
            },
          })
          .then((res) => {
            this.pokemonStatus = res.data;
           if(this.pokemonStatus.pokemonImage === null){
            this.pokemonStatus.pokemonImage = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+this.pokemonStatus.pokemonId+".png"
          } 
          });
      }
    },
    setPokemonList(pokemonList) {
      this.pokemonList = pokemonList;
      pokemonList.forEach((element) => {
        this.candidateList.push(element.pokemonName);
      });
    },
    clear() {
      this.pokemonStatus = [];
      this.pokemonName = "";
    },
  },
  mounted() {
    axios.get("/list").then((res) => {
      this.setPokemonList(res.data);
    });
  },
};
</script>
