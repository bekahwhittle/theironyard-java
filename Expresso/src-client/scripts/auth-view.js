import React from 'react'
import ReactDOM from 'react-dom'
import Backbone from 'backbone'
import $ from 'jquery'
import ACTIONS from './user-actions.js'

const AuthView = React.createClass({
   _submitAuth: function(evt){
      evt.preventDefault()

      let newUser = {
         name: evt.target.name.value,
         password: evt.target.password.value
      }
      console.log(newUser)

      ACTIONS.registerUser(newUser)
   },

   render: function(){

      return(
               <div className = "row text-center">
                  <form className = "col-sm-offset-3 col-sm-6 " id="auth-form" onSubmit = {this._submitAuth}>
                        <a href = "#"><i className="fa fa-home fa-2x " aria-hidden="true"></i></a>
                        <h2 className = "">Login or Sign Up</h2>
                     <div className = "form-group">
                        <label htmlFor = "name"> </label>
                        <input type = "text" className="form-control" id="name" placeholder="Enter Name" />
                     </div>
                     <div className="form-group">
                        <label htmlFor = "password"></label>
                        <input type = "password" className="form-control" id="password" placeholder="Enter Password"/>
                     </div>
                        <input type = "submit" className="btn btn-default" />
                  </form>
               </div>
            )
         }
})
module.exports = AuthView
