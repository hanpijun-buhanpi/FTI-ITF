package v1.fti;

import v1.file.DataFileInput;
import v1.header.EncryptionHeaderData;
import v1.image.DataImageOutput;

/**
 * 将文件转换成图片的方法的接口
 */
public interface FileTransformationImage {
    void startTransformation(EncryptionHeaderData ehd, DataFileInput dfi, DataImageOutput dio) throws Exception;
}
