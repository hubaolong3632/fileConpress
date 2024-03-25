package ljx;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class cs {
    public static void main(String[] args) {
        cs cs = new cs();
        String s = cs.GPTMessage4("""
                你好啊//*“”
                “""
                asdqwe 
                sdd
                                
                                
                "asd"
                """);
        System.out.println(s);
    }


    //    gpt4连接接口
    public String GPTMessage4(String message){
        message= message.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n");
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
//            System.out.println(body);

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

}
