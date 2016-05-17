$( document ).ready(function() {
  
  var languages = {
    "ar": {
      name: "Arabic",
      langs: ["en"]
    },
    "arz": {
      name: "Egyptian",
      langs: ["en"]
    },
    "en": {
      name: "English",
      langs: ["ar", "arz", "es", "fr", "it", "pt"]
    },
    "es": {
      name: "Spanish",
      langs: ["en", "fr"]
    },
    "fr": {
      name: "French",
      langs: ["en", "es"]
    },
    "it": {
      name: "Italian",
      langs: ["en"]
    },
    "pt": {
      name: "Portuguese",
      langs: ["en"]
    }
  };
  
  //***************************************************
  // U T I L I T Y  F U N C T I O N S
  //***************************************************
  
  function htmlEncode(value){
    //create a in-memory div, set it's inner text(which jQuery automatically encodes)
    //then grab the encoded contents back out.  The div never exists on the page.
    return $('<div/>').text(value).html();
  }

  //***************************************************
  // K E Y  L I S T E N E R S
  //***************************************************
  
  $("#languageFrom").change(function () {
    var selected = $(this).val();
    var languageTo = $("#languageTo").empty();
    languages[selected].langs.forEach(function (item) {
      languageTo.append("<option value='" + item + "'>" + languages[item].name + "</option>")
    })
  }).trigger("change");
  
  //***************************************************
  // C L I C K  L I S T E N E R S
  //***************************************************
  
  $('#translateBtn').click(function() {
    $.ajax({
      type: "POST",
      url: "/translate" + "?from=" + $("#languageFrom").val() + "&to=" + $("#languageTo").val(),
      data: JSON.stringify({
        "text": $('#input').val().trim()}),
      contentType: "application/json"
    }).done(function(data) {
      $('#output').text(data.text);
    }).fail(function (data) {
      // set the message to display: none to fade it in later.
      var message = $('<div class="alert alert-danger error-message" style="display: none;">');
      // a close button
      var close = $('<button type="button" class="close" data-dismiss="alert">&times</button>');
      message.append(close); // adding the close button to the message
      message.append(data.responseJSON.message); // adding the error response to the message
      // add the message element to the body, fadein, wait 3secs, fadeout
      message.appendTo($('body')).fadeIn(300).delay(3000).fadeOut(500);
    });
    return false;
  });
  
});