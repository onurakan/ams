<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">    
    <div class="assetDetail" v-if="assetId">
      <a href="javascript:void(0);" v-on:click="closeAsset">Close Asset</a>
      <tab-component :tabAssetId="assetId"/>
    </div>
    <div v-else>
      <a href="javascript:void(0);" v-on:click="openNewAsset">New Asset</a>
      <asset-list-component @assetId-clicked="setParentAssetId"/>
    </div>
  </div>
</template>

<script>
  import AssetListComponent from './components/AssetListComponent.vue'
  import TabComponent from './components/TabComponent.vue'

  export default {
    name: 'App',
    el: '#app',
    components: {
      AssetListComponent,
      TabComponent,
    },
    data () {
      return {
          assetId: null,
      }
    },
    created() {
    },
    methods: {
      setParentAssetId: function (assetId) {
        console.log("App->"+'setParentAssetId: Changed parentAssetId='+assetId);
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
