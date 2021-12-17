<template>
  <div>
    <img alt="Vue logo" src="./assets/logo.png">
    <AssetListComponent :showAssetList="true" :showAssetDetail="false"/>
  </div>
</template>

<script>
  import AssetListComponent from './components/AssetListComponent.vue'

  export default {
    name: 'App',
    components: {
      AssetListComponent
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
@import'~bootstrap/dist/css/bootstrap.css'
</style>
