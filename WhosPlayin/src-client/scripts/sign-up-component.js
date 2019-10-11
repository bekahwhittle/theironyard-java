const React = require('react')
const ReactDOM = require('react-dom')

const ACTIONS = require('./actions.js')

const SignUpView = React.createClass({
      getInitialState: function() {
         return {
             signInData: []
         };
      },

      _handleSignIn: function(evt){
         evt.preventDefault()

         let userObj = {
            username: evt.target.username.value,
            password: evt.target.password.value
         }

         ACTIONS._signInUser(userObj)
      },

      _handleSignUp: function(evt){
         evt.preventDefault()

         let newUserObj = {
            firstName: evt.target.firstName.value,
            lastName: evt.target.lastName.value,
            email: evt.target.email.value,
            username: evt.target.username.value,
            password: evt.target.password.value,
            city: evt.target.location.value
         }

         ACTIONS._signUpUser(newUserObj)
      },

     render: function(){
         return (
               <div className="main-container login-page">
                <div className="title">
                  <a clasName="title-home" href="#!">WHOSPLAYIN</a>
                </div>
                 <div className="row">
                   <div className="column left-column col s12 m6">
                     <form className="sign-in" onSubmit={this._handleSignIn}>
                       <h1 className="center">Sign In</h1>
                       <input type="text" className="form-control" placeholder="Username" name="username"/>
                       <br></br>
                       <input type="password" className="form-control" placeholder="Password" name="password"/>
                       <br></br>
                       <button className="center sign-btn btn waves-effect waves-light" type="submit">Sign In</button>
                       <br></br>
                     </form>
                   </div>


                   <div className="column right-column col s12 m6">
                     <form className="sign-up" onSubmit={this._handleSignUp}>
                       <h1 className ="center">Sign Up</h1>
                       <input type="text" className="form-control" placeholder="First Name" name="firstName"/>
                       <br></br>
                       <input type="text" className="form-control" placeholder="Last Name" name="lastName"/>
                       <br></br>
                       <input type="text" className="form-control" placeholder="Email" name="email"/>
                       <br></br>
                       <input type="text" className="form-control" placeholder="Username" name="username"/>
                       <br></br>
                       <input type="text" className="form-control" placeholder="Password" type="password" name="password" id="password" maxLength="32"/>
                       <br></br>
                       <input type="text" className="form-control" placeholder="Location" name="location"/>
                       <br></br>
                       <div className="right-col-btn-wrapper">
                         <button className="signUp-btn btn waves-effect waves-light" type="submit">Sign Up</button>
                       </div>
                       <br></br>
                    </form>
                  </div>
               </div>
            </div>
         )
     }

})
module.exports = SignUpView
