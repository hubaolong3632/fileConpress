package ljx.imagezip.Text;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;

public class ImageCompressUtil {
    String folderName="image"; //文件夹名称
    String filePath="";// 用户提交的文件路径
//    float scale = 0.2f; //压缩比
    float scale = 1f; //压缩比

    public static void main(String[] args) throws IOException {
        ImageCompressUtil ig=new ImageCompressUtil();
//         String path = "C:\\Users\\hubao\\Pictures\\AI绘图\\";
         String path = "C:\\Users\\hubao\\Pictures\\cc"
                 +"\\";
        ig.init(path);

    }


//    初始化
    public void init(String path){
        filePath=path; //拿到当前位置
        File f1 = new File(path+"\\"+folderName); //创建一个用于保存文件的文件夹
        if (f1.mkdir()) {
            System.out.println("创建路径成功");
        }
        fileConvenience(path); //便利当前文件夹

    }

//    遍历文件
    public void fileConvenience(String path){
        File pathFile = new File(path); //查找文件

//        拿到文件下的路径
        String[] list =pathFile.list();
        for (String fileName : list) {
            String[] split = fileName.split("\\.");
            if(split.length>1){ //如果长度大于2相当于必须得是文件
                String type = split[1];
                if(type.equals("png")||type.equals("gif")||type.equals("jpg")){ //如果是这些文件那么就进行便利

                    image(path,fileName,type);
                    System.out.println("创建成功::"+fileName);
                    System.out.println(path+fileName+"\n");

//                    System.out.println(path+fileName);
                }
            }else{ //如果不是文件的话那基本上可以是文件夹了
                if(fileName.equals(folderName))  continue;//如果为我生成的文件夹就不管他
                String nextFile = path + fileName; //下一个文件路径
                File f2 = new File(nextFile); //查找文件
                if(f2.isFile()) continue; //如果发现不是文件夹也跳过此文件
//                System.out.println("------------");
//                System.out.println(nextFile);
//                System.out.println("------------");


                fileConvenience(nextFile+"\\"); //执行递归查找文件



            }
        }


    }

 int i=0;
// float scale = 1.0f; //压缩比

    // 对图片进行压缩图片路径+文件名称
    public String image(String path, String fileName, String type){

        try {
            String pathFileName = path + fileName;

            File imageFile = new File(pathFileName);
            BufferedImage inputImage = ImageIO.read(imageFile);

            //通过比例压缩
//        float scale = 0.5f;
            BufferedImage outputImage = compress(inputImage, scale);

            String imageType = getImageType(inputImage.getType());//返回图片类型
        //图片输出路径，以及图片名
//            File outputFile = new File( filePath+"\\"+folderName+"\\"+ fileName );
            File outputFile = new File( filePath+"\\"+folderName+"\\"+ "c"+(i++)+"."+type );
//            File outputFile = new File(path +"\\image\\"+ fileName + "." + imageType);
            ImageIO.write(outputImage, imageType, outputFile);
            return outputFile.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "null";
    }


    /**
     * 压缩图片
     */
    public static BufferedImage compress(BufferedImage inputImage, float scale) {
        //压缩之后的长度和宽度
        int outputHeight = (int)(inputImage.getHeight()*scale);
        int outputWidth = (int)(inputImage.getWidth()*scale);

        BufferedImage outputImage = new BufferedImage(outputWidth, outputHeight, inputImage.getType());

        outputImage.getGraphics().drawImage(
                inputImage.getScaledInstance(outputWidth, outputHeight, Image.SCALE_SMOOTH), 0, 0, null);

        return outputImage;
    }

    public static String getImageType(int imageType){
        switch (imageType){
//            case 5:
//                return "jpg";
            case 6:
                return "png";
            case 13:
                return "gif";
            default:
                return "jpg";
        }
    }

}

