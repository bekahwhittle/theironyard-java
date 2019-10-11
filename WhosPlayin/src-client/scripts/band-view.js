const React = require('react')
const ReactDOM = require('react-dom')

const BandView = React.createClass ({

  _bandInfo: function(){
    window.location.hash='#bandinfo'
  },

  render: function(){
     console.log(this.props.currentArtist)
    return (
      <div className="container band-info">
        <nav>
          <div className="nav-wrapper">
            <a href="#home" className="brand-logo center">whosplayin</a>
          </div>
        </nav>
        <h1 className="band-title">{this.props.artistname}</h1>
        <div className="row">
          <div className="left-column col s6 m6 l6">
            <div className="band-image">
              <img className ="band-img" src="https://lastfm-img2.akamaized.net/i/u/ar0/55292eb15e7e442a9f40f09625d6e111"/>
            <br></br>
              <script type='text/javascript' src='http://widget.bandsintown.com/javascripts/bit_widget.js'></script>
              <a href="http://www.bandsintown.com/SmallBlack" className="bit-widget-initializer" data-artist="Small Black">Susto Tour Dates</a>
            </div>
          </div>
          <div className="right-column col s6 m6 l6">
            <div className="spotify-widget">
              <iframe src="https://embed.spotify.com/?uri=spotify:user:erebore:playlist:788MOXyTfcUb1tdw4oC7KJ" width="500" height="640" frameBorder="0" allowTransparency="true"></iframe>
            </div>
          </div>
        </div>


  </div>

    )
  }
})
module.exports = BandView
