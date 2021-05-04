$(document).ready(function() {

	singleStandard();

});

function singleStandard() {
		
	$.ajax({

		type: "POST",
		url: './../getSingleStandard',
		contentType: 'text/plain',
		data: $("#id").val() + "",
		success: function(data) {
			$("#title").html(data.title);
			$("#article_title").html(data.title);
			$("#article_content").html(data.content);
			$("#article_date").html(new Date(data.create_date));
			
		},
		error: function(data) {
		alert(" single standard çalıştım HATA" , data);
			
		}
	});
}
