<template>
    <div class="container">
        <h2>Ams Assets</h2>
        <div class="assets">
            <p v-if="isLoading">Loading ...</p>
            <table class="table table-bordered" v-else>
                <thead>
                <tr>
                    <th>Asset Id</th>
                    <th>Status</th>
                    <th>Classification</th>
                    <th>Description</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input ref="assetId" placeholder="Asset Id" @blur="filterAssets()" @keyup.enter="filterAssets()" /></td>
                        <td><input ref="assetStatus" placeholder="Status" @blur="filterAssets()" @keyup.enter="filterAssets()"/></td>
                        <td><input ref="assetClassification" placeholder="Classification" @blur="filterAssets()" @keyup.enter="filterAssets()"/></td>
                        <td><input ref="assetDescription" placeholder="Description" @blur="filterAssets()" @keyup.enter="filterAssets()"/></td>
                    </tr>
                    <tr v-for="asset in filteredAssets" :key="asset.id">
                        <td><a href="javascript:void(0);" v-on:click="currentAssetId">{{ asset.assetId }}</a></td>
                        <td>{{ asset.status }}</td>
                        <td>{{ asset.classification }}</td>
                        <td>{{ asset.description }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    export default {
        props: [],
        data() {
            return {
                assets: [],
                filteredAssets: [],
                errors: [],
                assetId: 1,
                isLoading: false
            }
        },

        methods: {
                currentAssetId: function (event) {
                    console.log("AssetListComponent->currentAssetId:" + event.target.innerHTML)
                    this.$emit('assetId-clicked', event.target.innerHTML);
                },
                filterAssets: function () { 
                    if (!this.assets.length) {
                        return []
                    }
                
                    this.filteredAssets = this.assets.filter(item => {
                        var matchAssetId = (this.$refs.assetId.value == '' || this.$refs.assetId.value == item.assetId);
                        var matchAssetStatus = (this.$refs.assetStatus.value == '' || this.$refs.assetStatus.value == item.status);
                        var matchAssetClassification = (this.$refs.assetClassification.value == '' || this.$refs.assetClassification.value == item.classification);
                        var matchAssetDescription = (this.$refs.assetDescription.value == '' || this.$refs.assetDescription.value == item.description);

                        return matchAssetId && matchAssetStatus && matchAssetClassification && matchAssetDescription;
                    });
                }
        },
        created() {
            this.isLoading = true;
            axios.get('http://localhost:8080/api/assets')
                .then(response => {
                        // JSON responses are automatically parsed.
                        this.assets = response.data
                        this.filteredAssets = this.assets
                        this.isLoading = false;
                    })
                .catch(e => {
                    this.isLoading = false;
                    this.errors.push(e)
                })
        }
     };
</script>

<style scoped>
    ::-webkit-input-placeholder {
    text-align: center;
    }

    :-moz-placeholder { /* Firefox 18- */
    text-align: center;  
    }

    ::-moz-placeholder {  /* Firefox 19+ */
    text-align: center;  
    }

    :-ms-input-placeholder {  
    text-align: center; 
    }
</style>