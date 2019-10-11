const ReactDOM = require('react-dom');
const React = require('react');
const Backbone = require('backbone');



import Spotify from 'spotify-web-api-js'

const Dispatcher = require('./dispatcher.js');

const AppRouter = Backbone.Router.extend({
  routes: {
    "bandinfo" : "showBandPage",
    "home" : "showHomePage",
    "signup": "showSignUpPage",
    "*path": "showSplashPage"

  },

  showHomePage: function(){
    ReactDOM.render(<Dispatcher routedFrom="MainView"/>, document.querySelector("#app-container"));
  },
  showSplashPage: function(){
    ReactDOM.render(<Dispatcher routedFrom="SplashPageView"/>, document.querySelector("#app-container"));
  },
  showSignUpPage: function(){
    ReactDOM.render(<Dispatcher routedFrom="SignUpView"/>, document.querySelector('#app-container'));
  },
  showBandPage: function(){
    ReactDOM.render(<Dispatcher routedFrom="BandView"/>, document.querySelector('#app-container'));
  },

  initialize: function(){
    Backbone.history.start();
  }
})
// s.getArtistTopTracks('5wVc58a2hRj76nbaCKwlDt', function(res){
//   console.log(res);
// })
// const s = new Spotify();
new AppRouter()
