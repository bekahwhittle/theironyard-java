
// thanks google for having a nifty javascript writeup
// to query for user-location!

const initMap = function() {
        var map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: -34.397, lng: 150.644},
          zoom: 6
        });
        var infoWindow = new google.maps.InfoWindow({map: map});

        // Try HTML5 geolocation.
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function(position) {
            var pos = {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            };

            infoWindow.setPosition(pos);
            infoWindow.setContent('Location found.');
            map.setCenter(pos);
          }, function() {
            handleLocationError(true, infoWindow, map.getCenter());
          });
        } else {
          // Browser doesn't support Geolocation
          handleLocationError(false, infoWindow, map.getCenter());
        }
      }
const handleLocationError = function(browserHasGeolocation, infoWindow, pos) {
  infoWindow.setPosition(pos);
  infoWindow.setContent(browserHasGeolocation ?
                        'Error: The Geolocation service failed.' :
                        'Error: Your browser doesn\'t support geolocation.');
}

const returnCoffeeShops = function(){
  let coffeeShops = [];
  coffeeShops.get("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" +userLocation+ "keyword=coffee&key=AIzaSyCs5x2Pf3lrXOD0-TVVuEJ1oWSsT_l1_o8");
  console.log(coffeeShops);
}
module.exports {initMap, handleLocationError, returnCoffeeshops}
