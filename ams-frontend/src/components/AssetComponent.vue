<template>
  <div>
    <p v-if="isLoading">Loading...</p>
    <p v-else-if="isUpdating">Updating, please wait ...</p>
    <div v-else>


      <div>
        <div class="divTable" style="width: 51%">
          <div class="divTableBody">
            <div class="divTableRow">
              <div class="divTableCell"><label for="assetId">Asset Id:</label></div>
              <div v-if="edit2" class="divTableCell">
                <input id="assetId" v-model="assetObj.assetId" @blur="edit2 = false; $emit('update'); updateAsset(assetObj.assetId)" @keyup.enter="edit2=false; $emit('update')" v-focus>
              </div>        
              <div v-else class="divTableCell">
                  <label @click="edit2 = true;"> {{assetObj.assetId}} </label>
              </div>
            </div>
            <div class="divTableRow">
              <div class="divTableCell"><label for="assetStatus">Asset Status:</label></div>
              <div v-if="edit3" class="divTableCell">
                <input id="assetStatus" v-model="assetObj.status" @blur="edit3 = false; $emit('update'); updateAsset(assetObj.assetId)" @keyup.enter="edit3=false; $emit('update')" v-focus>
              </div>
              <div v-else class="divTableCell">
                  <label @click="edit3 = true;"> {{assetObj.status}} </label>
              </div>
            </div>
            <div class="divTableRow">
              <div class="divTableCell"><label for="assetClassification">Asset Classification:</label></div>
              <div v-if="edit4" class="divTableCell">
                <input id="assetClassification" v-if="edit4" v-model="assetObj.classification" @blur="edit4 = false; $emit('update'); updateAsset(assetObj.assetId)" @keyup.enter="edit4=false; $emit('update')" v-focus>
              </div>
              <div v-else class="divTableCell">
                  <label @click="edit4 = true;"> {{assetObj.classification}} </label>
              </div>
            </div>
            <div class="divTableRow">
              <div class="divTableCell"><label for="assetDescription">Asset Description:</label></div>
              <div v-if="edit5" class="divTableCell">
                <input id="assetDescription" v-if="edit5" v-model="assetObj.description" @blur="edit5 = false; $emit('update'); updateAsset(assetObj.assetId)" @keyup.enter="edit5=false; $emit('update')" v-focus>
              </div>
              <div v-else class="divTableCell">
                  <label @click="edit5 = true;"> {{assetObj.description}} </label>
              </div>
            </div>
            <div class="divTableRow">
              <div class="divTableCell"><label for="assetTag">Asset Tag:</label></div>
              <div v-if="edit6" class="divTableCell">
                <input id="assetTag" v-if="edit6" v-model="assetObj.assetTag" @blur="edit6 = false; $emit('update'); updateAsset(assetObj.assetId)" @keyup.enter="edit6=false; $emit('update')" v-focus>
              </div>
              <div v-else class="divTableCell">
                  <label @click="edit6 = true;"> {{assetObj.assetTag}} </label>
              </div>
            </div>
          </div>
        </div>
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
          isLoading: false,
          isUpdating: false,
          edit2: false,
          edit3: false,
          edit4: false,
          edit5: false,
          edit6: false

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
        },
        updateAsset : function (assetId) {
          alert("assetId=" + assetId + " will be updated!");
          this.isUpdating = true;
          console.log("AssetComponent->updateAsset request:" +JSON.stringify(this.assetObj));
          axios.put('http://localhost:8080/api/assets/' + assetId, this.assetObj)
                  .then(response => {
                                      // JSON responses are automatically parsed.
                                      console.log("AssetComponent->updateAsset response:" + JSON.stringify(response.data));
                                      this.isUpdating = false;
                                      alert("assetId=" + assetId + " is updated :)");
                                  })
                  .catch(e => {
                      this.isUpdating = false;
                      this.errors.push(e)
                  })
        }
    },
    directives: {
            focus: {
                inserted (el) {
                    el.focus()
                }
            }
        }
  }
</script>

<style scoped>
  /* DivTable.com */
  .divTable{
    display: table;
    width: 100%;
  }
  .divTableRow {
    display: table-row;
  }
  .divTableHeading {
    background-color: #EEE;
    display: table-header-group;
  }
  .divTableCell, .divTableHead {
    border: 1px solid #999999;
    display: table-cell;
    padding: 3px 10px;
  }
  .divTableHeading {
    background-color: #EEE;
    display: table-header-group;
    font-weight: bold;
  }
  .divTableFoot {
    background-color: #EEE;
    display: table-footer-group;
    font-weight: bold;
  }
  .divTableBody {
    display: table-row-group;
  }
</style>