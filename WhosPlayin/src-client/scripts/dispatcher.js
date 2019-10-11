const React = require('react');
const STORE = require('./store.js');
const ACTIONS = require('./actions.js');

const MainView = require('./new-main.js');
const BandView = require('./band-view.js');
const SplashPageView = require('./component-splash.js');
const SignUpView = require('./sign-up-component.js')


const Dispatcher = React.createClass({
        getInitialState: function(){
          STORE.setStore('currentSongs', [])

          let startingState = STORE.getStoreData()
          return startingState
        },

        componentWillMount: function(){
          ACTIONS._getUser()

          let self = this
          STORE.onChange(function(){
            let updateState = STORE.getStoreData()
            self.setState(updateState)
          })
        },

         render: function(){
           console.log("cu",this.state.currentUser);
          //  if(!this.state.currentArtist.length < 1){
          //    return (
          //      <p>loadinnn</p>
          //           )
          //  }
               switch (this.props.routedFrom){
                  case "SplashPageView":
                     return <SplashPageView/>
                     break;

                  case "MainView":
                     return <MainView currentUser={this.state.currentUser} artistArr={this.state.artistArr} currentArtist={this.state.currentArtist}/>
                     break;

                  case "SignUpView":
                     return <SignUpView/>
                     break;

                  case "BandView":
                     return <BandView currentArtist={this.state.currentArtist}/>
                     break;

                  case "CardView":
                     return <CardView artistArr={this.state.artistArr}/>
                     break;
               }
            }
})

module.exports = Dispatcher
