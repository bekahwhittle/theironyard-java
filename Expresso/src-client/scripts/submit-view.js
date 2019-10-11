import React from 'react'
import ReactDOM from 'react-dom'
import Backbone from 'backbone'
import $ from 'jquery'
import ACTIONS from './user-actions.js'

const SubmitView = React.createClass({


   _submitForm: function(evt){
      evt.preventDefault()
      let newEntry = {
          name: this.refs.nameInputEl.value,
          location: this.refs.locInputEl.value,
          hours: this.refs.hoursInputEl.value,
          website: this.refs.webInputEl.value,
          likes: parseInt(this.refs.rateInputEl.value, 10),
          info: this.refs.infoInputEl.value,
          image: this.refs.imgInputEl.value
      }

           ACTIONS.createNewCoffeeEntry(newEntry)

   },

      render: function(){

         return(
            <div className = "row text-center">
              <form className = "col-sm-offset-3 col-sm-6" id = "submit-form" onSubmit ={this._submitForm}>
                <a href = "#"><i className = "fa fa-home fa-2x" aria-hidden = "true"></i></a>
                <h2 className = "">Submit a Coffee Shop</h2>
                <div className = "form-group">
                  <label htmlFor = "name">Shop Name</label>
                  <input type = "text" className = "form-control" name = "name" ref="nameInputEl" placeholder = "Enter Shop Name" />
                </div>
                <div className = "form-group">
                  <label htmlFor = "loc">Location</label>
                  <input type = "text" className = "form-control" name = "loacation" ref="locInputEl" placeholder = "Enter Location" />
                </div>
                <div className = "form-group">
                  <label htmlFor = "hours">Shop Hours</label>
                  <input type = "text" className = "form-control" name = "hours" ref="hoursInputEl" placeholder = "Enter Shop Hours" />
                </div>
                <div className = "form-group">
                  <label htmlFor = "web">WebSite</label>
                  <input type = "text" className = "form-control" name = "website" ref="webInputEl" placeholder= "Enter Website" />
                </div>
                <div className = "form-group">
                  <label htmlFor = "web">Rating</label>
                  <input type = "text" className = "form-control" name = "likes" ref="rateInputEl" placeholder= "Enter Rating" />
                </div>
                <div className = "form-group">
                  <label htmlFor = "image">Image</label>
                  <input type = "text" className = "form-control" id = "image" ref="imgInputEl" placeholder = "Enter An Image" />
                </div>
                <div className = "form-group">
                  <label htmlFor = "info">Description</label>
                  <input type = "text" className = "form-control" id = "info" ref="infoInputEl" placeholder = "Enter A Description" />
                </div>
                <input type = "submit" className = "btn btn-default" />
              </form>
            </div>
         )
      }

})

module.exports = SubmitView
