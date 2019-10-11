const React = require('react')

const ACTIONS = require('./actions.js')


const SplashPageView = React.createClass({
   _handleClick: function(evt){
      evt.preventDefault()

      window.location.hash='signup'

   },

   _signUp: function(){
      window.location.hash="signup"
   },

   render: function(){
      return (
      <div className="splash-container">

      <div className="splash-title">
         <h4>WHOSPLAYIN</h4>
      </div>
      <div className="splash-login" onClick={this._signUp}>

      </div>

         <div className="splash-top">
            <h1>Find Bands Playing Near You!</h1>
            <button onClick={this._handleClick} className="col-sm-6 center listen-button btn waves-effect waves-light"><a>Sign Up</a></button>

         </div>

         <div className="splash-bottom">

         </div>

      </div>
   )
   }
})

module.exports = SplashPageView
