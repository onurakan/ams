<template>
    <div class="container">
        <h2>Ams Specifications</h2>
        <div class="specifications">
            <p v-if="isLoading">Loading ...</p>
            <p v-else-if="isUpdating">Updating, please wait ...</p>
            <div v-else-if="isError" class="ui message red big" v-show="errors.length > 0">
                <p>Error occured:</p>
                <li v-for="(error, index) in errors" :key="index">{{ error }}</li>
            </div>
            <div v-else>
                <label>Asset# {{assetId}}</label>
            
                <table class="table table-bordered" >
                    <thead>
                    <tr>
                        <th>specificationId</th>
                        <th>status</th>
                        <th>attribute</th>
                        <th>attributeDescription</th>
                        <th>dataType</th>
                        <th>alphanumericValue</th>
                        <th>alphanumericDescription</th>
                        <th>numericValue</th>
                        <th>numericDescription</th>
                        <th>unitOfMeasure</th>
                        <th>tableValue</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr v-for="specification in specifications" :key="specification.specificationId">
                            <td><a href="javascript:void(0);" v-on:click="currentSpecificationId">{{ specification.specificationId }}</a></td>
                            <td>                                
                                <div v-if="edit1">
                                    <select v-model="specification.status" id="specificationStatus" @blur="edit1 = false; $emit('update'); updateSpecification(specification.specificationId)" @keyup.enter="edit1=false; $emit('update')" v-focus>
                                        <option>ACTIVE</option>
                                        <option>PASSIVE</option>
                                    </select>
                                </div>
                                <div v-else>
                                    <label @click="edit1 = true;"> {{specification.status}} </label>
                                </div>
                            </td>
                            <td>
                                <input v-if="edit2" v-model="specification.attribute" @blur="edit2 = false; $emit('update'); updateSpecification(specification.specificationId)" @keyup.enter="edit2=false; $emit('update')" v-focus>
                                <div v-else>
                                    <label @click="edit2 = true;"> {{specification.attribute}} </label>
                                </div>
                            </td>
                            <td>
                                <input v-if="edit3" v-model="specification.attributeDescription" @blur="edit3 = false; $emit('update'); updateSpecification(specification.specificationId)" @keyup.enter="edit3=false; $emit('update')" v-focus>
                                <div v-else>
                                    <label @click="edit3 = true;"> {{specification.attributeDescription}} </label>
                                </div>
                            </td>
                            <td>
                                <input v-if="edit4" v-model="specification.dataType" @blur="edit4 = false; $emit('update'); updateSpecification(specification.specificationId)" @keyup.enter="edit4=false; $emit('update')" v-focus>
                                <div v-else>
                                    <label @click="edit4 = true;"> {{specification.dataType}} </label>
                                </div>
                            </td>
                            <td>
                                <input v-if="edit5" v-model="specification.alphanumericValue" @blur="edit5 = false; $emit('update'); updateSpecification(specification.specificationId)" @keyup.enter="edit5=false; $emit('update')" v-focus>
                                <div v-else>
                                    <label @click="edit5 = true;"> {{specification.alphanumericValue}} </label>
                                </div>
                            </td>
                            <td>
                                <input v-if="edit6" v-model="specification.alphanumericDescription" @blur="edit6 = false; $emit('update'); updateSpecification(specification.specificationId)" @keyup.enter="edit6=false; $emit('update')" v-focus>
                                <div v-else>
                                    <label @click="edit6 = true;"> {{specification.alphanumericDescription}} </label>
                                </div>
                            </td>
                            <td>
                                <input v-if="edit7" v-model="specification.numericValue" @blur="edit7= false; $emit('update'); updateSpecification(specification.specificationId)" @keyup.enter="edit7=false; $emit('update')" v-focus>
                                <div v-else>
                                    <label @click="edit7 = true;"> {{specification.numericValue}} </label>
                                </div>
                            </td>
                            <td>
                                <input v-if="edit8" v-model="specification.numericDescription" @blur="edit8 = false; $emit('update'); updateSpecification(specification.specificationId)" @keyup.enter="edit8=false; $emit('update')" v-focus>
                                <div v-else>
                                    <label @click="edit8 = true;"> {{specification.numericDescription}} </label>
                                </div>
                            </td>
                            <td>
                                <input v-if="edit9" v-model="specification.unitOfMeasure" @blur="edit9 = false; $emit('update'); updateSpecification(specification.specificationId)" @keyup.enter="edit9=false; $emit('update')" v-focus>
                                <div v-else>
                                    <label @click="edit9 = true;"> {{specification.unitOfMeasure}} </label>
                                </div>
                            </td>
                            <td>
                                <input v-if="edit10" v-model="specification.tableValue" @blur="edit10 = false; $emit('update'); updateSpecification(specification.specificationId)" @keyup.enter="edit10=false; $emit('update')" v-focus>
                                <div v-else>
                                    <label @click="edit10 = true;"> {{specification.tableValue}} </label>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: 'SpecificationTableComponent',
        props:['assetId'],
        data () {
            return {
                specifications: [],
                isLoading: true,
                isUpdating: false,
                isError: false,
                edit1: false,
                edit2: false,
                edit3: false,
                edit4: false,
                edit5: false,
                edit6: false,
                edit7: false,
                edit8: false,
                edit9: false,
                edit10: false,
                errors:[]
           }
        },
        created() {
            this.fetchSpecifications(this.assetId);
        },
        watch: {
            assetId: function(newVal, oldVal) { // watch it
                console.log('SpecificationTableComponent->Watch assetId: ', newVal, ' | was: ', oldVal);
                this.fetchSpecifications(newVal);
            }
        },
        methods: {
            currentSpecificationId: function (event) {
                    console.log("SpecificationTableComponent->currentSpecificationId:" + event.target.innerHTML)
                    this.$emit('specificationId-clicked', event.target.innerHTML);
                },
            updateSpecification : function (specificationId) {
                this.errors = [];
                this.isUpdating = true;
                
                this.specifications.forEach(element => {
                    if (element.specificationId == specificationId) {
                        alert("specificationId=" + specificationId + " will be updated!");
                        console.log("SpecificationTableComponent->updateSpecification request: " +JSON.stringify(element));
                        this.isError = false;
                        axios.put(this.ams_backend_url + '/specification/' + specificationId, element)
                                .then(response => {
                                                    // JSON responses are automatically parsed.
                                                    console.log("SpecificationTableComponent->updateSpecification response:" + JSON.stringify(response.data));
                                                    this.isUpdating = false;
                                                    alert("specificationId=" + specificationId + " is updated :)");
                                                })
                                .catch(e => {
                                    this.isUpdating = false;
                                    if (e.response.status) {
                                        this.isError = true;
                                        this.errors.push(e.response.status + "-" + e.response.data.message);
                                    }
                                })
                    } else {
                        this.isUpdating = true;
                    }
                });
            },
            fetchSpecifications : function (inAssetId) {
                this.errors = [];

                if (inAssetId != null && inAssetId != -1) {
                    console.log("SpecificationTableComponent->fetchSpecifications request for assetId=" + inAssetId)
                    
                    this.isLoading=true;
                    this.isError = false;
                    
                    axios.get(this.ams_backend_url + '/specification?assetId=' + inAssetId, this.auth)
                        .then(response => {
                                            // JSON responses are automatically parsed.
                                            console.log("SpecificationTableComponent->fetchSpecifications response:" + JSON.stringify(response.data));
                                            this.specifications = response.data
                                            this.isLoading = false;
                                        })
                        .catch(e => {
                            this.isLoading=false;

                            if (e.response.status) {
                                this.isError = true;
                                this.errors.push(e.response.status + "-" + e.response.data.message);
                            }
                        })
                } else {
                    this.specifications = [];
                    this.isLoading = false;
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
