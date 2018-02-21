var Login = function() {
	var $inputLogin, $formLogin;
	return {
		init : function() {
			$inputLogin = $('#login');
			$formLogin = $('#loginForm');

			/* INITIALIZER FUNCTIONS */
			Login.validar_form_login();
			Login.close_alerts();
			Login.open_alerts();
			Login.toLowerCaseLogin();
		},
		open_alerts : function() {
			$('.alert').fadeIn(100);
		},
		close_alerts : function() {
			var t = 10000;

			setTimeout(function() {
				$('.alert').fadeOut();
			}, t);
		},
		validar_form_login : function() {
			$formLogin.validate({
				rules : {
					login : {
						required : true,
					},
					password : {
						required : true,
					},

				},
				highlight : function(element) {
					$(element).closest('.form-group').addClass('has-error');
				},
				unhighlight : function(element) {
					$(element).closest('.form-group').removeClass('has-error');
				},
				errorElement : 'span',
				errorClass : 'help-block',
				errorPlacement : function(error, element) {
					if (element.parent('.input-group').length) {
						error.insertAfter(element.parent());
					} else if (element.parent('.radio-inline').length) {
						error.insertAfter(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				},
				messages : {
					login : {
						required : "Por favor, informe seu login",
					},
					password : {
						required : "Por favor, informe sua senha",
					}
				}
			});

		},
		verificar_login_cpf : function() {
			var mask = '999.999.999-99';
			$inputLogin.on('keypress', function() {
				var input = $(this);
				var inputLoginVal = input.val();
				// var result = /\d{11}/g.test(inputLoginVal);
				if (!isNaN(parseFloat(inputLoginVal))
						&& isFinite(inputLoginVal)) {
					input.mask(mask);
					input.val(inputLoginVal);
				} else {
					input.unmask();
				}
			});
		},
		toLowerCaseLogin : function(){
			$inputLogin.val($inputLogin.val().toLowerCase());
			$inputLogin.on('keyup', function () {
				var input = $(this).val();
				if(input != ''){
					$(this).addClass('toLowerCaseLogin');
				} else {
					$(this).removeClass('toLowerCaseLogin');
				}
			});
		}
	}
}();