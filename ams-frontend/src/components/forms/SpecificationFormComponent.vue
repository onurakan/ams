<template>
  <div>
    <p v-if="isLoading">Loading...</p>
    <p v-else-if="isUpdating">Updating, please wait ...</p>
    <div v-else-if="isError" class="ui message red big" v-show="errors.length > 0">
      <p>Error occured:</p>
      <li v-for="(error, index) in errors" :key="index">{{ error }}</li>
    </div>
    <div v-else>
      <br/>
      <div>
        <div class="divTable" style="width: 51%">
          <div class="divTableBody">
            <div class="divTableRow" v-if="specification.specificationId != null">
              <div class="divTableCell"><label for="specificationId">Specification Id:</label></div>
              <div class="divTableCell">
                  <label> {{specification.specificationId}} </label>
              </div>
            </div>
            <div class="divTableRow" v-if="specification.status != null">
              <div class="divTableCell"><label for="specificationStatus">Status:</label></div>
              <div v-if="edit2" class="divTableCell">
                <select v-model="specification.status" id="specificationStatus" @blur="$emit('update'); updateSpecification()" @keyup.enter="$emit('update')" v-focus>
                  <option>ACTIVE</option>
                  <option>PASSIVE</option>
                </select>
              </div>
              <div v-else class="divTableCell">
                  <label @click="edit2 = true;"> {{specification.status}} </label>
              </div>
            </div>
            <div class="divTableRow">
              <div class="divTableCell"><label for="specificationAttribute">Attribute:</label></div>
              <div v-if="edit3" class="divTableCell">
                <input id="specificationAttribute" v-model.number="specification.attribute" @blur="$emit('update'); updateSpecification()" @keyup.enter="$emit('update')" v-focus>
              </div>
              <div v-else class="divTableCell">
                  <label @click="edit3 = true;"> {{specification.attribute}} </label>
              </div>
            </div>
            <div class="divTableRow">
              <div class="divTableCell"><label for="specificationAttributeDescription">Attribute Description:</label></div>
              <div v-if="edit4" class="divTableCell">
                <input id="specificationAttributeDescription" v-if="edit4" v-model="specification.attributeDescription" @blur="$emit('update'); updateSpecification()" @keyup.enter="$emit('update')" v-focus>
              </div>
              <div v-else class="divTableCell">
                  <label @click="edit4 = true;"> {{specification.attributeDescription}} </label>
              </div>
            </div>
            <div class="divTableRow">
              <div class="divTableCell"><label for="dataType">Data Type:</label></div>
              <div v-if="edit5" class="divTableCell">
                <input id="dataType" v-if="edit5" v-model="specification.dataType" @blur="$emit('update'); updateSpecification()" @keyup.enter="$emit('update')" v-focus>
              </div>
              <div v-else class="divTableCell">
                  <label @click="edit5 = true;"> {{specification.dataType}} </label>
              </div>
            </div>
            <div class="divTableRow">
              <div class="divTableCell"><label for="alphanumericValue">Alphanumeric Value:</label></div>
              <div v-if="edit6" class="divTableCell">
                <input id="alphanumericValue" v-if="edit6" v-model="specification.alphanumericValue" @blur="$emit('update'); updateSpecification()" @keyup.enter="$emit('update')" v-focus>
              </div>
              <div v-else class="divTableCell">
                  <label @click="edit6 = true;"> {{specification.alphanumericValue}} </label>
              </div>
            </div>
            <div class="divTableRow">
              <div class="divTableCell"><label for="alphanumericDescription">Alphanumeric Description:</label></div>
              <div v-if="edit7" class="divTableCell">
                <input id="alphanumericDescription" v-if="edit7" v-model="specification.alphanumericDescription" @blur="$emit('update'); updateSpecification()" @keyup.enter="$emit('update')" v-focus>
              </div>
              <div v-else class="divTableCell">
                  <label @click="edit7 = true;"> {{specification.alphanumericDescription}} </label>
              </div>
            </div>
            <div class="divTableRow">
              <div class="divTableCell"><label for="numericValue">Numeric Value:</label></div>
              <div v-if="edit8" class="divTableCell">
                <input id="numericValue" v-if="edit8" v-model="specification.numericValue" @blur="$emit('update'); updateSpecification()" @keyup.enter="$emit('update')" v-focus>
              </div>
              <div v-else class="divTableCell">
                  <label @click="edit8 = true;"> {{specification.numericValue}} </label>
              </div>
            </div>
            <div class="divTableRow">
              <div class="divTableCell"><label for="numericDescription">Numeric Description:</label></div>
              <div v-if="edit9" class="divTableCell">
                <input id="numericDescription" v-if="edit9" v-model="specification.numericDescription" @blur="$emit('update'); updateSpecification()" @keyup.enter="$emit('update')" v-focus>
              </div>
              <div v-else class="divTableCell">
                  <label @click="edit9 = true;"> {{specification.numericDescription}} </label>
              </div>
            </div>
            <div class="divTableRow">
              <div class="divTableCell"><label for="unitOfMeasure">Unit of Measure:</label></div>
              <div v-if="edit10" class="divTableCell">
                <input id="unitOfMeasure" v-if="edit10" v-model="specification.unitOfMeasure" @blur="$emit('update'); updateSpecification()" @keyup.enter="$emit('update')" v-focus>
              </div>
              <div v-else class="divTableCell">
                  <label @click="edit10 = true;"> {{specification.unitOfMeasure}} </label>
              </div>
            </div>
            <div class="divTableRow">
              <div class="divTableCell"><label for="tableValue">Table Value:</label></div>
              <div v-if="edit11" class="divTableCell">
                <input id="tableValue" v-if="edit11" v-model="specification.tableValue" @blur="$emit('update'); updateSpecification()" @keyup.enter="$emit('update')" v-focus>
              </div>
              <div v-else class="divTableCell">
                  <label @click="edit11 = true;"> {{specification.tableValue}} </label>
              </div>
            </div>
          </div>
        </div>
        <br/>
        <button v-if="specification.specificationId == null" v-on:click="createSpecification()" style="margin-left: -140px; margin-top: 10px;" >Create Specification</button>
      </div>
    </div>
  </div>
