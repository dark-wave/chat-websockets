package dev.noemontes.server.chat.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WebSocketSessionInfo {
    private String sessionId;
    private String username;
}
