import React from 'react';
import ReactDOM from 'react-dom';
import Backbone from 'backbone';
import Spotify from 'spotify-web-api-js'

const HomeView = React.createClass({
  render: function(){
    return (
            <div className="main-container" style={mainStyle}>
              {/* <Login /> */}
              {/* <i className="fa fa-bars fa-4x hamburger" aria-hidden="true" style={hamburgerStyle}></i> */}
              <div className="row center">
                <CardView />
              </div>
              <footer className="page-footer">
                <div className="row">
                  <div className="col l4 s12">
                    <div className="container">
                      <div className="controls">
                        <div className="container">
                          <a className="grey-text text-lighten-4 left" href="#play"><i className="fa fa-play-circle fa-4x" aria-hidden="true"></i>
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div className="col l4 s12">
                    <h1 className="white-text">wow a progress bar here</h1>
                  </div>
                  <div className="col l4 s12">
                    <ul>
                      <li><a className="grey-text text-lighten-3 right" href="#!">{this.props.bandname}</a></li>
                      <br></br>
                      <li><a className="grey-text text-lighten-3 right" href="#!">{this.props.artistname}</a></li>
                    </ul>
                  </div>
                </div>
              </footer>
            </div>
           )
  }
});

const CardView = React.createClass({
  render: function(){
    return (
        <div className="col m4">
          <div className="card">
            <div className="card-content black-text">
              <div className="card-image">
                <img src="https://unsplash.it/200/?random"></img>
              </div>
              <span className="card-title">{this.props.bandname}</span>
              <h6>{this.props.artistname}</h6>
              <h6>{this.props.datetime}</h6>
            </div>
            <div className="cta">
              {/* <iframe src="https://embed.spotify.com/?uri=spotify:playlist:788MOXyTfcUb1tdw4oC7KJ" width="300" height="380" frameborder="0" allowtransparency="true"></iframe> */}
              <iframe src="https://embed.spotify.com/?uri=spotify:trackset:WOWTESTTESTTEST:4uLU6hMCjMI75M1A2tKUQC,3LX0Qc1iKkqLZP0FijjLmI,1x6ACsKV4UdWS2FMuPFUiT,4bi73jCM02fMpkI11Lqmfe" frameBorder="0" allowTransparency="true"></iframe>
              <a href="#bandinfo">view full playlist</a>
            </div>
          </div>
        </div>
    );
  }
});

module.exports = {HomeView, CardView}
