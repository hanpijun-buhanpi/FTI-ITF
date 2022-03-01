package v1.itf;

import v1.file.DataFileOutput;
import v1.header.DecryptHeaderData;
import v1.image.DataImageInput;

/**
 * 将图片转换成文件的接口
 */
public interface ImageTransformationFile {
    void startTransformation(DecryptHeaderData dhd, DataImageInput dii, DataFileOutput dfi) throws Exception;
}
