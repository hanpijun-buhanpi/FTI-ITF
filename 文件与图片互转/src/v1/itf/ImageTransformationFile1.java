package v1.itf;

import v1.file.DataFileOutput;
import v1.header.DecryptHeaderData;
import v1.image.DataImageInput;

/**
 * ImageTransformationFile的实现类
 */
public class ImageTransformationFile1 implements ImageTransformationFile {
    @Override
    public void startTransformation(DecryptHeaderData dhd, DataImageInput dii, DataFileOutput dfo) throws Exception {
        byte[] data=new byte[dhd.getDataLength()];//创建一个等同数据长度大小的byte数组
        System.arraycopy(dii.getAllByteRGB(),27,data,0,dhd.getDataLength());//读取图片数据并将有效数据复制给data
        dfo.write(data);//将data传递给dfo写进磁盘
    }
}
