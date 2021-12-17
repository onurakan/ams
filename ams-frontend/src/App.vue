<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <HelloWorld msg="Welcome to Asset Management Vue.js App"/>
  <MyFirst />
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import MyFirst from './components/MyFirst.vue'

export default {
  name: 'App',
  components: {
    MyFirst,
    HelloWorld
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>





