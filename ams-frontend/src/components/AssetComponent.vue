<template>
  <div>
    <p v-if="isLoading">is loading...</p>
    <div v-else>
      <div>
        <label>Asset Name: </label><input type="text" readonly v-model="assetObj.assetId" /><br>
        <label>Asset Status: </label><input type="text" readonly v-model="assetObj.status" /><br>
        <label>Asset Classification: </label><input type="text" readonly v-model="assetObj.classification" /><br>
        <label>Asset Description: </label><input type="text" readonly v-model="assetObj.description" /><br>
        <label>Asset AssetTag: </label><input type="text" readonly v-model="assetObj.assetTag" /><br>
      </div>
    </div>
  </div>
</template>



<script>
  import axios from 'axios';
  
  export default {
    name: 'AssetComponent',
    props:['assetId'],
    data () {
      return {
          assetObj: {
            assetId: null,
            status: null,
            classification: null,
            description: null,
            assetTag: null
          },
          isLoading: false
      }
    },
    watch: { 
      assetId: function(newVal, oldVal) { // watch it
        console.log('Prop changed: ', newVal, ' | was: ', oldVal);
        this.fetchAsset(newVal);
      }
    },
    created() {
      this.fetchAsset(this.assetId);
    },
    methods: {
        fetchAsset : function (inAssetId) {
          if (inAssetId>0) {
           console.log("AssetComponent->fetchAsset request for assetId=" + inAssetId)
           this.isLoading=true;
            axios.get('http://localhost:8080/api/assets/' + inAssetId)
                .then(response => {
                                    // JSON responses are automatically parsed.
                                    console.log("AssetComponent->fetchSpecifications response:" + JSON.stringify(response.data));
                                    this.assetObj = response.data
                                    this.isLoading = false;
                                  })
                .catch(e => {
                  this.isLoading=false;
                  this.errors.push(e)
                })
          }
        }
    }
  }
</script>