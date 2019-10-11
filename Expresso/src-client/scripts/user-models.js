import Backbone from 'backbone';
import $ from 'jquery';

const UserModel = Backbone.Model.extend({
   url: 'http://localhost:8080/user',

      initialize: function(){

      }
})

const ShopModel = Backbone.Model.extend({
   model: UserModel,
   url: 'http://localhost:8080/shop',

      initialize: function(){

      }
})

const UserCollection = Backbone.Collection.extend({
   model: UserModel,
   url: 'http://localhost:8080/shop',

      initialize: function(){

      }
})
const LikeModel = Backbone.Model.extend({
  url: 'http://localhost:8080/like-shop',

      initialize: function(){

      }
})

module.exports = {
   UserModel,
   UserCollection,
   ShopModel,
   LikeModel

}
