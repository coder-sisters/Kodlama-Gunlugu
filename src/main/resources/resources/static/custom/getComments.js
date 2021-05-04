$(document).ready(function() {

	getComments();


});

function getComments() {
	
	$.ajax({
		type: "POST",
		url: 'getComments',
		success: function(data) {
			var getList = "";
			
			$(data).each(function(i, val) {
				getList = getList
				
					+ '<h3 class="h2">Yorumlar</h3>';
					
			});
			$("#getList").html(getList);

		},
		error: function(data) {
			alert("get comment çalıştım HATA");
			
		
		}
	});
}
