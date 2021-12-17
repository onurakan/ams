<template>
  <div>
    {{asset}}
  </div>
</template>



<script>
  import axios from 'axios';
  
  export default {
    name: 'AssetComponent',
    props: {
      assetId: Number
    },
    data() {
            return {
                asset: Number,
                errors: []
            }
        },

    created() {
            alert("AssetComponent"+this.$props.assetId)
            axios.get('http://localhost:8080/api/assets/' + this.$props.assetId)
            .then(response => {
            // JSON responses are automatically parsed.
            this.asset = response.data
            })
            .catch(e => {
            this.errors.push(e)
            })
        }
  }
</script>