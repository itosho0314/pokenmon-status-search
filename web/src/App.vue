<template>
  <v-app>
    <v-main>
      <v-row justify="center" >
        <v-col>
          <pokemonSearch />
        </v-col>
        <v-col>
          <pokemonSearch />
        </v-col>
      </v-row>
    </v-main>
  </v-app>
</template>

<script>
import pokemonSearch from "./components/search.vue";

export default {
  name: "App",

  components: {
    pokemonSearch,
  },

  data: () => ({
    //
  }),
};
</script>
