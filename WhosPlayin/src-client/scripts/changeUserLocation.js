import React from 'react'
import ReactDOM from 'react-dom'

const STORE = require('./store.js')
const ACTIONS = require('./actions.js')


// NEW ACTION

_changeUserLocation: function(userNewLocationObj){
     let userNewLocationMod = new UserModel("/edit-account")
     userNewLocationMod.set(userNewLocationObj)

     userNewLocationMod.save().then(function(serverRes){
      STORE.setStore('currentUser', serverRes)
      window.location.hash = "home"
   })
},


// NEW BUTTON IN MAIN  & CLICKHANDLER

<form className="change-location" onSubmit={this._changeLocationSubmitHandler}>
   <input type="text" value="Location" name="city"></input>
   <button type="submit" className="center location-btn btn waves-effect waves-light">Change Location</button>
</form>



_changeLocationSubmitHandler = function(){
   evt.preventDefault()

   let userNewLocationObj = {
      city: evt.target.city.value,
   }

   ACTIONS._changeUserLocation(userNewLocationObj)
},


const cardViewConstructor =  function(artistDataObj){
   return (
      <div className="col xs12 s12 m4 lg3">
         <div className="card z-depth-4">
           <div className="card-content black-text">
             <div className="card-image">
               <img src={artistDataObj.images[1].url}></img>
             </div>
             <span className="card-title"></span>
             <h6>{artistDataObj.name}</h6>
           </div>
           <div className="cta">

             <iframe src={this.props.currentArtist} frameBorder="0" allowTransparency="true"></iframe>
             <script type='text/javascript' src='http://widget.bandsintown.com/javascripts/bit_widget.js'></script>
             <a href="http://www.bandsintown.com/SmallBlack" className="bit-widget-initializer bandsintown" data-artist="Small Black">Susto Tour Dates</a>
           </div>
         </div>
      </div>
   );
}

let bigStr = ""
for (var i = 0; i < spotifyQueryResults.length; i++){
   bigStr += cardViewConstructor(spotifyQueryResults[i][0].artists.items[0])
}
STORE.setStore("cardView", bigStr)
