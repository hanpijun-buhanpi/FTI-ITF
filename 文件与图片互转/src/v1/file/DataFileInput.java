package v1.file;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

/**
 * 输入文件类
 */
public class DataFileInput {
    public static final int MAX_DATA_LENGTH=2147210244;
    private BufferedInputStream bufferedInputStream;
    private byte[] fileData=null;

    /**
     * 通过文件路径创建输入流
     * @param filePath 文件路径
     * @throws Exception 创建流错误
     */
    public DataFileInput(String filePath) throws Exception {
        bufferedInputStream=new BufferedInputStream(new FileInputStream(filePath));
        read();
    }

    /**
     * 读取数据，并判断是否超出最大容量
     * @throws Exception 读取错误
     */
    private void read() throws Exception {
        byte[] bytes=new byte[MAX_DATA_LENGTH-27];
        int len=0;
        len=bufferedInputStream.read(bytes);
        if (bufferedInputStream.read()==-1) {
            fileData = new byte[len];
            System.arraycopy(bytes, 0, fileData, 0, len);
        }
        bufferedInputStream.close();
    }

    /**
     * 获取保存的数据
     * @return 文件数据
     */
    public byte[] getFileData(){
        return fileData;
    }
}
