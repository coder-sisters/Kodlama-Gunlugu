function login() {
	var param = {
		username: $("#username").val(),
		password: $("#pass").val()

	}
	var ser_data = JSON.stringify(param);

	$.ajax({
		type: "POST",
		contentType: 'application/json; charset=UTF-8',
		url: 'controlUser',
		data: ser_data,
		success: function(data) {
			 if (data == 'ok') {
				$(location).attr('href', '/anasayfa')
				
			} else if (data == 'error') {
				alert("Giriş başarısız !");
			}
		},
		error: function(data) {
		alert(" login çalıştım HATA", data);
		}

	});
}