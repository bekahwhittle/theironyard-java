
const getPos = navigator.geolocation.getCurrentPosition(function(position) {
  console.log("it works" position.coords.latitude, position.coords.longitude);
});
module.exports = {
  getPos
}
// OR

const getPosByIP = function(){
  // let userIP = logic for grabbing userIP
  $.getJSON('freegeoip.net/{format}/{'+ userIP +'}').then(console.log)
}
