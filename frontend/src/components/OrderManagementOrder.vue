<template>
    <v-card style="width:450px; height:100%;" outlined>
        <template slot="progress">
            <v-progress-linear
                    color="primary-darker-1"
                    height="10"
                    indeterminate
            ></v-progress-linear>
        </template>

        <v-card-title v-if="value._links">
            주문 # {{decode(value._links.self.href.split("/")[value._links.self.href.split("/").length - 1])}}
        </v-card-title >
        <v-card-title v-else>
            주문
        </v-card-title >        

        <v-card-text style="background-color: white;">
            <String v-if="editMode" label="주문 ID" v-model="value.orderId" :editMode="editMode" :inputUI="''"/>
            <String label="음식점 ID" v-model="value.restaurantId" :editMode="editMode" :inputUI="''"/>
            <String label="사용자 ID" v-model="value.userId" :editMode="editMode" :inputUI="''"/>
            <String label="음식 ID" v-model="value.foodId" :editMode="editMode" :inputUI="''"/>
            <Number label="수량" v-model="value.quantity" :editMode="editMode" :inputUI="''"/>
            <String label="특별 요청" v-model="value.specialRequest" :editMode="editMode" :inputUI="''"/>
            <String label="결제 수단" v-model="value.paymentMethod" :editMode="editMode" :inputUI="''"/>
            <String label="주문 상태" v-model="value.status" :editMode="editMode" :inputUI="''"/>
        </v-card-text>

        <v-card-actions style="background-color: white;">
            <v-spacer></v-spacer>
            <v-btn
                color="primary"
                text
                @click="edit"
                v-if="!editMode"
            >
                수정
            </v-btn>
            <div v-else>
                <v-btn
                    color="primary"
                    text
                    @click="save"
                >
                    주문하기
                </v-btn>
                <v-btn
                    color="primary"
                    text
                    @click="remove"
                    v-if="!editMode"
                >
                    삭제
                </v-btn>
                <v-btn
                    color="primary"
                    text
                    @click="editMode = false"
                    v-if="editMode && !isNew"
                >
                    취소
                </v-btn>
            </div>
        </v-card-actions>
        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openAcceptOrder"
            >
                AcceptOrder
            </v-btn>
            <v-dialog v-model="acceptOrderDiagram" width="500">
                <AcceptOrderCommand
                    @closeDialog="closeAcceptOrder"
                    @acceptOrder="acceptOrder"
                ></AcceptOrderCommand>
            </v-dialog>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openRejectOrder"
            >
                RejectOrder
            </v-btn>
            <v-dialog v-model="rejectOrderDiagram" width="500">
                <RejectOrderCommand
                    @closeDialog="closeRejectOrder"
                    @rejectOrder="rejectOrder"
                ></RejectOrderCommand>
            </v-dialog>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openPickupFood"
            >
                PickupFood
            </v-btn>
            <v-dialog v-model="pickupFoodDiagram" width="500">
                <PickupFoodCommand
                    @closeDialog="closePickupFood"
                    @pickupFood="pickupFood"
                ></PickupFoodCommand>
            </v-dialog>
            <v-btn
                v-if="!editMode"
                color="primary"
                text
                @click="openDeliverFood"
            >
                DeliverFood
            </v-btn>
            <v-dialog v-model="deliverFoodDiagram" width="500">
                <DeliverFoodCommand
                    @closeDialog="closeDeliverFood"
                    @deliverFood="deliverFood"
                ></DeliverFoodCommand>
            </v-dialog>
        </v-card-actions>

        <v-snackbar
            v-model="snackbar.status"
            :top="true"
            :timeout="snackbar.timeout"
            color="error"
        >
            {{ snackbar.text }}
            <v-btn dark text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
    </v-card>

</template>

<script>
    const axios = require('axios').default;


    export default {
        name: 'OrderManagementOrder',
        components:{
        },
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean,
        },
        data: () => ({
            snackbar: {
                status: false,
                timeout: 5000,
                text: '',
            },
            acceptOrderDiagram: false,
            rejectOrderDiagram: false,
            pickupFoodDiagram: false,
            deliverFoodDiagram: false,
        }),
	async created() {
        },
        methods: {
            decode(value) {
                return decodeURIComponent(value);
            },
            selectFile(){
                if(this.editMode == false) {
                    return false;
                }
                var me = this
                var input = document.createElement("input");
                input.type = "file";
                input.accept = "image/*";
                input.id = "uploadInput";
                
                input.click();
                input.onchange = function (event) {
                    var file = event.target.files[0]
                    var reader = new FileReader();

                    reader.onload = function () {
                        var result = reader.result;
                        me.imageUrl = result;
                        me.value.photo = result;
                        
                    };
                    reader.readAsDataURL( file );
                };
            },
            edit() {
                this.editMode = true;
            },
            async save(){
                try {
                    var temp = null;

                    if(!this.offline) {
                        if(this.isNew) {
                            temp = await axios.post(axios.fixUrl('/orders'), this.value)
                        } else {
                            temp = await axios.put(axios.fixUrl(this.value._links.self.href), this.value)
                        }
                    }

                    if(this.value!=null) {
                        for(var k in temp.data) this.value[k]=temp.data[k];
                    } else {
                        this.value = temp.data;
                    }

                    this.editMode = false;
                    this.$emit('input', this.value);

                    if (this.isNew) {
                        this.$emit('add', this.value);
                    } else {
                        this.$emit('edit', this.value);
                    }

                    location.reload()

                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
                
            },
            async remove(){
                try {
                    if (!this.offline) {
                        await axios.delete(axios.fixUrl(this.value._links.self.href))
                    }

                    this.editMode = false;
                    this.isDeleted = true;

                    this.$emit('input', this.value);
                    this.$emit('delete', this.value);

                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            change(){
                this.$emit('input', this.value);
            },
            async acceptOrder(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links[''].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeAcceptOrder();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openAcceptOrder() {
                this.acceptOrderDiagram = true;
            },
            closeAcceptOrder() {
                this.acceptOrderDiagram = false;
            },
            async rejectOrder(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links[''].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeRejectOrder();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openRejectOrder() {
                this.rejectOrderDiagram = true;
            },
            closeRejectOrder() {
                this.rejectOrderDiagram = false;
            },
            async pickupFood(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links[''].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closePickupFood();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openPickupFood() {
                this.pickupFoodDiagram = true;
            },
            closePickupFood() {
                this.pickupFoodDiagram = false;
            },
            async deliverFood(params) {
                try {
                    if(!this.offline) {
                        var temp = await axios.put(axios.fixUrl(this.value._links[''].href), params)
                        for(var k in temp.data) {
                            this.value[k]=temp.data[k];
                        }
                    }

                    this.editMode = false;
                    this.closeDeliverFood();
                } catch(e) {
                    this.snackbar.status = true
                    if(e.response && e.response.data.message) {
                        this.snackbar.text = e.response.data.message
                    } else {
                        this.snackbar.text = e
                    }
                }
            },
            openDeliverFood() {
                this.deliverFoodDiagram = true;
            },
            closeDeliverFood() {
                this.deliverFoodDiagram = false;
            },
        },
    }
</script>

