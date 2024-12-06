<template>
    <v-card outlined>
        <v-card-title>
            PlaceOrder
        </v-card-title>

        <v-card-text>
            <String label="RestaurantId" v-model="value.restaurantId" :editMode="editMode"/>
            <String label="UserId" v-model="value.userId" :editMode="editMode"/>
            <String label="FoodId" v-model="value.foodId" :editMode="editMode"/>
            <Number label="Quantity" v-model="value.quantity" :editMode="editMode"/>
            <String label="SpecialRequest" v-model="value.specialRequest" :editMode="editMode"/>
            <String label="PaymentMethod" v-model="value.paymentMethod" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="placeOrder"
            >
                PlaceOrder
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
        name: 'PlaceOrderCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.restaurantId = '';
            this.value.userId = '';
            this.value.foodId = '';
            this.value.quantity = 0;
            this.value.specialRequest = '';
            this.value.paymentMethod = '';
        },
        watch: {
        },
        methods: {
            placeOrder() {
                this.$emit('placeOrder', this.value);
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

