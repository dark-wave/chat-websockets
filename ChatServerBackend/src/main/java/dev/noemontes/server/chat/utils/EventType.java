package dev.noemontes.server.chat.utils;

public enum EventType {
	contactRequest("00", "Solicitud de contacto");
	
	public final String codEvento;
	public final String descEvento;
	
	private EventType(String codEvento, String descEvento) {
		this.codEvento = codEvento;
		this.descEvento = descEvento;
	}


	public String getCodEvento() {
		return codEvento;
	}

	public String getDescEvento() {
		return descEvento;
	}
}
