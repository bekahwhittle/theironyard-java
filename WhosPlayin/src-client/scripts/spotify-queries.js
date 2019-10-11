import $ from 'jquery'
import http from 'http'
import request from 'request'
import ACTIONS from './actions.js'
// console.log(userIP);

import Spotify from 'spotify-web-api-js'
let self = this
const seatGeekGeo = function(){
  $.getJSON('https://api.seatgeek.com/2/events?geoip='+ userIP +'&range=20mi').then(function(res){
    console.log(res);
    const artistID = this.performers.links.id
  });
}

const spotifyTracks = function(){
  $.getJSON('https://api.spotify.com/v1/artists/'+ artistID +'/top-tracks').then(function(res){
    console.log(res);
  });
}

const getLocation = navigator.geolocation.getCurrentPosition(function(position) {
  console.log(position.coords.latitude, position.coords.longitude);
  return {latitude: position.coords.latitude, longitude: position.coords.longitude};
});

// ACTIONS._captureLocation(getLocation)
//
// $.getJSON("http://api.songkick.com/api/3.0/search/locations.json?location=clientip&apikey=YlX4r2ab8xzzlYDB").then(function(data){
//   console.log(data)
// });

const fetchMetroData = function(){
  request('http://api.songkick.com/api/3.0/search/locations.json?location=geo:' + userLocation + '&apikey=YlX4r2ab8xzzlYDB', function (error, response, body) {
    if (!error && response.statusCode == 200) {
      console.log(body)
      const userMetro = body
    }
    if (!error && response.statusCode !== 200){
      document.getElementById('app-container').appendChild("<h1>it's all broken!!!</h1>")
    }
  });
}


const fetchMetroConcerts = function(){
  request('http://api.songkick.com/api/3.0/metro_areas/{metro_area_id}/calendar.json?apikey=YlX4r2ab8xzzlYDB', function (error, response, body){
    if (!error && response.statusCode == 200) {
      console.log(body)
      const metroConcerts = body
    }
    if (!error && response.statusCode !== 200){
      document.getElementById('app-container').appendChild("<h1>it's all broken!!!</h1>")
    }
  });
}
s.getArtistTopTracks('5wVc58a2hRj76nbaCKwlDt', function(res){
  console.log("wow i love elvis" + res);
})
module.exports = {
  getLocation,
  seatGeekGeo,
  spotifyTracks
}

var s = new Spotify();
