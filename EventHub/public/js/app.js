

var genericEvents = [
   {
      author: 'Super Cool Events Inc.',
      name: 'Beer Fest 2016',
      text: 'Come to this years Beer Fest in Hampton Park!',
      date: 'Neveruary The First',
      image: 'http://i.huffpost.com/gen/1393359/images/o-PINT-GLASS-BEER-facebook.jpg',
      website: 'www.beer.com',
      location: 'Hampton Park, Charleston SC'

   },
   {
      author: 'Awesome Event Creators',
      name: 'Rat Rod Car Event',
      text: 'Come Check out all the cool cars!',
      date: 'October 30th, 1957',
      image: 'http://just-rat-rods.com/images/international-1b.jpg',
      website: 'www.google.com',
      location: 'Harley Davidson Parking Lot'

   },
   {
      author: 'Toys For Tots',
      name: 'Toy Drive',
      text: 'Help Donate Toys to kids who need them!',
      date: 'December 23rd, 2016',
      image: 'http://www.leadinglady.com/blog/wp-content/uploads/2015/02/toy_sprawl1.jpg',
      website: 'www.google.com',
      location: 'Citadel Mall'

   },
   {
      author: 'Charleston Police Dept.',
      name: 'K9 Police Dog Event',
      text: 'Check out the police dogs in action.',
      date: 'April 20th, 2017',
      image: 'http://images.huffingtonpost.com/2015-05-27-1432699536-9008783-20150511SeattlePoliceDogsHUFFPO_js_01.JPG',
      website: 'http://www.policek9.com/',
      location: 'James Island County Park'

   },
   {
      author: 'L.A.R.P of Charleston',
      name: 'Charleston L.A.R.P othon',
      text: 'Come join Charlestons biggest larping event!',
      date: 'November 1, 2016',
      image: 'http://67.media.tumblr.com/e85b531650c660fb15a5016d73e037ab/tumblr_nvd063OJC81ugxwewo1_1280.jpg',
      website: 'larping.org',
      location: 'River Front Park'

   }


]




var appElSelector = document.querySelector('#app-container')
// BACKEND KEYS - HTML CLASSES
// String author; event-host
// String name; event-name
// String text; event-info
// String date; event-date
// String image; image-url
// String website; event-website
// String location; event-location





// button SELECTOR for event Submit Page

var homePageBuilder = function(){

   var homePageStr = '<div class="jumbotron text-center">'
      homePageStr += '<h1>Charleston Event Hub</h1>'
      homePageStr += '</div>'
      homePageStr += '<div class="col-md-12 top">'
      homePageStr += '<a href="#postevent"><button class="btn btn-primary create-event">Create An Event!!!!</button></a>'
      homePageStr += '</div>'
      homePageStr += '<div class ="event-container"></div>'
      homePageStr += '<div class="col-md-12 top">'
      homePageStr += '</div>'

      appElSelector.innerHTML= homePageStr
}

// ***********
// OBJECT CREATOR FUNCTION / POSTS TO SERVER
// ***********

var eventObjectCreator = function(){
//
//    // INPUT SELECTORS FOR EVENT CREATOR PAGE
//    // ***********************************
   var creatorSelector = document.querySelector('.event-host')
   var infoSelector = document.querySelector('.event-info')
   var nameSelector = document.querySelector('.event-name')
   var dateSelector = document.querySelector('.event-date')
   var imgInpSelector = document.querySelector('.image-url')
   var websiteSelector = document.querySelector('.event-website')
   var locSelector = document.querySelector('.event-location')
//    // ************************************
//
//
//
   var eventObj = {
      name: nameSelector.value,
      author: creatorSelector.value,
      text: infoSelector.value,
      date: dateSelector.value,
      image: imgInpSelector.value,
      website: websiteSelector.value,
      location: locSelector.value

   }
   $.post('/add-event', JSON.stringify(eventObj))
}

