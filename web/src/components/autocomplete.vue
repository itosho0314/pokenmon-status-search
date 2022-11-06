<template>
  <div class="autocomplete">
    <v-text-field
      v-model="search"
      @input="onChange()"
      @keydown.down="onArrowDown"
      @keydown.up="onArrowUp"
      @keydown.enter="onEnter"
    />
    <ul ref="list" class="autocomplete-results" v-show="isOpen">
      <li
        class="autocomplete-result"
        v-for="(result, i) in results"
        :key="i"
        @click="setResult(result)"
        :class="{ 'is-active': i === arrowCounter }"
      >
        {{ result }}
      </li>
    </ul>
  </div>
</template>

<script>
// import { Emit, Watch } from 'vue-property-decorator';
export default {
  name: "autoComplete",

  //親コンポーネントからもらう値を定義
  props: {
    list: [],
  },

  data: () => ({
    search: "",
    isOpen: false,
    results: [],
    arrowCounter: -1,
  }),

  methods: {
    // 入力フォームの入力イベント
    onChange() {
      //テキストボックスが空の時はリストを出さない
      if (this.search.length === 0) {
        this.isOpen = false;
        this.arrowCounter = -1;
        return;
      }
      this.filterResults();
      this.isOpen = true;
      return this.search;
    },
    // 選択候補のフィルタ
    filterResults() {
      // indexOf()が入力文字が何番目にあるか判定をしている
      this.results = this.list.filter(
        (e) =>
          e.indexOf(this.toHalfWidth(this.hiraganaToKatagana(this.search))) == 0
      );
      this.arrowCounter = -1;
    },
    // ↓ キーイベント
    onArrowDown() {
      if (this.arrowCounter < this.results.length - 1) {
        this.arrowCounter = this.arrowCounter + 1;
        const scrollTop = (this.arrowCounter - 3) * 32 + 8;
        if (this.$refs.list.scrollTop < scrollTop) {
          this.$refs.list.scrollTop = scrollTop;
        }
      } else {
        this.arrowCounter = 0;
      }
    },
    // ↑ キーイベント
    onArrowUp() {
      if (this.arrowCounter > 0) {
        this.arrowCounter = this.arrowCounter - 1;
        if (this.$refs.list.scrollTop > this.arrowCounter * 32) {
          this.$refs.list.scrollTop = this.arrowCounter * 32;
        }
      } else {
        this.arrowCounter = this.results.length - 1;
      }
    },

    // 入力フォームの確定イベントと親へのEit
    onEnter() {
      if (this.arrowCounter >= 0) {
        this.search = this.results[this.arrowCounter];
        this.isOpen = false;
        this.arrowCounter = -1;
        this.$parent.pokemonName = this.search;
        this.$parent.search();
      } else {
        if (this.search !== "") {
          this.isOpen = false;
          this.arrowCounter = -1;
        }
        return this.search;
      }
    },
    // 候補リストの選択イベントと親へのEmit
    setResult(result) {
      this.isOpen = false;
      // Let's warn the parent that a change was made
      this.search = result;
      this.$parent.pokemonName = this.search;
      this.$parent.search();
      return result;
    },
    onOptionChanged() {}, // 確定内容でフォーム表示

    // 候補リストの領域外クリックでリスト非表示
    handleClickOutside(event) {
      if (!this.$el.contains(event.target)) {
        if (this.isOpen && !this.onChangeEvent) {
          this.search = this.option;
        }
        this.isOpen = false;
        this.arrowCounter = -1;
      }
    },
    // 平仮名でも候補を出すために使う
    hiraganaToKatagana(src) {
      return src.replace(/[\u3041-\u3096]/g, function (match) {
        const chr = match.charCodeAt(0) + 0x60;
        return String.fromCharCode(chr);
      });
    },
    toHalfWidth(strVal) {
      // 半角変換
      var halfVal = strVal.replace(/[！-～]/g, function (tmpStr) {
        // 文字コードをシフト
        return String.fromCharCode(tmpStr.charCodeAt(0) - 0xfee0);
      });
      return halfVal
        .replace(/”/g, '"')
        .replace(/’/g, "'")
        .replace(/‘/g, "`")
        .replace(/￥/g, "\\")
        .replace(/〜/g, "~");
    },
  },
  // 領域外クリックのイベントハンドラ登録
  mounted() {
    document.addEventListener("click", this.handleClickOutside);
  },
  // 領域外クリックのイベントハンドラ削除
  beforeDestroyed() {
    document.removeEventListener("click", this.handleClickOutside);
  },
};
</script>
<style scoped>
.autocomplete {
  width: 100%;
  position: relative;
}
.autocomplete-input {
  padding: 0;
  margin: 0;
  border-color: #eee;
  height: 100%;
  overflow: auto;
  width: 100%;
}
.autocomplete-results {
  padding: 0;
  margin: 0;
  border: 1px solid #eeeeee;
  max-height: 128px;
  overflow: auto;
  width: 100%;
  background-color: #ffffff;
}
.autocomplete-result {
  list-style: none;
  text-align: left;
  height: 32px;
  line-height: 32px;
  cursor: pointer;
}
.autocomplete-result.is-active {
  background-color: #4aae9b;
  color: white;
}
</style>