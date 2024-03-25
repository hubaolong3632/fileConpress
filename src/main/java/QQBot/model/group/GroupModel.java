package QQBot.model.group;

import lombok.Data;

@Data
public class GroupModel implements MessageModel{ //群聊消息
   private String userId; //用户id
   private String message; //消息
   private String messageId; //消息id
   private String groupId; //群聊号码

    public GroupModel(String userId, String message, String messageId, String groupId) {
        this.userId = userId;
        this.message = message;
        this.messageId = messageId;
        this.groupId = groupId;
    }
}
