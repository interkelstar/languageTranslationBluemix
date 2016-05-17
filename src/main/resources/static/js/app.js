$( document ).ready(function() {
  
  //***************************************************
  // U T I L I T Y  F U N C T I O N S
  //***************************************************
  
  function htmlEncode(value){
    //create a in-memory div, set it's inner text(which jQuery automatically encodes)
    //then grab the encoded contents back out.  The div never exists on the page.
    return $('<div/>').text(value).html();
  }
  
  //***************************************************
  // C L I C K  L I S T E N E R S
  //***************************************************
  
  $('#translateBtn').click(function() {
    $.ajax({
      type: "POST",
      url: "/translate",
      data: JSON.stringify({
        "text": $('#input').val().trim()}),
      contentType: "application/json"
    }).done(function(data) {
      $('#output').text(data.text);
    });
    return false;
  });
  
});