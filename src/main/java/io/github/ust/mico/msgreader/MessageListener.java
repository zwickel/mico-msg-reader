package io.github.ust.mico.msgreader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class MessageListener {


    /**
     * Used for messaging via stomp and websockets
     */
    @Autowired
    SimpMessagingTemplate websocketsTemplate;

    /**
     * Entry point for incoming messages from kafka.
     *
     * @param message
     */
    @KafkaListener(topics = "${kafka.input-topic}", groupId = "${kafka.group-id}")
    public void receive(String message) {
        log.debug("Received CloudEvent message: {}", message);
        websocketsTemplate.convertAndSend("/topic/messaging-bridge", message);
    }

}
