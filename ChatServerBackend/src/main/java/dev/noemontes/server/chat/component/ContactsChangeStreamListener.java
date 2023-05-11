package dev.noemontes.server.chat.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import dev.noemontes.server.chat.model.UserModel;

/**
 * @author dark-wave
 * @since 1.0.0
 *
 * Clase encargada de escuchar cambios en la base de datos y notificarlos
 * via websocket a los clientes.
 */
@Component
public class ContactsChangeStreamListener extends AbstractMongoEventListener<UserModel> {
	@Autowired
	SimpMessagingTemplate messagingTemplate;
	
    @Override
    public void onAfterSave(AfterSaveEvent<UserModel> event) {
        System.out.println("onAfterSave({}, {})" + event.getSource() + event.getDocument());
        
        //Publicamos los contactos al usuario específico usando websockets
    }
}
