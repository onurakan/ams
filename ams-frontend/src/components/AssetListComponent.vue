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
                    <tr v-for="asset in assets" :key="asset.id">
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
                errors: [],
                assetId: 1,
                isLoading: false
            }
        },

        methods: {
                currentAssetId: function (event) {
                    console.log("AssetListComponent->currentAssetId:" + event.target.innerHTML)
                    this.$emit('assetId-clicked', event.target.innerHTML);
                }
        },
        created() {
            this.isLoading = true;
            axios.get('http://localhost:8080/api/assets')
                .then(response => {
                        // JSON responses are automatically parsed.
                        this.assets = response.data
                        this.isLoading = false;
                    })
                .catch(e => {
                    this.isLoading = false;
                    this.errors.push(e)
                })
        }
     };
</script>