// INPUT EVENT LIST DATA ON HTML run on EMPTY #
var eventListBuilder = function(eventData){

   // var eventObj = {
   //
   //    author: creatorSelector.value,
   //    text: infoSelector.value,
   //    date: dateSelector.value,
   //    image: imgInpSelector.value,
   //    website: websiteSelector.value,
   //    location: locSelector.value
   //
   // }
   var evtContainerSelector = document.querySelector('.event-container')
   var bigStr = ''

   eventData.forEach(function(obj){

          bigStr +=  '<div class="col-md-12 event-tile">'
          bigStr +=  '<div class="head-name heading-footingstyle"><h1>' + obj.name + '</h1></div>'
          bigStr +=  '<div class="col-md-4"><h1><img src="' + obj.image +'" alt="https://www.walldevil.com/wallpapers/w01/awesome-face-meme-wallpaper.jpg" class="test"></h1></div>'
          bigStr +=  '            <div class="col-md-8 tile-description"><p>' + obj.text +'</p></div>'
          bigStr +=             '<div class="columns-container event-date-venueName">'
          bigStr +=            '<div class="Venue-Name-children"><h1>'+ obj.date + '</h1></div>'
          bigStr += '               <div class="Venue-Name-children"><h1>'+ obj.location +'</h1></div>'
          bigStr +=             '</div>'
          bigStr +=             '<div class="tile-footer heading-footingstyle"><h1>' + obj.website + '</h1></div>'
          bigStr +=                  '</div>'

   })

   evtContainerSelector.innerHTML = bigStr



}


var retrieveEventInfo = function(){

   $.getJSON('/events').then(eventListBuilder)

}

var eventBuilderPage = function(){
   var eventHtmlStr = '<div class="fluid-container text-center event-listing-container">'
       eventHtmlStr += '               <div class="row">'
       eventHtmlStr += '               <div class="event-page-header">'
       eventHtmlStr += '                  <h1>Create A Charleston Event</h1>'
       eventHtmlStr += '               </div>'
       eventHtmlStr += '               <h3 class="input-title">Event Host</h3>'
       eventHtmlStr +=               '<div class="input-group">'
       eventHtmlStr +=        '<input type="text" class="form-control event-host" id="basic-url" aria-describedby="basic-addon3">'
       eventHtmlStr += '       </div>'
       eventHtmlStr += '       <h3 class="input-title">Event Name</h3>'
       eventHtmlStr += '       <div class="input-group">'
       eventHtmlStr += '            <input type="text" class="form-control event-name"  id="basic-url" aria-describedby="basic-addon3">'
       eventHtmlStr += '       </div>'
       eventHtmlStr +=   '       <h3 class="input-title">Date of Event</h3>'
       eventHtmlStr += '       <div class="input-group">'
       eventHtmlStr += '            <input type="text" class="form-control event-date" id="basic-url" aria-describedby="basic-addon3">'
       eventHtmlStr += '       </div>'
       eventHtmlStr += '       <h3 class="input-title">Post An Image for your Event</h3>'
       eventHtmlStr += '       <div class="input-group">'
       eventHtmlStr += '            <input type="text" class="form-control image-url" placeholder="please enter image URL" id="basic-url" aria-describedby="basic-addon3">'
       eventHtmlStr += '       </div>'
       eventHtmlStr += '       <h3 class="input-title">Event Website</h3>'
       eventHtmlStr += '       <div class="input-group">'
       eventHtmlStr += '            <input type="text"  class="form-control event-website"  id="basic-url" aria-describedby="basic-addon3">'
       eventHtmlStr += '       </div>'
       eventHtmlStr += '       <h3 class="input-title">Location of Event</h3>'
       eventHtmlStr += '       <div class="input-group">'
       eventHtmlStr += '            <input type="text" class="form-control event-location"  id="basic-url" aria-describedby="basic-addon3">'
       eventHtmlStr += '       </div>'
       eventHtmlStr += '       <h3 class="input-title">About Event</h3>'
       eventHtmlStr += '       <div class="input-group">'
       eventHtmlStr += '            <input type="text" class="form-control event-info"  id="basic-url" aria-describedby="basic-addon3">'
       eventHtmlStr += '       </div>'
       eventHtmlStr += '       <a href="#"><button class="btn btn-primary submit-event">Submit Event</button></a>'
       eventHtmlStr += '   </div>'
       eventHtmlStr += '</div>'


   appElSelector.innerHTML = eventHtmlStr
   var submitSelector = document.querySelector('.submit-event')


   submitSelector.addEventListener('click', eventObjectCreator)

}





var hashRouter = function(){
   var hashStr = window.location.hash.slice(1)

   if(hashStr.length === 0){
      homePageBuilder()
      retrieveEventInfo()
   }
   switch(hashStr){

   case "postevent":
   eventBuilderPage()
   break;

   default:
}




}

var createEventEl = document.querySelector('.create-event')

window.addEventListener('hashchange', hashRouter)
hashRouter()
