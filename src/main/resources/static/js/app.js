$( document ).ready(function() {
  
  var languages = {
    "ar": {
      name: "Arabic",
      langs: ["en"],
      canBeSpoken: false
    },
    "arz": {
      name: "Egyptian",
      langs: ["en"],
      canBeSpoken: false
    },
    "en": {
      name: "English",
      langs: ["ar", "arz", "es", "fr", "it", "pt"],
      canBeSpoken: true
    },
    "es": {
      name: "Spanish",
      langs: ["en", "fr"],
      canBeSpoken: true
    },
    "fr": {
      name: "French",
      langs: ["en", "es"],
      canBeSpoken: true
    },
    "it": {
      name: "Italian",
      langs: ["en"],
      canBeSpoken: true
    },
    "pt": {
      name: "Portuguese",
      langs: ["en"],
      canBeSpoken: true
    }
  };

  //***************************************************
  // U T I L I T Y  F U N C T I O N S
  //***************************************************

  function synthesizeRequest(options, audio) {
    var downloadURL = '/textToSpeech' +
        '?lang=' + options.lang +
        '&text=' + encodeURIComponent(options.text);
    
    audio.pause();
    audio.src = downloadURL;
    audio.addEventListener('canplaythrough', onCanplaythrough);
    audio.muted = true;
    audio.play();
    $('body').css('cursor', 'wait');
    $('#speakBtn').css('cursor', 'wait');      

    return true;
  }
  
  function createErrorMessage(errorMessage) {
    // set the message to display: none to fade it in later.
    var message = $('<div class="alert alert-danger error-message" style="display: none;">');
    // a close button
    var close = $('<button type="button" class="close" data-dismiss="alert">&times</button>');
    message.append(close); // adding the close button to the message
    message.append(errorMessage); // adding the error response to the message
    // add the message element to the body, fadein, wait 3secs, fadeout
    message.appendTo($('body')).fadeIn(300).delay(3000).fadeOut(500);
  }

  //***************************************************
  // E V E N T  L I S T E N E R S
  //***************************************************

  function onCanplaythrough() {
    console.log('onCanplaythrough');
    var audio = $('#audio').get(0);
    audio.removeEventListener('canplaythrough', onCanplaythrough);
    audio.currentTime = 0;
    audio.controls = true;
    audio.muted = false;
    $('body').css('cursor', 'default');
    $('#speakBtn').css('cursor', 'pointer');
  }

  $("#audio").on("error", function () {
    createErrorMessage("Error playing audio!");
    $('body').css('cursor', 'default');
    $('#speakBtn').css('cursor', 'pointer');
  });
  
  //***************************************************
  // K E Y  L I S T E N E R S
  //***************************************************
  
  $("#languageFrom").change(function () {
    var selected = $(this).val();
    var languageTo = $("#languageTo").empty();
    languages[selected].langs.forEach(function (item) {
      languageTo.append("<option value='" + item + "'>" + languages[item].name + "</option>")
    }); 
    languageTo.trigger("change");
  }).trigger("change");  
  
  $("#languageTo").change(function () {
    var selected = $(this).val();
    if (languages[selected].canBeSpoken) {
      $("#speakBtn").removeClass("disabled");
    } else {
      $("#speakBtn").addClass("disabled");
    }
  }).trigger("change");
  
  //***************************************************
  // C L I C K  L I S T E N E R S
  //***************************************************
  
  $('#translateBtn').click(function() {
    $('body').css('cursor', 'wait');
    $('#translateBtn').css('cursor', 'wait');
    $.ajax({
      type: "POST",
      url: "/translate" + "?from=" + $("#languageFrom").val() + "&to=" + $("#languageTo").val(),
      data: JSON.stringify({
        "text": $('#input').val().trim()}),
      contentType: "application/json"
    }).done(function(data) {
      $('#output').val(data.text);
      $('body').css('cursor', 'default');
      $('#translateBtn').css('cursor', 'pointer');
    }).fail(function (data) {
      createErrorMessage(data.responseJSON.message);
    });
    return false;
  });

  $('#speakBtn').click(function(evt) {
    evt.stopPropagation();
    evt.preventDefault();
    var options = {
      text: $('#output').val().trim(),
      lang: $("#languageTo").val()
    };
    synthesizeRequest(options, audio);
    return false;
  });
  
});