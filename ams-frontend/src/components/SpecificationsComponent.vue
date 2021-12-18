<template>
    <div class="container">
        <h2>Ams Specifications</h2>
        <div class="specifications">
            <p v-if="isLoading">Loading ...</p>

            <table class="table table-bordered" v-else>
                <thead>
                <tr>
                    <th>Id</th>
                    <th>attribute</th>
                    <th>attribureDescription</th>
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
                        <td>{{ specification.id }}</td>
                        <td>{{ specification.attribute }}</td>
                        <td>{{ specification.attribureDescription }}</td>
                        <td>{{ specification.dataType }}</td>
                        <td>{{ specification.alphnumericValue }}</td>
                        <td>{{ specification.alphanumericDescription }}</td>
                        <td>{{ specification.numericValue }}</td>
                        <td>{{ specification.numericDescription }}</td>
                        <td>{{ specification.unitOfMeasure }}</td>
                        <td>{{ specification.tableValue }}</td>
                    </tr>
                </tbody>
            </table>
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
                isLoading: true
            }
        },
        created() {
            this.fetchSpecifications(this.assetId);
        },
        methods: {
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
            }
        }
    }
</script>
