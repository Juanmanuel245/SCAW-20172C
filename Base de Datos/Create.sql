CREATE TABLE Usuarios(
id int NOT NULL PRIMARY KEY,
eMail varchar(255) NOT NULL,
contraseña varchar(255) NOT NULL,
apellido varchar(255) NOT NULL,
nombre varchar(255) NOT NULL,
idEstadoUsuario int);

CREATE TABLE Roles(
id int NOT NULL PRIMARY KEY,
descripcion varchar(50) NOT NULL);

CREATE TABLE EstadosUsuarios(
id int NOT NULL PRIMARY KEY,
descripcion varchar(50) NOT NULL);

CREATE TABLE RolesUsuarios(
idUsuario int NOT NULL,
idRol int NOT NULL);

CREATE TABLE Materias(
id int PRIMARY KEY NOT NULL IDENTITY,
nombre varchar(255) NOT NULL,
idDocenteTitular int,
idEstadoMateria int);

CREATE TABLE EstadosMaterias(
id int NOT NULL PRIMARY KEY,
descripcion varchar(50) NOT NULL);

CREATE TABLE Examenes(
id int NOT NULL PRIMARY KEY,
nombre varchar(255) NOT NULL,
IdMateria int,
idEstadoExamen int);

CREATE TABLE EstadosExamenes(
id int NOT NULL PRIMARY KEY,
descripcion varchar(50) NOT NULL);

CREATE TABLE Preguntas(
id int NOT NULL PRIMARY KEY,
idExamen int NOT NULL,
pregunta varchar(255) NOT NULL);

CREATE TABLE Respuestas(
id int NOT NULL PRIMARY KEY,
idPregunta int NOT NULL,
respuesta varchar(255) NOT NULL,
idTipoRespuesta int NOT NULL);

CREATE TABLE TiposRespuestas(
id int NOT NULL,
descripcion varchar(50));




	public  boolean validarNombre(String nombre){
		Pattern patNom = Pattern.compile("[a-zA-Z]");
		Matcher mather = patNom.matcher(nombre);
		if(!mather.find()){
			return false;
		}
		
		return true;
	}
	
	public  boolean validarApellido(String Apellido){
		Pattern patApe = Pattern.compile("[a-zA-Z]");
		Matcher mather = patApe.matcher(apellido);
		if(!mather.find()){
			return false;
		}
		return true;
		
	}
	
	public boolean validarEmail(String eMail){
		Pattern patMail = Pattern.
				compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		
		Matcher mather = patMail.matcher(eMail);
		
		if(!mather.find()){
			return false;
		}
		
		return true;
	}