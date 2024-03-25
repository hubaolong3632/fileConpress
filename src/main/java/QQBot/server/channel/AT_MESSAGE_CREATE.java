package QQBot.server.channel;

import QQBot.model.group.GroupModel;
import QQBot.server.HttpQQBotServer;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class AT_MESSAGE_CREATE {
    //    处理邮件消息
    public void Mail_User(GroupModel group) {

        String message = group.getMessage();

        if (message.contains("/写信给自己")) {
            message(group,"已经写了一封信请等待未来某一时刻推送到你的邮箱中");

        } else if (message.contains("/收一封")) {
            message(group, """
                    亲爱的陌生人，
                                        
                    我写信是想表达我对你的感激之情。感谢你在我需要帮助时伸出援手，帮我完成了这个项目。
                                        
                    你的专业知识和经验帮助了我解决了许多困扰我很久的问题。你的指导和建议让我更加了解了这个领域，并且提升了我的技能。我深深感激你对我的支持和耐心。
                                        
                    没有你的帮助，我可能无法按时完成任务。你的帮助对我来说非常宝贵，我无法用言语来表达我的感激之情。
                                        
                    再次感谢你的慷慨帮助。我相信我会继续努力学习和成长，以回报你的支持和信任。
                                        
                    祝好，
                                        
                    一封
                    """);


        } else if (message.contains("/指定邮箱写信")) {
            message(group,"感谢写邮件将在某一时间推送给哪个她");

        } else if (message.contains("/写信给随机人")) {
            message(group,"已写,请等待有缘人接收哦");

        }
    }

    //        发送消息频道
    public void message(GroupModel group, String message){
        try {
            JSONObject jsonBody = new JSONObject();

            jsonBody.put("content",  message); //回答的消息
            jsonBody.put("msg_id",group.getMessageId()); //接入上一条回答id
            String requestBody = jsonBody.toString(); //将JSON对象转换为字符串
            //         创建连接并设置请求头和请求体
            Connection connection = Jsoup.connect("https://api.sgroup.qq.com/channels/"+group.getGroupId()+"/messages")
                    .header("Content-Type", "application/json")
                    .header("Authorization", HttpQQBotServer.BotKeyAuthorization)
                    .ignoreContentType(true)
//                                        .method(Connection.Method.POST) //执行类型但是下面可以直接指定
                    .requestBody(requestBody);

            // 发送请求并获取响应
            Document doc =connection.post();
            String jsonStr = doc.text();
            System.out.println("运行程序后的参数:" + jsonStr);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
