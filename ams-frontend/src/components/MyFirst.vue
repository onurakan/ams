<template>
    <div>
        <form @submit.prevent="addToPage">
            <h1>Ams Assets</h1>
            <input v-model="data.word" />
            <button>Click to get asstes</button>
        </form>

        <ul>
            <li v-for="(w, index) in data.words" :key="index">
            <h1>{{ w }}</h1>
            </li>
        </ul>
    </div>
</template>

<script>
import { reactive } from "vue";

export default {
  name: 'MyFirst',
  setup() {
    const data = reactive({
      word : "",
      words: [],
    });

    function addToPage() {
      console.log('click')
      data.words.push(data.word);
    }
    return {
      addToPage,
      data
    };
  }
}
</script>