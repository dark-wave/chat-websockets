package dev.noemontes.server.chat.component;


import ch.qos.logback.core.net.SyslogOutputStream;
import dev.noemontes.server.chat.model.UserModel;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;

/**
 * @author dark-wave
 * @since 1.0.0
 *
 * Clase encargada de escuchar cambios en la base de datos y notificarlos
 * via websocket a los clientes.
 */
@Component
public class ContactsChangeStreamListener extends AbstractMongoEventListener<UserModel> {
    @Override
    public void onAfterSave(AfterSaveEvent<UserModel> event) {
        System.out.println("onAfterSave({}, {})" + event.getSource() + event.getDocument());
    }
}
