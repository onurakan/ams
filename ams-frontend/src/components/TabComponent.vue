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
                    <asset-component :assetId="tabAssetId"/>
                </div>
                <div class="tab-pane fade" :class="{ 'active show': isActive('specifications') }" id="specifications">
                    <specifications-component :assetId="tabAssetId" />
                </div>
                <div class="tab-pane fade" :class="{ 'active show': isActive('other') }" id="other">Other content</div>
            </div>
        </div>
    </div>
</template>

<script>
    import AssetComponent from './AssetComponent.vue'
    import SpecificationsComponent from './SpecificationsComponent.vue'

    export default {
        //el: "#app",
        components: {
            AssetComponent,
            SpecificationsComponent
        },
        props:['tabAssetId'],
        data () {
            return {
                activeItem: 'asset'
            }
        },
        methods: {
            isActive (menuItem) {
                return this.activeItem === menuItem
            },
            setActive (menuItem) {
                this.activeItem = menuItem
            }
        }
     };
</script>