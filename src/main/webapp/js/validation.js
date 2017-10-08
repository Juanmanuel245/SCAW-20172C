/**
 * Validaciones de formulario editar usuario
 * 
 */

$(document).ready(function(){
	tipoAcc = $('#actualizarUsuario\\:accion').val();
	inicializaFormulario();
	
	$('#actualizarUsuario\\:error-nombre').addClass('ocultar');
	$('#actualizarUsuario\\:error-apellido').addClass('ocultar');
	$('#actualizarUsuario\\:error-mail').addClass('ocultar');
	$('#actualizarUsuario\\:error-pass').addClass('ocultar');
	$('#actualizarUsuario\\:error-rol').addClass('ocultar');
	
	$('#actualizarUsuario\\:nombre').on('blur',validarNombre);
	$('#actualizarUsuario\\:apellido').on('blur',validarApellido);
	$('#actualizarUsuario\\:eMail').on('blur',validarEmail);
	$('#actualizarUsuario\\:pass').on('blur',validarPass);
	$('#actualizarUsuario\\:rol').on('blur',validarRol);
	
	
	$('#actualizarUsuario\\:Guardar').click(function(e){
		e.preventDefault();
		tipoAcc = $('#actualizarUsuario\\:accion').val();
		if(tipoAcc == 'ED'){
			if(!validarNombre()){
				alert("El nombre ingresado es invalido");
			}else if(!validarApellido()){
				alert("El apellido ingresado es invalido");
			}else if(!validarEmail()){
				alert("El mail ingredo es invalido");
			}else if(!validarPass()){
				alert("La contraseña debe contener solo letras y numeros");
			}else if(!validarRol()){
				alert("Debe seleccionar un rol");
			}else{
				$(this).off('submit').submit();
			}
		}
		
	});
});

function validarNombre(){
	
	var nombre = $('#actualizarUsuario\\:nombre').val();
	 //Valido que nombre sean solo letras
	if(nombre.match(/^[a-zA-Z\s]*$/) || $('#actualizarUsuario\\:accion').val() == 'CO'){	
		$('#actualizarUsuario\\:error-nombre').addClass('ocultar');
		return true;
	}else{
		$('#actualizarUsuario\\:error-nombre').text('El nombre ingreaso es invalido');
		$('#actualizarUsuario\\:error-nombre').removeClass('ocultar');
		return false;
	}
	
}


function validarApellido(){
	
	var ape = $('#actualizarUsuario\\:apellido').val();
	 //Valido que nombre sean solo letras
	if(ape.match(/^[a-zA-Z\s]*$/) || $('#actualizarUsuario\\:accion').val() == 'CO'){
		$('#actualizarUsuario\\:error-apellido').addClass('ocultar');
		return true;
	}else{
		$('#actualizarUsuario\\:error-apellido').removeClass('ocultar');
		$('#actualizarUsuario\\:error-apellido').text('El apelldo ingresado es Invalido');
		return false;
	}
	
	
}

function validarPass(){
	var pass = $('#actualizarUsuario\\:pass').val();
	
	if(pass.match(/^[0-9a-zA-Z\s]*$/) || $('#actualizarUsuario\\:accion').val() == 'CO'){
		$('#actualizarUsuario\\:error-pass').addClass('ocultar');
		return true;
	}else{
		$('#actualizarUsuario\\:error-pass').removeClass('ocultar');
		$('#actualizarUsuario\\:error-pass').text('La contraseña debe tener letras y numeros solamente');
	}
}

function validarEmail(){
	
	var mail = $('#actualizarUsuario\\:eMail').val();
	 //Valido que el mail sea valido
	if(mail.match(/^[a-zA-Z0-9\._-]+@[a-zA-Z0-9-]{2,}[.][a-zA-Z]{2,4}$/)
			|| $('#actualizarUsuario\\:accion').val() == 'CO'){
		$('#actualizarUsuario\\:error-mail').addClass('ocultar');
		$('#actualizarUsuario\\:Guardar').attr('disable');
		return true;
	}else{
		$('#actualizarUsuario\\:error-mail').removeClass('ocultar');
		$('#actualizarUsuario\\:error-mail').text('Ingrese una direccion de mail valida');
		return false;
	}
	
}

function validarRol(){
	var idRol = $('#actualizarUsuario\\:idRol').val();
	
	if(idRol && idRol != ""){
		$('#actualizarUsuario\\:error-rol').addClass('ocultar');
		return true;
	}else{
		$('#actualizarUsuario\\:error-rol').removeClass('ocultar');
		$('#actualizarUsuario\\:error-rol').text('Debe seleccionar un rol');
		return false;
	}
	
}


function inicializaFormulario(){
		
	$('#actualizarUsuario\\:nombre').removeClass("ok error");
	$('#actualizarUsuario\\:apellido').removeClass("ok error");
	$('#actualizarUsuario\\:eMail').removeClass("ok error");
	
	if (tipoAcc === 'CO'){
		$('#actualizarUsuario\\:nombre').attr('readonly',true);
		$('#actualizarUsuario\\:apellido').attr('readonly',true);
		$('#actualizarUsuario\\:eMail').attr('readonly',true);
		
		$('#actualizarUsuario\\:nombre').attr('disable',true);
		$('#actualizarUsuario\\:apellido').attr('disable',true);
		$('#actualizarUsuario\\:mail').attr('disable',true);
		$('#actualizarUsuario\\:Guardar').attr('disable',true);
		$('#actualizarUsuario\\:Guardar').hide();
	}
	
	var error = $('#actualizarUsuario\\:error').val();
	
	if(error || error == ""){
		$('#actualizarUsuario\\:error').removeClass('ocultar');
	}
	else{
		$('#actualizarUsuario\\:error').addClass('ocultar')
	}
	
}
	

