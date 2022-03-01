package v1.image;

public class DataImageInput extends DataImage {

    /**
     * 通过传递进来的参数调用父类的构造方法，创建BufferedImage对象
     * @param imagePath 图片路径
     * @throws Exception 图片读取错误
     */
    public DataImageInput(String imagePath) throws Exception {
        super(imagePath+".bmp");
    }

    /**
     * 读取第y行，第x列的像素的rgb的数值，以byte类型返回
     * @param x 第几列
     * @param y 第几行
     * @return 包含rgb信息的byte类型数据
     */
    public byte[] getByteRGB(int x,int y){
        return intRGB_ByteRGB(image.getRGB(x,y));
    }

    /**
     * 读取整张图片的rgb信息，并将其排列好返回
     * @return 包含整张图片的rgb信息的byte数组
     */
    public byte[] getAllByteRGB(){
        int[] rgbs=new int[image.getWidth()*image.getHeight()];
        image.getRGB(0,0,image.getWidth(),image.getHeight(),rgbs,0,image.getWidth());
        byte[] result=new byte[rgbs.length*3];
        for (int i=0;i<rgbs.length;i++){
            byte[] temp=intRGB_ByteRGB(rgbs[i]);
            result[i*3]=temp[0];
            result[i*3+1]=temp[1];
            result[i*3+2]=temp[2];
        }
        return result;
    }
}
