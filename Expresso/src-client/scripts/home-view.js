import React from 'react';
import ReactDOM from 'react-dom';
import Backbone from 'backbone';
import $ from 'jquery'
import ACTIONS from './user-actions.js'
import STORE from './user-store.js'
import AppController from './view-controller.js'

const HomeView = React.createClass({

  render: function() {

     let products = this.props.coffeeShopData.coffeeShopData.models.map(function(model){
       return <ProductView key={model.cid} model={model} />
     })
     console.log('products', products);

    return (
      <div className="home-container">

        <div className="jumbotron jumbotron-fluid">
          <div className="container">
            <h1 className="display-3"> <span className="coffee">☕ </span>Expresso<span className="coffee"> ☕</span></h1>
            <h3 className="lead">Expresso helps you find and review coffee shops near you.</h3>
          </div>
          <nav className="breadcrumb">
            <a className="breadcrumb-item" href="#user">Log in	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;	&nbsp;</a>
            <a className="breadcrumb-item" href="#submit">Submit	&nbsp; &nbsp;  &nbsp; &nbsp;   &nbsp;   &nbsp;   </a>
            <a className="breadcrumb-item" href="#delete-shop">Remove Submission</a>
          </nav>
          <div>

          </div>
        </div>

        <div className="container">
          <div className="row">

            { products }

          </div>

        </div>

      </div>

    );
  }
});

const ProductView = React.createClass({

    _updateLikes: function(){
      let newLikeData = {
         shopId: '',
         likedShop: true
      }
      ACTIONS.updateCoffeeLikes(newLikeData)

},

  render: function(){
     return(
          <div className="col-xs-12 col-md-4 col-lg-3 ">
            <div>
               <img src= {this.props.model.get('image')} />
            </div>
            <div className="col-xs-12 info-details">
              <h2>{this.props.model.get('name')}</h2>
              <p>Location</p>
              {this.props.model.get('location')}
              <p>Hours</p>
              {this.props.model.get('hours')}
              <p>Website</p>
              {this.props.model.get('website')}
              <p>Description</p>
              {this.props.model.get('info')}
              <div>
              <i className="fa fa-thumbs-up fa-4x" aria-hidden="true" onClick = {this._updateLikes}>
              <p>{this.props.model.get('likes')}</p></i>
              </div>
            </div>
          </div>
   )
  }

});

export { HomeView, ProductView };
