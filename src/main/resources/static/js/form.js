$(document).ready(function() {
	var modelContent = $("#formContent");
	var content = $.parseJSON(modelContent.val());
	
	if(content != null) {
		$("#availableCoins").append(displayAvailableCoins(content.coins));
		$("#availableDrinks").append(displayAvailableDrinks(content.drinks));
	}
	
	$(".insertedCoins").change(displayTotalAmountInserted);	
});

displayAvailableCoins = function(coins) {
	var availableCoins = "";
	if(coins != null) {
		availableCoins = "<table width=\"100%\" border=\"1\">";
		
		availableCoins += "<tr class=\"availableCoins\">";
			
		$.each(coins, function(index, coin) {
			availableCoins += "<td id=\"coin" + index + "></br>" +
					"<div></br>" +
						"<span class=\"coinValue\"><b>" + coin.value + " FCH</b></span></br>" +
					"</div>" + 
				"</td>";		
		});

		availableCoins += "<tr class=\"coinsChoice\"></br></tr>";
		
		$.each(coins, function(index, coin) {
			availableCoins += "<td id=\"choice" + index + "></br>" +
					"<div class=\"quantityGiven\"></br>" +
						"<label><input type=\"number\" min=\"0\" class=\"insertedCoins\" name=\"insertedCoins\" value=\"0\" /></label></br>" +
						"<span class=\"remainedQuantity\"> (Remained qty : <b>" + coin.availableQuantity + "</b>)</span>" +
				        "<input type=\"hidden\" id=\"coinValue" + index + "\" value=\"" + coin.value + "\"/></br>" +
				    "</div>" + 
				"</td>"; 
		});
		availableCoins += "</tr>";
		availableCoins += "</table";
	}
	return availableCoins;
};

displayAvailableDrinks = function(drinks) {
	var availableDrinks = "";
	if(drinks != null) {
		availableDrinks = "<table width=\"100%\" border=\"1\">";
		
		availableDrinks += "<tr class=\"availableDrinks\">";
			
		$.each(drinks, function(index, drink) {
			availableDrinks += "<td id=\"drink" + index + "\"></br>" +
					"<div class=\"drinkContent\"></br>" +
						"<span class=\"drinkImage\"></br>"+
							"<img src=\"\\images\\" + drink.name + ".png\"></br>" +
					    "</span></br>" +
						"<span class=\"drinkDescription\">" + drink.name + "</span></br>" +
					"</div>" + 
				    "<span class=\"price\"><b> Remained qty: " + drink.quantity + "</b></span></br>";		
		});

		availableDrinks += "<tr class=\"drinkChoice\"></br></tr>";
		
		$.each(drinks, function(index, drink) {
			var checkedValue = (index == 0) ? "checked=\"checked\"": "";
			
			availableDrinks += "<td id=\"choice" + index + "></br>" +
					"<div class=\"radio\"></br>" +
					    "<span class=\"price\"><b>" + drink.unitPrice + " FCH</b></span></br>" +
						"<label><input type=\"radio\" name=\"drinkChoice\" value=\"" + index + "\"" + checkedValue + "/></label></br>" +
					"</div>";
		
		});
		availableDrinks += "</tr>";	
		availableDrinks += "</table";
	}
	return availableDrinks;
};

displayTotalAmountInserted = function() {
	var coinQuantity;
	var coinsQuantity = 0;
	
	$.each($(".insertedCoins"), function(index, coinQuantityChoice) {
		coinQuantity = $(coinQuantityChoice).val();
		var coinValue = $("#coinValue" + index).val();
		coinsQuantity += coinQuantity ? parseInt(coinQuantity) * parseFloat(coinValue) : 0;
	});
	
	$("#totalAmountInserted").val(coinsQuantity.toFixed(2));
};