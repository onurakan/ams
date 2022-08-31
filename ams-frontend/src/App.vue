<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">    
    <div class="assetForm" v-if="assetId">
      <a href="javascript:void(0);" v-on:click="closeAsset">Close Asset</a>
      <asset-component :assetId="assetId"/>
    </div>
    <div v-else>
      <a href="javascript:void(0);" v-on:click="openNewAsset">New Asset</a>
      <asset-table-component @assetId-clicked="passAssetId"/>
    </div>
  </div>
</template>

<script>
  import AssetTableComponent from './components/table/AssetTableComponent.vue'
  import AssetComponent from './components/AssetComponent.vue'

  export default {
    name: 'App',
    el: '#app',
    components: {
      AssetTableComponent,
      AssetComponent,
    },
    data () {
      return {
          assetId: null,
      }
    },
    created() {
    },
    methods: {
      passAssetId: function (assetId) {
        console.log("App->"+'passAssetId: '+assetId);
        this.assetId = assetId;
      },
      closeAsset: function () {
        console.log("App->"+'closeAsset: assetId='+ this.assetId);
        this.assetId = null;
      },
      openNewAsset: function() {
        console.log("App->"+'openNewAsset');
        this.assetId = -1;
      }
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
