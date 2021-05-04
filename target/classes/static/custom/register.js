function addUser() {
	var param = {
		username: $("#username").val(),
		name: $("#name").val(),
		surname: $("#surname").val(),
		email: $("#email").val(),
		password: $("#pass").val(),
		password2: $("#pass2").val(),

	}
	var ser_data = JSON.stringify(param);
	
	$.ajax({
		type: "POST",
		contentType: 'application/json; charset=UTF-8',
		url: 'addUser',
		data: ser_data,
		success: function(data) {
			if (data == '1') {
				alert("parolalar eşleşmiyor");
			} else if (data == 'OK') {
				alert("Kayıt olma başarılı !");
			} else if (data == 'ERROR') {
				alert("Kayıt olma başarısız !");
			}

		},
		error: function(data) {
			alert(" add user çalıştım HATA" , data);
		}

	});
}