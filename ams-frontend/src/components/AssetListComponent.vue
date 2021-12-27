<template>
    <div class="container">
        <h2>Ams Assets</h2>
        <div class="assets">
            <p v-if="isLoading">Loading ...</p>
            <p v-else-if="isError">Error occured: "{{requestError}}"</p>

            <table class="table table-bordered" v-else>
                <thead>
                <tr>
                    <th>Asset Id</th>
                    <th>Status</th>
                    <th>Classification</th>
                    <th>Description</th>
                    <th>Tag</th>
                </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><input v-model="asset.assetId" placeholder="Asset Id" @blur="filterAssets()" @keyup.enter="filterAssets()" /></td>
                        <td><input v-model="asset.status" placeholder="Status" @blur="filterAssets()" @keyup.enter="filterAssets()"/></td>
                        <td><input v-model="asset.classification" placeholder="Classification" @blur="filterAssets()" @keyup.enter="filterAssets()"/></td>
                        <td><input v-model="asset.description" placeholder="Description" @blur="filterAssets()" @keyup.enter="filterAssets()"/></td>
                        <td><input v-model="asset.assetTag" placeholder="Tag" @blur="filterAssets()" @keyup.enter="filterAssets()"/></td>
                    </tr>
                    <tr v-for="asset in filteredAssets" :key="asset.id">
                        <td><a href="javascript:void(0);" v-on:click="currentAssetId">{{ asset.assetId }}</a></td>
                        <td>{{ asset.status }}</td>
                        <td>{{ asset.classification }}</td>
                        <td>{{ asset.description }}</td>
                        <td>{{ asset.assetTag }}</td>
                    </tr>
                </tbody>
            </table>

            <div v-if="page.previous || page.next" class="divTable" style="width: 20%; margin: 0 auto;" >
                <div class="divTableBody">
                    <div class="divTableRow">
                        <div class="divTableCell"><a v-if="page.previous" href="javascript:void(0);" v-on:click="openPage(page.previous)">Previous Assets</a></div>
                        <div class="divTableCell"><a v-if="page.next"     href="javascript:void(0);" v-on:click="openPage(page.next)">Next Assets</a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    export default {
        props: [],
        data() {
            return {
                isLoading: false,
                isError: false,
                requestError : null,
                filteredAssets: [],
                errors: [],
                page: {previous: null, next: null},
                asset: {
                    assetId: null,
                    status: null,
                    classification: null,
                    description: null,
                    assetTag: null
                }
            }
        },

        methods: {
                currentAssetId: function (event) {
                    console.log("AssetListComponent->currentAssetId:" + event.target.innerHTML)
                    this.$emit('assetId-clicked', event.target.innerHTML);
                },
                openPage: function (filterUrl) {
                    this.asset = JSON.parse(JSON.stringify(this.asset, function (i, val) { return val === "" ? null : val;}));

                    axios.post(filterUrl, this.asset)
                      .then(response => {
                                // JSON responses are automatically parsed.
                                console.log("AssetListComponent->filterAssets response:" + JSON.stringify(response.data));
                                this.isLoading = false;
                                this.filteredAssets = response.data.data;
                                this.page.previous = null;
                                this.page.next = null;
                                if (response.data.previousPage) this.page.previous = this.ams_backend_url + response.data.previousPage;
                                if (response.data.nextPage)     this.page.next = this.ams_backend_url + response.data.nextPage;
                            })
                      .catch(e => {
                          this.isLoading = false;
                          if (e.response.status) {
                            this.isError = true;
                            this.requestError = e.response.status + "-" + e.response.data.error;
                          }
                          this.errors.push(e)
                      })
                },
                filterAssets: async function () { 
                    if (!this.filteredAssets.length) {
                        return []
                    }

                    this.openPage(this.ams_backend_url + '/api/asset/read/filter/1/' + this.page_size);
                }
        },
        created() {
            this.isLoading = true;
            this.isError = false;
            this.openPage(this.ams_backend_url + '/api/asset/read/filter/1/' + this.page_size);
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
        border: 0px solid #999999;
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