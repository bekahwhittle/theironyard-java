const Backbone = require('backbone');


const UserModel = Backbone.Model.extend({
   url:"",

   initialize: function(str){
      this.url = str
   }
})

module.exports = {
   UserModel
}



module.exports = {
   UserModel
}
