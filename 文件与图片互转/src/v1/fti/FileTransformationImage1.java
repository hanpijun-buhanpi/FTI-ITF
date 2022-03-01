package v1.fti;

import v1.file.DataFileInput;
import v1.header.EncryptionHeaderData;
import v1.image.DataImage;
import v1.image.DataImageOutput;

/**
 * FileTransformationImage的实现类
 */
public class FileTransformationImage1 implements FileTransformationImage {
    @Override
    public void startTransformation(EncryptionHeaderData ehd, DataFileInput dfi, DataImageOutput dio) throws Exception {
        byte[] fileData=dfi.getFileData();//获取文件数据
        //组装信息头与文件数据
        byte[] trueData=new byte[27+fileData.length];
        System.arraycopy(ehd.getHeaderData(),0,trueData,0,27);
        System.arraycopy(fileData,0,trueData,27,fileData.length);
        //判断数据离填满图片还剩多少，并使用随机数填充余下的数据
        byte[] rgbData;
        int rgbLength=dio.getWight()*dio.getHeight()*3;
        if (trueData.length==rgbLength){
            rgbData=trueData;
        } else {
            rgbData=new byte[rgbLength];
            System.arraycopy(trueData,0,rgbData,0,trueData.length);
            byte[] temp=new byte[rgbLength-trueData.length];
            for (int i=0;i<temp.length;i++){
                temp[i]= DataImage.getRandomByteRGB();
            }
            System.arraycopy(temp,0,rgbData,trueData.length,temp.length);
        }
        //设置图片的像素值并写入到硬盘
        dio.setAllByteRGB(rgbData);
        dio.write();
    }
}
