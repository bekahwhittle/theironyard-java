import Backbone from 'backbone'
import React from 'react'
import ReactDOM from 'react-dom'
import $ from 'jquery'

const STORE = {
      coffeeData: {
         coffeeShopData: [],
   },

   setStore: function(coffeeProp, maindata){
      if(typeof this.coffeeData[coffeeProp] === 'undefined'){
         console.error(`This is not a Coffee House, reSubmit your entry`)
         return
      }

      this.coffeeData[coffeeProp] = maindata
      Backbone.Events.trigger('shopChange')
   },

   getCoffeeData: function(){
      return this.coffeeData
   },

    onChange: function(someFunc){
      Backbone.Events.on('shopChange', someFunc)
   }

}
module.exports = STORE
