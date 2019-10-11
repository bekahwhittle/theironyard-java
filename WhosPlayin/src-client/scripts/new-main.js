import React from 'react'
import ReactDOM from 'react-dom'


// const STORE = require('./store.js')
import ACTIONS from './actions.js'


let bigStr = ''
// componentDidMount() {
//     var element = ReactDOM.findDOWNode(this.refs.dropdown)
//     let(element).ready(function(){
//       let('select').material_select();
//     });
// }


const MainView = React.createClass({
  componentWillMount: function() {
    console.log("prop dawgs", this.props);
   //  if(!this.props.currentUser.city){
   //    return(
   //      <p>loadddinnn</p>
   //    )

    // let eventsListNames = performance[0]
    // console.log(eventsListNames);
  },

  _logoutHandler: function(){
    ACTIONS._signOutUser()
  },

  componentWillReceiveProps: function(newProps){
    console.log('new props',newProps)

    if(newProps.currentUser.city !== this.props.currentUser.city){
      ACTIONS._fetchBandsandTrackData(newProps.currentUser.city)
    }
  },

  _changeLocationSubmitHandler: function(){
     evt.preventDefault()

     let userNewLocationObj = {
        city: evt.target.city.value,
     }

     ACTIONS._changeUserLocation(userNewLocationObj)
  },

  render: function(){
    if(!this.props.artistArr.catMusicShit){
      return(
        <p>loadddinnn</p>
      )
    }

   //  console.log();
   //  let cards = this.props.whatever.map(function(element){
   //
   //    // return <CardView currentArtist={this.props.currentArtist} artistArr={this.props.artistArr} />
   // })
   console.log(this.props.artistArr)
    return (
      <div className="wrapper">
        <div className="hero">
          <div className="input-field">
          </div>
          <nav>
            <div className="nav-wrapper">
               <form className="row change-location left" onSubmit={this._changeLocationSubmitHandler}>
                  <button type="submit" className="col sm6 center location-btn btn waves-effect waves-light">Change Location</button>
                  <input className="col sm6" type="text" name="city"></input>
               </form>
              <a href="#!" className="brand-logo center">whosplayin</a>
              <ul className="right hide-on-med-and-down">


                <li><a>Hello {this.props.currentUser.firstName}</a></li>
                <li><a href="#ok">Home</a></li>

                <li><a href="#ok">{this.props.currentUser.city}</a></li>
                <li><a href="/#" onClick={this._logoutHandler}>Logout</a></li>
              </ul>
            </div>
          </nav>
        </div>
        <div className="playlist-area">
          <iframe src={this.props.artistArr.catMusicShit} width="500" height="380" frameborder="0" allowtransparency="true"></iframe>


        </div>

        <div className="row">
          {this.props.artistArr.spotifyQueryResults.map(function(obj, i){
             console.log(this.props.artistArr.catMusicShit)
             return(
                <div className="col xs12 s12 m4 lg3">
                   <div className="card z-depth-4">
                     <div className="card-content black-text">
                       <div className="card-image">
                         <img src={obj[0].artists.items[0].images[1].url}></img>
                       </div>
                       <span className="card-title"></span>
                          <h2>{obj[0].artists.items[0].name}</h2>
                     </div>
                     <div className="cta">
                       {/* <iframe src="{this.props.artistArr.catMusicShit}" width="300" height="380" frameborder="0" allowtransparency="true"></iframe>
                     <iframe src={this.props.artistArr.catMusicShit} frameBorder="0" allowTransparency="true"></iframe> */}
                     <script type='text/javascript' src='http://widget.bandsintown.com/javascripts/bit_widget.js'></script>
                     <a target="_blank" href={`http://www.bandsintown.com/${obj[0].artists.items[0].name}`} className="bit-widget-initializer bandsintown" data-artist="Small Black">{obj[0].artists.items[0].name} Tour Dates</a>

                     </div>
                   </div>
                </div>
             )
          }.bind(this)) }
        </div>
      </div>
    );
  }
});





module.exports = MainView