</template>


<script>
  import axios from 'axios';
  
  export default {
    name: 'SpecificationFormComponent',
    props:['specificationId', 'assetId'],
    data () {
      return {
          isLoading: false,
          isUpdating: false,
          isError: false,
          specification: {
            id: null,
            status: null,
            attribute: null,
            attributeDescription: null,
            dataType: null,
            alphanumericValue: null,
            alphanumericDescription: null,
            numericValue: null,
            numericDescription: null,
            unitOfMeasure: null,
            tableValue: null,
            assetId: null
          },
          edit2: false,
          edit3: false,
          edit4: false,
          edit5: false,
          edit6: false,
          edit7: false,
          edit8: false,
          edit9: false,
          edit10: false,
          edit11: false,
          errors:[]
      }
    },
    watch: {
      specificationId: function(newVal, oldVal) { // watch it
        console.log('SpecificationFormComponent->Watch specificationId: ', newVal, ' | was: ', oldVal);
        this.fetchSpecification(newVal);
      }
    },
    created() {
      this.fetchSpecification(this.specificationId);
    },
    methods: {
        fetchSpecification : async function (inSpecificationId) {
          this.errors = [];

          if (inSpecificationId != null && inSpecificationId != -1) {
            console.log("SpecificationFormComponent->fetchSpecification request for specificationId=" + inSpecificationId)
            this.isLoading=true;
            this.isError = false;
            axios.get(this.ams_backend_url + '/specification/' + inSpecificationId, this.auth)
                .then(response => {
                                    // JSON responses are automatically parsed.
                                    console.log("SpecificationFormComponent->fetchSpecifications response:" + JSON.stringify(response.data));
                                    this.specification = response.data
                                    this.isLoading = false;
                                  })
                .catch(e => {
                  this.isLoading=false;
                  
                  if (e.response.status) {
                    this.isError = true;
                    this.errors.push(e.response.status + "-" + e.response.data.message);
                  }
                })
          } else { //new Specification
            this.specification ={
                            specificationId: null,
                            status: null,
                            attribute: null,
                            attributeDescription: null,
                            dataType: null,
                            alphanumericValue: null,
                            alphanumericDescription: null,
                            numericValue: null,
                            numericDescription: null,
                            unitOfMeasure: null,
                            tableValue: null,
                            assetId: null
                          };
            
            this.edit2 = true;
            this.edit3 = true;
            this.edit4 = true;
            this.edit5 = true;
            this.edit6 = true;
            this.edit7 = true;
            this.edit8 = true;
            this.edit9 = true;
            this.edit10 = true;
            this.edit11 = true;
          }
        },
        createSpecification : function () {
          this.errors = [];

          if (this.specification.attribute != null &&
              this.specification.attributeDescription != null &&
              this.specification.dataType != null &&
              this.specification.alphanumericValue != null &&
              this.specification.alphanumericDescription != null &&
              this.specification.numericValue != null &&
              this.specification.numericDescription != null &&
              this.specification.unitOfMeasure!= null &&
              this.specification.tableValue!= null
                ) {

            this.edit2 = false;
            this.edit3 = false;
            this.edit4 = false;
            this.edit5 = false;
            this.edit6 = false;
            this.edit7 = false;
            this.edit8 = false;
            this.edit9 = false;
            this.edit10 = false;
            this.edit11 = false;
            
            this.specification.assetId = this.assetId;

            alert("New specification will be created!");
            console.log("SpecificationFormComponent->createSpecification request:" +JSON.stringify(this.specification));
            this.isError = false;
            axios.post(this.ams_backend_url + '/specification', this.specification)
                    .then(response => {
                                        // JSON responses are automatically parsed.
                                        console.log("SpecificationFormComponent->createSpecification response:" + JSON.stringify(response.data));
                                        this.isUpdating = false;
                                        this.specification.specificationId = response.data.specificationId;
                                        this.specification.status = response.data.status;
                                        alert("specificationId=" + this.specification.specificationId + " is created :)");
                                    })
                    .catch(e => {
                        this.isUpdating = false;
                        if (e.response.status) {
                            this.isError = true;
                            this.errors.push(e.response.status + "-" + e.response.data.message);
                        }
                    })
            }
        },
        updateSpecification : function () {
          this.errors = [];

          if (this.specification.specificationId != null && this.specification.specificationId != '') {
            this.edit2 = false;
            this.edit3 = false;
            this.edit4 = false;
            this.edit5 = false;
            this.edit6 = false;
            this.edit7 = false;
            this.edit8 = false;
            this.edit9 = false;
            this.edit10 = false;
            this.edit11 = false;

            alert("specificationId=" + this.specification.specificationId + " will be updated!");
            this.isUpdating = true;
            console.log("SpecificationFormComponent->updateSpecification request:" +JSON.stringify(this.specification));
            this.isError = false;
            axios.put(this.ams_backend_url + '/specification/' + this.specification.specificationId, this.specification, this.auth)
                    .then(response => {
                                        // JSON responses are automatically parsed.
                                        console.log("SpecificationFormComponent->updateSpecification response:" + JSON.stringify(response.data));
                                        this.isUpdating = false;
                                        alert("specificationId=" + this.specification.specificationId + " is updated :)");
                                    })
                    .catch(e => {
                        this.isUpdating = false;
                        if (e.response.status) {
                          this.isError = true;
                          this.errors.push(e.response.status + "-" + e.response.data.message);
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