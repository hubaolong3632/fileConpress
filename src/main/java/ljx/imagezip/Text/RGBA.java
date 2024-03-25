package ljx.imagezip.Text;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RGBA {
        public static void main(String[] args) {
            try {
                // 读取jpg图片
                BufferedImage img = ImageIO.read(new File("C:\\Users\\hubao\\Pictures\\AI绘图\\image\\tp3.png"));

                // 创建一个新的RGBA格式的BufferedImage
                BufferedImage rgbaImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
                rgbaImage.getGraphics().drawImage(img, 0, 0, null);

                // 将RGBA格式的图片写入新文件
                System.out.println(rgbaImage);
                    ImageIO.write(rgbaImage, "png", new File("C:\\Users\\hubao\\Pictures\\AI绘图\\image\\output.png"));

                System.out.println("转换成功");
            } catch (IOException e) {
                System.out.println("转换失败: " + e);
            }
        }
}
