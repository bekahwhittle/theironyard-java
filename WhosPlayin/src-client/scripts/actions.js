const Backbone = require('backbone');
const STORE = require('./store.js');
import $ from 'jquery'
import * as APIModule from './test.js'

const UserModel = require('./model-user.js').UserModel

const ACTIONS = {
      _signUpUser: function(newUserObj){
            console.log(newUserObj)
           let userMod = new UserModel("/sign-up")
           userMod.set(newUserObj)
           console.log(userMod)

         userMod.save().then(function(serverRes){
            console.log(serverRes)
            window.location.hash = "home"
         })
      },

      _signInUser: function(userObj){
           let userMod = new UserModel("/sign-in")
           userMod.set(userObj)

         userMod.save().then(function(serverRes){
            STORE.setStore('currentUser', serverRes)
            window.location.hash = "home"
         })
      },

      _signOutUser: function(){
           let userMod = new UserModel("/sign-out")

         userMod.save().then(function(){
            window.location.hash = ""
         })
      },

      // setTopTracks: function(){
      //   let coll = new TopTrackColl()
      //
      //   coll.fetch().then(function(){
      //
      //     let bigStr = coll.join(",")
      //     STORE.setStore("currentArtist", bigStr)
      //   }
      //
      // },

      _captureLocation: function(locationObj){
         console.log(locationObj)
         STORE.setStore('location', locationObj)
         window.location.hash = "home"

         // let locationMod = new UserModel("/location")
         // locationMod.set(locationObj)

         // locationMod.save().then(function(serverRes){
         //    console.log(serverRes)
         //    window.location.hash = "home"
         // })
      },

      _changeUserLocation: function(userNewLocationObj){
           let userNewLocationMod = new UserModel("/edit-account")
           userNewLocationMod.set(userNewLocationObj)

           userNewLocationMod.save().then(function(serverRes){
            STORE.setStore('currentUser', serverRes)
            window.location.hash = "home"
         })
      },

      _getUser: function(queryObj){
         const authenticateUser = new UserModel("/get-account")
         authenticateUser.fetch().then(function(serverRes){
            STORE.setStore('currentUser', serverRes)
         })

      },

      _fetchBandsandTrackData: function(cityName){

          APIModule.fetchSongKickBandsfromProxy(cityName)
            .then(APIModule.handleSongKickBandsfromProxyPromise)
            .then(APIModule.handleFetchArtist)
            .then(APIModule.handleFetchTopTracks)
            .then(function(catMusicData){
              console.log(catMusicData.spotifyQueryResults);
              console.log('all promises resolved succesfully: ', catMusicData.catMusicShit)
              STORE.setStore('artistArr', catMusicData)
            //   STORE.setStore('currentArtist', catMusicData.catMusicShit)
            })
      }

}

module.exports = ACTIONS
