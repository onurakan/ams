<template>
    <div>
        <div class="container">
            <h1>Asset Management System</h1>
            <br>

            <ul class="nav nav-tabs nav-justified">
            <li class="nav-item">
                <a class="nav-link" @click.prevent="setActive('asset')" :class="{ active: isActive('asset') }" href="#asset">Asset</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" @click.prevent="setActive('specifications')" :class="{ active: isActive('specifications') }" href="#specifications">Specifications</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" @click.prevent="setActive('other')" :class="{ active: isActive('other') }" href="#other">Other</a>
            </li>
            </ul>
            <div class="tab-content py-3" id="myTabContent">
                <div class="tab-pane fade" :class="{ 'active show': isActive('asset') }" id="asset">
                    <asset-form-component :assetId="assetId" @fetchAsset-finished="passLazyAssetId"/>
                </div>
                <div class="tab-pane fade" :class="{ 'active show': isActive('specifications') }" id="specifications">
                    <div class="specificationForm" v-if="specificationId">
                        <a href="javascript:void(0);" v-on:click="closeSpecification">Close Specification</a>
                        <specification-form-component :specificationId="specificationId" :assetId="assetId"/>
                    </div>
                    <div v-else>
                        <a href="javascript:void(0);" v-on:click="openNewSpecification">New Specification</a>
                        <specification-table-component @specificationId-clicked="passSpecificationId" :assetId="assetId"/>
                    </div>
                </div>
                <div class="tab-pane fade" :class="{ 'active show': isActive('other') }" id="other">Other content</div>
            </div>
        </div>
    </div>
</template>

<script>
    import AssetFormComponent from './forms/AssetFormComponent.vue'
    import SpecificationTableComponent from './table/SpecificationTableComponent.vue'
    import SpecificationFormComponent from './forms/SpecificationFormComponent.vue'

    export default {
        //el: "#app",
        components: {
            AssetFormComponent,
            SpecificationTableComponent,
            SpecificationFormComponent
        },
        props:['assetId'],
        watch: {
            assetId: function(newVal, oldVal) { // watch it
                console.log('AssetComponent->Watch assetId: ', newVal, ' | was: ', oldVal);
                this.lazy_AssetId = newVal;
            }
        },
        data () {
            return {
                activeItem: 'asset',
                lazy_AssetId: null,
                specificationId: null
            }
        },
        methods: {
            isActive (menuItem) {
                return this.activeItem === menuItem
            },
            setActive (menuItem) {
                this.activeItem = menuItem
            },
            passLazyAssetId: function(assetId) {
                console.log("AssetComponent->"+'passLazyAssetId: lazy_AssetId='+assetId);
                this.lazy_AssetId = assetId;
            },
            passSpecificationId: function (specificationId) {
                console.log("AssetComponent->"+'passSpecificationId: specificationId='+specificationId);
                this.specificationId = specificationId;
            },
            closeSpecification: function () {
                console.log("AssetComponent->"+'closeSpecification: specificationId='+ this.specificationId);
                this.specificationId = null;
                this.lazy_AssetId = null;
            },
            openNewSpecification: function() {
                console.log("AssetComponent->"+'openNewSpecification');
                this.specificationId = -1;
            }
        }
     };
</script>