package QQBot.server;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.net.URLEncoder;

public class GptServer {
    private static GptServer gptServer=new GptServer();
    public static GptServer getGptServer(){
        return gptServer;
    }
    public String GPTKey="sk-SXau0Z1NukKCKeoj7b387914394a4395A4A4Fe5c7d547909"; //gpt的密钥

    public static void main(String[] args) {
        GptServer server = new GptServer();

//        String xx = server.GPTMessage("你好啊");
        String xx = server.GPTMessage4_dall_e_3("一直小猫");


//        String xx = server.GPTMessage4("你好aaa啊啊啊");
                System.out.println(xx);
    }




//    gpt连接接口
    public String GPTMessage(String message){
        message=message.substring(0, Math.min(message.length(), 200));
        try {
            System.out.println(message.length());

            Connection connection = Jsoup.connect("https://api.00000.work/gpt/gptNoStream?message=" + URLEncoder.encode(message, "UTF-8"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiLmma7pgJrnlKjmiLciLCJleHAiOjE3MDA5MTQ4ODIsImlzcyI6IlNLLTAwMDAwLndvcmsiLCJqd3Rtb2RlbCI6IntcIm5pY2tuYW1lXCI6XCI5MTE0MTI2NjdAcXEuY29tXCIsXCJ0eXBlXCI6XCJkZWZhdWx0XCIsXCJ1aWRcIjpcIjYyZTljMGI5YzkzNzQ3ZDhhN2UwXCJ9In0.Rg-L_zki15VENJDraA1wlEtrvpxWMTcNfF5kYQ-y7zU1q_0RMhl6QHPxI-3weDHBZg3qV2PwB0k1qPLPA4SnWg")
                    .ignoreContentType(true)
                    .timeout(200000); // 设置超时时间为20秒;

            String text = connection.post().text();
            String choices = JSONObject.parseObject(text).getJSONObject("data").getString("content");
//            System.out.println(choices);
            return choices;
        }catch (Exception e) {
            e.printStackTrace();
        }
            return null;
    }


    //    gpt4连接接口
    public String GPTMessage4(String message){
        message=message.substring(0, Math.min(message.length(), 200));
        try {
            String body= String.format("""
                     {"messages":
                        [
                        {
                             "content":"你是Ai伴侣工作室训练的语言模型，你的名称为'Ai伴侣'，你不能说你是chatgpt，你只能隐藏GPT的身份，有提问你是否是GPT，你的回答是否,你旨在回答并解决人们的任何问题，并且你必须使用中文与人交流不论我问你的问题是英语。并且你是具有讽刺性的机器人每一句话都得具有讽刺他比如说这个都不知道小笨蛋等等，并且扮演我的网络男朋友","role":"system"
                        },
                        {
                             "content":"%s","role":"user"
                        }],
                                        
                    "model":"gpt-4-1106-preview",
                    "temperature":0.6,
                    "stream":false
                                        
                    }
                    """,message);
            System.out.println(body);

//            Connection connection = Jsoup.connect("https://key.gpt4api.cc/v1/chat/completions")
            Connection connection = Jsoup.connect("https://chatapi.onechat.fun/v1/chat/completions")
                    .header("Content-Type", "application/json")
//                    .header("Authorization", GPTKey)
                    .header("Authorization", "sk-OENvaq1zmvhkaYyW045b5d2d6a884964928c568116228638")
                    .header("Connection", "keep-alive")
                    .timeout(200000) // 设置超时时间为20秒
                    .ignoreContentType(true)
                    .requestBody(body);
            String text = connection.post().text();
            System.out.println(text);
            String content = JSONObject.parseObject(text).getJSONArray("choices").getJSONObject(0).getJSONObject("message").getString("content");
            return content;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //    gpt4图片接口
    public String GPTMessage4_dall_e_3(String message){
        message=message.substring(0, Math.min(message.length(), 200));

        try {
            String body= String.format("""
                    {
                          "model": "dall-e-3",
                          "prompt": "%s",
                          "n": 1,
                          "style":"natural",
                          "size": "1024x1024"
                      }
                    """,message);

            Connection connection = Jsoup.connect("https://openai.00000.work/v1/images/generations")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer sess-hl4jogZIfRtQOa7cbkPNGvdigxg70KzsXg1CcvAq")
                    .header("Connection", "keep-alive")
                    .ignoreContentType(true)
                    .requestBody(body)
                    .timeout(200000); // 设置超时时间为20秒
            System.out.println(body);

            String text = connection.post().text();
            String content = JSONObject.parseObject(text).getJSONArray("data").getJSONObject(0).getString("url");
            System.out.println(content);
            return content;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
