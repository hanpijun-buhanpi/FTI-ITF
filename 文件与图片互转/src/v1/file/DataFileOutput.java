package v1.file;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

/**
 * 输出文件类
 */
public class DataFileOutput {
    public static final int MAX_DATA_LENGTH=2147210244;
    private BufferedOutputStream bufferedOutputStream;

    /**
     * 构造方法，通过文件路径创建输出流
     * @param filePath 文件路径
     * @throws Exception 创建流错误
     */
    public DataFileOutput(String filePath) throws Exception {
        bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(filePath));
    }

    /**
     * 将传递进来的数据写入硬盘，并关闭流
     * @param bytes 需要写入硬盘的数据
     * @throws Exception 写入、关闭流错误
     */
    public void write(byte[] bytes) throws Exception {
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }
}
