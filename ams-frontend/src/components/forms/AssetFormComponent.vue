<template>
  <div>
    <p v-if="isLoading">Loading...</p>
    <p v-else-if="isUpdating">Updating, please wait ...</p>
    <div v-else-if="isError" class="ui message red big" v-show="errors.length > 0">
      <p>Error occured:</p>
      <li v-for="(error, index) in errors" :key="index">{{ error }}</li>
    </div>
    <div v-else>
      
      <div>
        <div class="divTable" style="width: 51%">
          <div class="divTableBody">
            <div class="divTableRow">
              <div class="divTableCell"><label for="assetId">Asset Id:</label></div>
              <div class="divTableCell">
                  <label> {{asset.assetId}} </label>
              </div>
            </div>
            <div class="divTableRow">
              <div class="divTableCell"><label for="assetStatus">Status:</label></div>
              <div v-if="edit3" class="divTableCell">
                <input id="assetStatus" v-model.number="asset.status" type="number" @blur="$emit('update'); updateAsset(asset.assetId)" @keyup.enter="$emit('update')" v-focus>
              </div>
              <div v-else class="divTableCell">
                  <label @click="edit3 = true;"> {{asset.status}} </label>
              </div>
            </div>
            <div class="divTableRow">
              <div class="divTableCell"><label for="assetClassification">Classification:</label></div>
              <div v-if="edit4" class="divTableCell">
                <input id="assetClassification" v-if="edit4" v-model="asset.classification" @blur="$emit('update'); updateAsset(asset.assetId)" @keyup.enter="$emit('update')" v-focus>
              </div>
              <div v-else class="divTableCell">
                  <label @click="edit4 = true;"> {{asset.classification}} </label>
              </div>
            </div>
            <div class="divTableRow">
              <div class="divTableCell"><label for="assetDescription">Description:</label></div>
              <div v-if="edit5" class="divTableCell">
                <input id="assetDescription" v-if="edit5" v-model="asset.description" @blur="$emit('update'); updateAsset(asset.assetId)" @keyup.enter="$emit('update')" v-focus>
              </div>
              <div v-else class="divTableCell">
                  <label @click="edit5 = true;"> {{asset.description}} </label>
              </div>
            </div>
            <div class="divTableRow">
              <div class="divTableCell"><label for="assetTag">Tag:</label></div>
              <div v-if="edit6" class="divTableCell">
                <input id="assetTag" v-if="edit6" v-model="asset.assetTag" @blur="$emit('update'); updateAsset(asset.assetId)" @keyup.enter="$emit('update')" v-focus>
              </div>
              <div v-else class="divTableCell">
                  <label @click="edit6 = true;"> {{asset.assetTag}} </label>
              </div>
            </div>
          </div>
        </div>
        <button v-if="asset.assetId == null" v-on:click="createAsset()" style="margin-left: -80px; margin-top: 10px;" >Create Asset</button>
      </div>
    </div>
  </div>
</template>


<script>
  import axios from 'axios';
  
  export default {
    name: 'AssetFormComponent',
    props:['assetId'],
    data () {
      return {
          isLoading: false,
          isUpdating: false,
          isError: false,
          asset: {
            assetId: null,
            status: null,
            classification: null,
            description: null,
            assetTag: null
          },
          edit3: false,
          edit4: false,
          edit5: false,
          edit6: false,
          errors:[]
      }
    },
    watch: { 
      assetId: function(newVal, oldVal) { // watch it
        console.log('AssetFormComponent-> Watch: assetId changed to:' + newVal + ', was:' + oldVal);
        this.fetchAsset(newVal);
      }
    },
    created() {
      console.log('AssetFormComponent-created called. assetId:' + this.assetId);
      this.fetchAsset(this.assetId);
    },
    methods: {
        fetchAsset : async function (inAssetId) {
          this.errors = [];

          if (inAssetId != null && inAssetId != -1) {
            console.log("AssetFormComponent->fetchAsset request for assetId=" + inAssetId)
            this.isLoading=true;
            this.isError = false;
            axios.get(this.ams_backend_url + '/asset/' + inAssetId, this.auth)
                .then(response => {
                                    // JSON responses are automatically parsed.
                                    console.log("AssetFormComponent->fetchAsset response:" + JSON.stringify(response.data));
                                    this.asset = response.data
                                    this.isLoading = false;
                                    this.$emit('fetchAsset-finished', inAssetId);//this.asset.specificationList);
                                  })
                .catch(e => {
                  this.isLoading=false;
                  
                  if (e.response.status) {
                    this.isError = true;
                    this.errors.push(e.response.status + "-" + e.response.data.errorMessage);
                  }
                })
          } else { //new Asset
            this.asset ={
                            assetId: null,
                            status: null,
                            classification: null,
                            description: null,
                            assetTag: null
                          };
            this.edit3 = true;
            this.edit4 = true;
            this.edit5 = true;
            this.edit6 = true;
          }
        },
        createAsset : function () {
          this.errors = [];

          if (this.asset.status != null &&
                this.asset.classification != null &&
                this.asset.description != null &&
                this.asset.assetTag != null
                ) {
              this.edit3 = false;
              this.edit4 = false;
              this.edit5 = false;
              this.edit6 = false;
              
              alert("New asset will be created!");
              console.log("AssetFormComponent->createAsset request:" +JSON.stringify(this.asset));
              this.isError = false;
              axios.post(this.ams_backend_url + '/asset', this.asset)
                      .then(response => {
                                          // JSON responses are automatically parsed.
                                          console.log("AssetFormComponent->createAsset response:" + JSON.stringify(response.data));
                                          this.isUpdating = false;
                                          this.asset.assetId = response.data.assetId;
                                          alert("assetId=" + this.asset.assetId + " is created :)");
                                      })
                      .catch(e => {
                          this.isUpdating = false;
                          if (e.response.status) {
                            this.isError = true;
                            this.errors.push(e.response.status + "-" + e.response.data.errorMessage);
                          }
                      })
            }
        },
        updateAsset : function () {
          this.errors = [];

          event.target.readonly = true;
          let assetId = this.asset.assetId;

          if (assetId != null && assetId != -1) {
            this.edit3 = false;
            this.edit4 = false;
            this.edit5 = false;
            this.edit6 = false;

            alert("assetId=" + assetId + " will be updated!");
            this.isUpdating = true;
            console.log("AssetFormComponent->updateAsset request:" +JSON.stringify(this.asset));
            this.isError = false;
            axios.put(this.ams_backend_url + '/asset/' + assetId, this.asset, this.auth)
                    .then(response => {
                                        // JSON responses are automatically parsed.
                                        console.log("AssetFormComponent->updateAsset response:" + JSON.stringify(response.data));
                                        this.isUpdating = false;
                                        alert("assetId=" + assetId + " is updated :)");
                                    })
                    .catch(e => {
                        this.isUpdating = false;
                        if (e.response.status) {
                          this.isError = true;
                          this.errors.push(e.response.status + "-" + e.response.data.errorMessage);
                        }
                        
                    })
          }
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