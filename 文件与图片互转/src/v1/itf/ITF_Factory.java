package v1.itf;

/**
 * 工厂类，根据serialNumber返回对应的ImageTransformationFile的实现类
 */
public class ITF_Factory {
    public static ImageTransformationFile getInstance(short serialNumber){
        ImageTransformationFile imageTransformationFile=null;
        switch(serialNumber){
            case 1:
                imageTransformationFile=new ImageTransformationFile1();
        }
        return imageTransformationFile;
    }
}
