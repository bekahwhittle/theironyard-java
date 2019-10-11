const ReactDOM = require('react-dom');
const React = require('react');
const Backbone = require('backbone');

const STORE = {
    _data: {
      currentViewSetting: '',
      currentSongs: [],
      currentUser: {},
      location: '',
      currentArtist: '',
      artistArr: []
    },

    setStore: function(storeProp, payload){
      this._data[storeProp] = payload
      Backbone.Events.trigger('storeChange')
    },

    getStoreData: function(){
      return this._data
    },

    onChange: function(someFunc){
      Backbone.Events.on('storeChange', someFunc)
    }
}

module.exports = STORE
