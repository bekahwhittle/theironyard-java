import ReactDOM from 'react-dom';
import React from 'react';
import Backbone from 'backbone';
import $ from 'jquery';

import SubmitView from './submit-view.js'
import AuthView from './auth-view.js'
import {HomeView, ProductView} from './home-view.js'
import {UserModel, ShopModel, UserCollection} from './user-models.js'
import ACTIONS from './user-actions.js'
import AppController from './view-controller.js'

const AppRouter = Backbone.Router.extend({
  routes: {
     "submit" : "showSubmitView",
       "user" : "showAuthPage",
           "" : "showHomePage"
   },

  showSubmitView: function(){
     ReactDOM.render(<AppController routedFrom = "SubmitView" />, document.querySelector('#app-container'))
   },

  showAuthPage: function(){
     ReactDOM.render(<AppController routedFrom = "AuthView" />, document.querySelector('#app-container'));
   },

  showHomePage: function(){
     ReactDOM.render(<AppController routedFrom = "HomeView" />, document.querySelector('#app-container'));
   },

  initialize: function(){
   Backbone.history.start();
  }

})
const application = new AppRouter;
