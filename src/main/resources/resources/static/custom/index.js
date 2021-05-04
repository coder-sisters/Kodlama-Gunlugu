$(document).ready(function() {

	getArticles();

	setInterval(function() { getArticles(); }, 90000);

});

function getArticles() {
	
	$.ajax({
		type: "POST",
		url: 'getArticles',
		success: function(data) {
			var list = "";
			$(data).each(function(i, val) {
				list = list


					+ '<div align="center" class="container">'
					+ '<div align="center" class="column large-10">'
					+ '<h3 align="center"><a href="single-standard/' + val.id + '">' + val.title + '</a></h3>'
					+ '<p >' + val.content + '</p>'
					+ '</div>'

				

			});
			$("#list").html(list);
		},
		error: function(data) {
			alert(" get article çalıştım HATA", data);
		}
	});
}
