
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);



import DeliveryManagementDeliveryDriverManager from "./components/listers/DeliveryManagementDeliveryDriverCards"
import DeliveryManagementDeliveryDriverDetail from "./components/listers/DeliveryManagementDeliveryDriverDetail"



import EventCollectorView from "./components/EventCollectorView"
import EventCollectorViewDetail from "./components/EventCollectorViewDetail"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [

            {
                path: '/deliveryManagements/deliveryDrivers',
                name: 'DeliveryManagementDeliveryDriverManager',
                component: DeliveryManagementDeliveryDriverManager
            },
            {
                path: '/deliveryManagements/deliveryDrivers/:id',
                name: 'DeliveryManagementDeliveryDriverDetail',
                component: DeliveryManagementDeliveryDriverDetail
            },



            {
                path: '/eventcollections/eventCollectors',
                name: 'EventCollectorView',
                component: EventCollectorView
            },
            {
                path: '/eventcollections/eventCollectors/:id',
                name: 'EventCollectorViewDetail',
                component: EventCollectorViewDetail
            },


    ]
})
