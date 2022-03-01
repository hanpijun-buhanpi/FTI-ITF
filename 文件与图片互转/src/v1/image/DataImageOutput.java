package v1.image;

import javax.imageio.ImageIO;
import java.io.File;

/**
 * 负责图片的输出操作
 */
public class DataImageOutput extends DataImage{
    File imageFile;

    /**
     * 根据宽和高创建BufferedImage和保存想要输出到的文件
     * @param w 宽
     * @param h 高
     * @param imagePath 图片路径
     */
    public DataImageOutput(int w,int h,String imagePath){
        super(w,h);
        imageFile=new File(imagePath+".bmp");
    }

    /**
     * 设置单个像素的rgb值
     * @param x 第几列
     * @param y 第几行
     * @param rgb rgb值
     */
    public void setByteRGB(int x,int y,byte[] rgb){
        image.setRGB(x,y,byteRGB_IntRGB(rgb));
    }

    /**
     * 设置整张图片的rgb值
     * @param rgb rgb值
     */
    public void setAllByteRGB(byte[] rgb){
        int rgbDataLength=rgb.length/3;
        int[] rgbArray=new int[rgbDataLength];
        for (int i=0;i<rgbDataLength;i++){
            rgbArray[i]=byteRGB_IntRGB(new byte[]{rgb[i*3],rgb[i*3+1],rgb[i*3+2]});
        }
        image.setRGB(0,0,image.getWidth(),image.getHeight(),rgbArray,0,image.getWidth());
    }

    /**
     * 将设置好的图片写进磁盘
     * @throws Exception 写入错误
     */
    public void write() throws Exception {
        ImageIO.write(image,"BMP",imageFile);
    }
}
