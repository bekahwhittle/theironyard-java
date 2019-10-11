import Backbone from 'backbone'
import React from 'react'
import $ from 'jquery'

import ACTIONS from './user-actions.js'
import STORE from './user-store.js'
import {HomeView, ProductView} from './home-view.js'
import SubmitView from './submit-view.js'
import AuthView from './auth-view.js'

const AppController = React.createClass({
   getInitialState: function(){
      let data = ACTIONS.fetchCoffeeData()
      STORE.setStore('coffeeShopData', data)

      let initialState = STORE.getCoffeeData()
      console.log(initialState)
   return initialState

   },

   componentWillMount: function(){
       let self = this
       STORE.onChange(function(){

           let newState = STORE.getCoffeeData()
           self.setState(newState)
       })
    },

   render: function(){
      console.log("b4 switch", this.state);

      switch(this.props.routedFrom){
         case "HomeView":
            return <HomeView coffeeShopData = {this.state}/>
            break;

         case "SubmitView":
            return <SubmitView />
            break;

         case "AuthView":
            return <AuthView />
            break;

         default:
            return
               <div>
                  <h1>Please Return to the home page</h1>
                  <a href = "#"><i className = "fa fa-home fa-2x" aria-hidden = "true"></i></a>
               </div>
      }
   }
})

module.exports = AppController
