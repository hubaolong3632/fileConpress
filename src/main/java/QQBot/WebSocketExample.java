package QQBot;

import QQBot.model.group.GroupModel;
import QQBot.model.privateChat.privateChatModel;
import QQBot.server.HttpQQBotServer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class WebSocketExample {
    public static void main(String[] args) {
        HttpQQBotServer qqBotServer=new HttpQQBotServer(); //客户端
        while (true) {
            try {
                String serverURL = "wss://api.sgroup.qq.com/websocket/";  // WebSocket服务器的URL
                WebSocketClient client = new WebSocketClient(new URI(serverURL)) {
                    @Override
                    public void onOpen(ServerHandshake handshakeData) {
                        System.out.println("连接成功");
//                                        "intents": 33554434,
//                        "intents": 1812730883,
                        send(String.format("""
                                {
                                    "op": 2,
                                    "d": {
                                        "token": "%s",

                                        "intents": 1107300354,
                                        "shard": [
                                            0,
                                            1
                                        ],
                                        "properties": {
                                            "$os": "linux",
                                          "$browser": "my_library",
                                            "$device": "my_library"
                                        }
                                    }
                                }
                                """,HttpQQBotServer.BotKeyAuthorization));  // 在连接建立时发送消息

                    }

                    @Override
                    public void onMessage(String message){
                        JSONObject js = JSON.parseObject(message);
                        Integer op = js.getInteger("op"); //发送的区别 0代表服务器推送
                        String t = js.getString("t"); //消息类型
                        if (op == 11) { //||op==0
                            return;
                        }


                        System.out.println("收到消息:" + message);
                        if (t.equals("AT_MESSAGE_CREATE") || t.equals("MESSAGE_CREATE")) { //如果为频道群消息事件
                            //                    String content = js.getString("content");
                            JSONObject d = js.getJSONObject("d");
                            String id = js.getString("id");
                            System.out.println("----------------------------------");
                            String content = d.getString("content");
                            String channel_id = d.getString("channel_id");
                            String guild_id = d.getString("guild_id");
                            System.out.println("消息id:"+id);
                            System.out.println("用户消息:"+content);
                            System.out.println("频道号:"+channel_id);
                            System.out.println("用户id:"+guild_id);

//                            userId; //用户id
//                            message; //消息
//                            messageId; //消息id
//                            groupId; //群聊号码
                            qqBotServer.groupsMessages(new GroupModel(guild_id,content,id,channel_id),t); //走向中专站


                        }else if(t.equals("GROUP_AT_MESSAGE_CREATE")){ //qq群聊艾特消息
                            JSONObject d = js.getJSONObject("d");
                            String content = d.getString("content"); //消息内容
                            String contentId = d.getString("id"); //消息id
                            String userid =d.getJSONObject("author").getString("id"); //用户id
                            String groupId = d.getString("group_id"); //群聊id

                            System.out.println("\n-----------------QQ群聊--------------------------");
                            System.out.println("群聊id："+groupId);
                            System.out.println("用户id："+userid);
                            System.out.println("消息id："+contentId);
                            System.out.println("群聊消息:"+content);
                            content= content.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n");

                            qqBotServer.groupsMessages(new GroupModel(userid,content,contentId,groupId),t); //走向中专站
                            System.out.println("-------------------------------------------\n\n");

                        }else if(t.equals("DIRECT_MESSAGE_CREATE")){ //如果为私信消息
                            JSONObject d = js.getJSONObject("d");

                            String messageId = d.getString("id"); //消息id
                            String message_content = d.getString("content"); //消息内容

                            String guild_id = d.getString("guild_id"); //私信人id


                            qqBotServer.groupsMessages(new privateChatModel(messageId,message_content,guild_id),t); //走向中专站


                            System.out.println("私聊");
                        }


                    }

                    @Override
                    public void onClose(int code, String reason, boolean remote) {
                        System.out.println("连接关闭");
                    }

                    @Override
                    public void onError(Exception ex) {
                        System.out.println("发生错误：" + ex.getMessage());
                    }
                };

                client.connect();//启动websocket
                Thread.sleep(1000); //等待1秒连接
                try{
                    while (true) {
                            client.send("""
                                            {
                                                "op":1,
                                                "d":251
                                            }
                                        """);
                        Thread.sleep(10000); //每10秒发送一次用来防止服务器断掉连接
                    }
                }catch (Exception e){
                    System.out.println("发送心跳包失败");
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}