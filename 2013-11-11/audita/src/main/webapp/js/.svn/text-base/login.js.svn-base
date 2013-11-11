$(function() {
	var logina = function() {
		$("#login").submit();
	};
	$("#restButton").click(function() {
				$("#userAccount").val("");
				$("#password").val("");
	});
	$("#loginButton").click(logina);
	document.onkeydown = keyDown ;
	/*$("#account").keydown(function(event) {
		switch (event.keyCode) {
		case 13:
			$("#pwd").focus();
			break;
		default:
			break;
		}
	});
	$("#pwd").keydown(function(event) {
		switch (event.keyCode) {
		case 13:
			$("#code").focus();
			break;
		default:
			break;
		}
	});
	$("#code").keydown(function(event) {
		switch (event.keyCode) {
		case 13:
			$("#loginButton").click();
			break;
		default:
			break;
		}
	});*/
});
function keyDown(){     
    if(event.keyCode==13)
    {
    	$("#login").submit();
        return false;                               
    }
}

