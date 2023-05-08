package dev.noemontes.server.chat.component;

import com.mongodb.client.model.changestream.FullDocument;
import dev.noemontes.server.chat.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.ChangeStreamEvent;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class ContactsChangeStreamListener {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    //Evento lanzado cuando la aplicación está lista para ser usada
    @EventListener(ApplicationReadyEvent.class)
    public void registerListener(){
        ChangeStreamOptions options = ChangeStreamOptions.builder()
                .filter(Aggregation.newAggregation(UserModel.class,
                        Aggregation.match(Criteria.where("_id").is("64484ca2c6ac0e7f9ea77d4f"))))
                .fullDocumentLookup(FullDocument.UPDATE_LOOKUP)
                .build();

        Flux<ChangeStreamEvent<UserModel>> userChangeStream = mongoTemplate.changeStream("users", options, UserModel.class);
        userChangeStream.subscribe(event -> {
            String userUuid = event.getBody().getUuid();
            UserModel updatedUser = event.getBody();
            messagingTemplate.convertAndSendToUser(userUuid, "/queue/contacts", updatedUser);
        });
    }
}
