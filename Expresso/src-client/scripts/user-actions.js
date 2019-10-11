const Backbone = require('backbone')
import STORE from './user-store.js'
import {UserModel, ShopModel, UserCollection, LikeModel} from './user-models.js'
import $ from 'jquery'

const ACTIONS = {
   registerUser: function(newUser){
      let userMod = new UserModel()
      userMod.set(newUser)
      console.log(userMod)

      userMod.save().then(function(serverRes){
         console.log(serverRes)
         location.hash = '';
    })
  },

   fetchCoffeeData: function(){
      let coffeeCollInstance = new UserCollection();
         coffeeCollInstance.fetch().then(function(){
            STORE.setStore('coffeeShopData', coffeeCollInstance);

            console.log('fetch ', coffeeCollInstance);
      })
      return coffeeCollInstance
   },


   createNewCoffeeEntry: function(newEntry){
      let newCoffeeEntry = new ShopModel();
          newCoffeeEntry.set(newEntry);
          console.log('submit', newCoffeeEntry);
          return newCoffeeEntry.save().then(function(){
           window.location.hash = '';
      })
   },

   updateCoffeeLikes: function(newLike){
      let coffeeLikes = new LikeModel();
         if(id < 1){
            coffeeLikes.set(newLike);
            coffeeLikes.save();
            coffeeLikes.fetch().then(function(){
               STORE.setStore('newLikeData', coffeeLikes);
            })
         } else {
            console.log('You have already submitted a like on this shop!!!');
         }
      }

}

module.exports = ACTIONS
