const Backbone = require('backbone');

const UserModel = Backbone.Model.extend({
       url: "/userHome"

       initialize: function(){

       }
})

const UserCollection = Backbone.Collection.extend({
        model: UserModel,

        url:"/userHome",

        initialize: function(){

        }
})

   module.exports = {
      UserModel,
      UserCollection
   }
