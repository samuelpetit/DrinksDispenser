$(document).ready(function() {
	var content = $("#returnedCoinsContent").val();
	if(content != null) {
		var returnedCoins = $.parseJSON(content);
		console.log("returned", returnedCoins);
		$("#returnedMoney").append(displayReturnedCoins(returnedCoins));
	}
});

//This method is used to display coins returned to customer
displayReturnedCoins = function(returnedCoins) {
	var displayContent = "";
	for(coin in returnedCoins) {
		if(returnedCoins[coin] != 0) {
		    displayContent += "<span><b>" + coin + "FCH </b> : <b>" + returnedCoins[coin] + "</b> coins returned</span></br>";
	    }
	}
	return displayContent;
};