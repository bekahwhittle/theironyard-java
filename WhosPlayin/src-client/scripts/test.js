/* I'm trying to figure out how to render playlists*/
import $ from 'jquery'
import Spotify from 'spotify-web-api-js'

var s = new Spotify();

// fetch event data from bekah
const fetchSongKickBandsfromProxy = function(userInputLocation){
  return $.ajax({
    url: `/whosplayin/${userInputLocation}`,
    method: "GET"
  });
}

// let eventsListNames = 'lil wayne'
// input variable into spotify query
const fetchArtist = function(artist){
  console.log(`artist list fetched`);
  return $.ajax({
    url: `https://api.spotify.com/v1/search?q=${artist.split(' ').join('+')}&type=artist`,
    method: `GET`
  });
}
// const artistSpotifyID = artists.items[0].uri
// const artistImg = artists.items[0].images[2]
// fetchBackEnd.map(fetchArtists);

// render top track URIs into iframe
const fetchTopTracks = function(artistId){
  return $.ajax({
    url: `https://api.spotify.com/v1/artists/${artistId}/top-tracks?country=US`,
    method: `GET`
  });
}

const handleSongKickBandsfromProxyPromise = function(res){

 console.log('res1', res);

 let shortList = res.slice(0, 10)
 let queryPromisesList = shortList.map(function(songKickObj){
   return fetchArtist( songKickObj.performance[0].artist.displayName );
 })
 return $.when( ...queryPromisesList );

}

const handleFetchArtist = function(...spotifyQueryResults){
 console.log('the spotify initial query:', spotifyQueryResults );
 let spotifyTopTrackPromisesList = spotifyQueryResults.filter(function(promiseObj){
     return promiseObj[0].artists.items.length > 0
 }).map(function(promiseObj){
     console.log(promiseObj);
     return fetchTopTracks(promiseObj[0].artists.items[0].id)
 })

 return $.when(spotifyQueryResults, ...spotifyTopTrackPromisesList)

}

const handleFetchTopTracks = function(spotifyQueryResults, ...topTrackResults){
  console.log(topTrackResults);
  // console.log(topTrackResults.length)
  // topTrackResults.forEach( function(){
  let musicShit = []
  let catMusicShit = ''
  let fak = ''

  for (var i = 0; i < topTrackResults.length; i++){
    let artistTopTracks = topTrackResults[i]["0"].tracks["0"].id
    console.log(artistTopTracks);
    //   STORE.setStore('currentArtist', artistTopTracks)
    musicShit.push(artistTopTracks)
  }

  if (musicShit.length > 0) {
    fak = musicShit.join(',');
    console.log("waaaaaaaaaaaa", fak);
    catMusicShit = "https://embed.spotify.com/?uri=spotify:trackset:WhosPlayin:"
    catMusicShit = catMusicShit + fak
  }

  let promiseObj = new $.Deferred()
  console.log(catMusicShit)
  return {
   //   catMusicShit: promiseObj.resolve(catMusicShit),
     catMusicShit: catMusicShit,
     spotifyQueryResults: spotifyQueryResults
  }


}

module.exports = {
  fetchSongKickBandsfromProxy,
  fetchArtist,
  fetchTopTracks,
  handleSongKickBandsfromProxyPromise,
  handleFetchArtist,
  handleFetchTopTracks
}
// const artistSpotifyID = artists.items[0].uri
// const artistImg = artists.items[0].images[2]
