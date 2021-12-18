<template>
  <div id="app">
    <img alt="Vue logo" src="./assets/logo.png">    
    <div class="assetDetail" v-if="assetId">
      <a href="javascript:void(0);" v-on:click="closeAsset">Close Asset</a>
      <TabComponent :tabAssetId="assetId"/>
    </div>
    <AssetListComponent @assetId-clicked="setParentAssetId" v-else/>
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
        //this.fetchAsset(assetId)
      },
      closeAsset: function () {
        this.assetId = null;
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
