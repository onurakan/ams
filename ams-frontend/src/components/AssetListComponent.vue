<template>
    <div class="container" v-show="showAssetList">
        <h2>Ams Assets</h2>
        <div class="assets">
            <table class="table table-bordered">
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
                    <td><a href="" v-on:click.once="openAsset">{{ asset.assetId }}</a></td>
                    <td>{{ asset.status }}</td>
                    <td>{{ asset.classification }}</td>
                    <td>{{ asset.description }}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="assetDetail" v-show="showAssetDetail">
            <a href="" v-on:click.once="closeAsset"></a>
            <AssetComponent :assetId="assetId"/>
        </div>""
    </div>
</template>

<script>
    import axios from 'axios';
    import AssetComponent from './AssetComponent.vue'

    export default {
        components: {
            AssetComponent
        },
        props: {
            showAssetList: Boolean,
            showAssetDetail: Boolean,
        },
        data() {
            return {
                assets: [],
                errors: [],
                assetId: 1
            }
        },

        methods: {
            openAsset: function (event) {
                this.$props.showAssetList=false
                //this.assetId = event.target.innerHTML
                alert(event.target.innerHTML);
                this.$props.showAssetDetail=true
                alert(this.$props.showAssetDetail);
            },
            closeAsset: function () {
                this.$props.showAssetDetail=false
                this.$props.showAssetList=true
            }
            /**isShowAssetList: function () {
                    return this.showAssetList;
                },
                isShowAssetDetail: function () {
                    return this.showAssetDetail;
                },
                currentAssetId: function () {
                    return this.assetId;
                }**/
        },


        created() {
            axios.get('http://localhost:8080/api/assets')
            .then(response => {
            // JSON responses are automatically parsed.
            this.assets = response.data
            })
            .catch(e => {
            this.errors.push(e)
            })
        }

     };
</script>