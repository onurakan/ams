<template>
    <div class="container">
        <h2>Ams Specifications</h2>
        <div class="specifications">
            <p v-if="isLoading">Loading ...</p>
            <p v-else-if="isUpdating">Updating, please wait ...</p>
            <div v-else>
                <label>Asset# {{assetId}}</label>
            
                <table class="table table-bordered" >
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>attribute</th>
                        <th>attributeDescription</th>
                        <th>dataType</th>
                        <th>alphnumericValue</th>
                        <th>alphanumericDescription</th>
                        <th>numericValue</th>
                        <th>numericDescription</th>
                        <th>unitOfMeasure</th>
                        <th>tableValue</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr v-for="specification in specifications" :key="specification.id">
                            <td>
                                <label> {{specification.id}} </label>
                            </td>
                            <td>
                                <input v-if="edit2" v-model="specification.attribute" @blur="edit2 = false; $emit('update'); updateSpecification(specification.id)" @keyup.enter="edit2=false; $emit('update')" v-focus>
                                <div v-else>
                                    <label @click="edit2 = true;"> {{specification.attribute}} </label>
                                </div>
                            </td>
                            <td>
                                <input v-if="edit3" v-model="specification.attributeDescription" @blur="edit3 = false; $emit('update'); updateSpecification(specification.id)" @keyup.enter="edit3=false; $emit('update')" v-focus>
                                <div v-else>
                                    <label @click="edit3 = true;"> {{specification.attributeDescription}} </label>
                                </div>
                            </td>
                            <td>
                                <input v-if="edit4" v-model="specification.dataType" @blur="edit4 = false; $emit('update'); updateSpecification(specification.id)" @keyup.enter="edit4=false; $emit('update')" v-focus>
                                <div v-else>
                                    <label @click="edit4 = true;"> {{specification.dataType}} </label>
                                </div>
                            </td>
                            <td>
                                <input v-if="edit5" v-model="specification.alphnumericValue" @blur="edit5 = false; $emit('update'); updateSpecification(specification.id)" @keyup.enter="edit5=false; $emit('update')" v-focus>
                                <div v-else>
                                    <label @click="edit5 = true;"> {{specification.alphnumericValue}} </label>
                                </div>
                            </td>
                            <td>
                                <input v-if="edit6" v-model="specification.alphanumericDescription" @blur="edit6 = false; $emit('update'); updateSpecification(specification.id)" @keyup.enter="edit6=false; $emit('update')" v-focus>
                                <div v-else>
                                    <label @click="edit6 = true;"> {{specification.alphanumericDescription}} </label>
                                </div>
                            </td>
                            <td>
                                <input v-if="edit7" v-model="specification.numericValue" @blur="edit7= false; $emit('update'); updateSpecification(specification.id)" @keyup.enter="edit7=false; $emit('update')" v-focus>
                                <div v-else>
                                    <label @click="edit7 = true;"> {{specification.numericValue}} </label>
                                </div>
                            </td>
                            <td>
                                <input v-if="edit8" v-model="specification.numericDescription" @blur="edit8 = false; $emit('update'); updateSpecification(specification.id)" @keyup.enter="edit8=false; $emit('update')" v-focus>
                                <div v-else>
                                    <label @click="edit8 = true;"> {{specification.numericDescription}} </label>
                                </div>
                            </td>
                            <td>
                                <input v-if="edit9" v-model="specification.unitOfMeasure" @blur="edit9 = false; $emit('update'); updateSpecification(specification.id)" @keyup.enter="edit9=false; $emit('update')" v-focus>
                                <div v-else>
                                    <label @click="edit9 = true;"> {{specification.unitOfMeasure}} </label>
                                </div>
                            </td>
                            <td>
                                <input v-if="edit10" v-model="specification.tableValue" @blur="edit10 = false; $emit('update'); updateSpecification(specification.id)" @keyup.enter="edit10=false; $emit('update')" v-focus>
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
        name: 'SpecificationsComponent',
        props:['assetId'],
        data () {
            return {
                specifications: [],
                isLoading: true,
                isUpdating: false,
                editedTodo: null,
                edit2: false,
                edit3: false,
                edit4: false,
                edit5: false,
                edit6: false,
                edit7: false,
                edit8: false,
                edit9: false,
                edit10: false
            }
        },
        created() {
            this.fetchSpecifications(this.assetId);
        },
        methods: {
            updateSpecification : function (specificationId) {
                this.isUpdating = true;
                
                this.specifications.forEach(element => {
                    if (element.id == specificationId) {
                        alert("specificationId=" + specificationId + " will be updated!");
                        console.log(JSON.stringify(element));
                        axios.put('http://localhost:8080/api/specifications/' + specificationId, element)
                                .then(response => {
                                                    // JSON responses are automatically parsed.
                                                    console.log("SpecificationsComponent->updateSpecification response:" + JSON.stringify(response.data));
                                                    this.isUpdating = false;
                                                    alert("specificationId=" + specificationId + " is updated :)");
                                                })
                                .catch(e => {
                                    this.isUpdating = false;
                                    this.errors.push(e)
                                })
                    }
                });
            },
            fetchSpecifications : function (inAssetId) {
                if (inAssetId>0) {
                    console.log("SpecificationsComponent->fetchSpecifications request for assetId=" + inAssetId)
                    this.isLoading=true;
                        axios.get('http://localhost:8080/api/assets/' + inAssetId)
                            .then(response => {
                                                // JSON responses are automatically parsed.
                                                console.log("SpecificationsComponent->fetchSpecifications response:" + JSON.stringify(response.data.specificationList));
                                                this.specifications = response.data.specificationList
                                                this.isLoading = false;
                                            })
                            .catch(e => {
                            this.isLoading=false;
                            this.errors.push(e)
                            })
                    }
            },
            editTodo: function (todo) {
               this.editedTodo = todo
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
