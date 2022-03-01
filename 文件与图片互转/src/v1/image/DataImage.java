package v1.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class DataImage {
    protected BufferedImage image;

    /**
     * 构造方法，通过文件路径创建BufferedImage
     * @param imagePath 文件路径
     * @throws Exception 读取图片错误
     */
    protected DataImage(String imagePath) throws Exception {
        image=ImageIO.read(new File(imagePath));
    }

    /**
     * 构造方法，通过宽和高创建BufferedImage
     * @param w 宽
     * @param h 高
     */
    protected DataImage(int w,int h){
        image=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
    }

    //获取图片宽
    public int getWight(){
        return image.getWidth();
    }
    //获取图片高
    public int getHeight(){
        return image.getHeight();
    }

    /**
     * 将int类型的rgb数据转换成byte[]类型的数据
     * @param rgb int类型rgb值
     * @return byte[]类型rgb值
     */
    public static byte[] intRGB_ByteRGB(int rgb){
        return new byte[]{(byte) ((rgb & 0xff0000) >> 16), (byte) ((rgb & 0xff00) >> 8), (byte) (rgb & 0xff)};
    }

    /**
     * 将byte[]类型的rgb数据转换成int类型
     * @param rgb byte[]类型rgb数据
     * @return int类型rgb数据
     */
    public static int byteRGB_IntRGB(byte[] rgb){
        return ((rgb[0] & 0xff) << 16) + ((rgb[1] & 0xff) <<8) + (rgb[2] & 0xff);
    }

    /**
     * 将int类型的rgb数据转换成int[]0~255类型的数据
     * @param rgb int类型的rgb数据
     * @return 0~255范围的int[]类型rgb数据
     */
    public static int[] intRGB_IntArrayRGB(int rgb){
        return new int[]{((rgb & 0xff0000) >> 16), ((rgb & 0xff00) >> 8), (rgb & 0xff)};
    }

    /**
     * 获取随机rgb值
     * @return int类型
     */
    public static int getRandomIntRGB(){
        return ((getRandomByteRGB() & 0xff) << 16) + ((getRandomByteRGB() & 0xff) << 8) + (getRandomByteRGB() & 0xff);
    }

    /**
     * 获取随机rgb值
     * @return 0~255范围的int[]类型
     */
    public static int[] getRandomArrayRGB(){
        return new int[]{getRandomByteRGB() & 0xff,getRandomByteRGB() & 0xff,getRandomByteRGB() & 0xff};
    }

    /**
     * 获取随机rgb值
     * @return byte类型的单个值
     */
    public static byte getRandomByteRGB(){
        return (byte) (new Random().nextInt(255) & 0xff);
    }
}
