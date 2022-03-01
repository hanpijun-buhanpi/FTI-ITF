package v1.fti;

/**
 * 工厂类，根据serialNumber返回对应的FileTransformationImage的实现类
 */
public class FTI_Factory {
    public static FileTransformationImage getInstance(short serialNumber){
        FileTransformationImage fileTransformationImage=null;
        switch(serialNumber){
            case 1:
                fileTransformationImage=new FileTransformationImage1();
        }
        return fileTransformationImage;
    }
}
