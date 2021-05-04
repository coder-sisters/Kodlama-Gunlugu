function addArticle() {
	var param = {
		title: $("#note_title").val(),
		content: $("#note_detail").val(),
	}
	var ser_data = JSON.stringify(param);
	$.ajax({
		type: "POST",
		contentType: 'application/json; charset=UTF-8',
		url: 'addArticle',
		data: ser_data,
		success: function(data) {
			alert(data);
		},
		error: function(data) {
		alert(" add article çalıştım HATA", data);
		}

	});
}