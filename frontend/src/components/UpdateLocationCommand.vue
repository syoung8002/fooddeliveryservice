<template>
    <v-card outlined>
        <v-card-title>
            UpdateLocation
        </v-card-title>

        <v-card-text>
            <Address offline label="Location" v-model="value.location" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="updateLocation"
            >
                UpdateLocation
            </v-btn>
            
            <v-btn
                    color="primary"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'UpdateLocationCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.location = {};
        },
        watch: {
        },
        methods: {
            updateLocation() {
                this.$emit('updateLocation', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

