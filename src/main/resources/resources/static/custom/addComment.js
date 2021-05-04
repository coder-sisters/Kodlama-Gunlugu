function addComment() {
	var param = {
		name: $("#cName").val(),
		email: $("#cEmail").val(),
		website: $("#cWebsite").val(),
		message: $("#cMessage").val()
	}
	var ser_data = JSON.stringify(param);
	
	$.ajax({
		type: "POST",
		contentType: 'application/json; charset=UTF-8',
		url: 'addComment',
		data: ser_data,
		success: function(data) {
			alert(data);
		},
		error: function(data) {
			alert(" add comment çalıştım HATA", data);
		}

	});
}