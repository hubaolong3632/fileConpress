package QQBot.model.privateChat;

import QQBot.model.group.MessageModel;
import lombok.Data;

@Data
public class privateChatModel implements MessageModel { //群聊消息
   private String messageId; //消息id
    private String message; //消息
    private String guild_id; //发送人id

    public privateChatModel() {
    }

    public privateChatModel(String messageId, String message, String guild_id) {
        this.messageId = messageId;
        this.message = message;
        this.guild_id = guild_id;
    }
}
