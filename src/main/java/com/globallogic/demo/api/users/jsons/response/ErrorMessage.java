package com.globallogic.demo.api.users.jsons.response;

public class ErrorMessage {

	private String mensaje;
	
	public ErrorMessage() {
	}

	public ErrorMessage(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


}
