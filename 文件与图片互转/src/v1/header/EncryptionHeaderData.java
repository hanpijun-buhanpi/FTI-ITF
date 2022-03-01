package v1.header;

import java.util.Arrays;

/**
 * 组装信息头
 */
public class EncryptionHeaderData {
    private String suffix;
    private int dataLength;
    private short serialNumber;
    private byte[] encryptionNumber;
    private byte[] headerData=new byte[27];

    /**
     * 将传递进来的参数的数据组合成byte数组
     * @param suffix 后缀名
     * @param dataLength 存储的数据长度
     * @param serialNumber 加密方式的序号
     * @param encryptionNumber 加密用的特殊数字
     */
    public EncryptionHeaderData(String suffix, int dataLength, short serialNumber, byte[] encryptionNumber) {
        this.suffix = suffix;
        this.dataLength = dataLength;
        this.serialNumber = serialNumber;
        this.encryptionNumber = encryptionNumber;
        encryptionHeaderData();
    }

    /**
     * 将各信息转换成byte保存
     */
    private void encryptionHeaderData(){
        char[] chars=suffix.toCharArray();
        for (int i=0;i<9;i++){
            if (i<chars.length){
                headerData[i*2]=(byte) ((chars[i] & 0xff00) >> 8);
                headerData[i*2+1]=(byte) (chars[i] & 0xff);
            } else {
                headerData[i*2]=(byte) ((' ' & 0xff00) >> 8);
                headerData[i*2+1]=(byte) (' ' & 0xff);
            }
        }
        headerData[18]=(byte) ((dataLength & 0xff000000) >> 24);
        headerData[19]=(byte) ((dataLength & 0xff0000) >> 16);
        headerData[20]=(byte) ((dataLength & 0xff00) >> 8);
        headerData[21]=(byte) (dataLength & 0xff);
        headerData[22]=(byte) ((serialNumber & 0xff00) >> 8);
        headerData[23]=(byte) (serialNumber & 0xff);
        headerData[24]=encryptionNumber[0];
        headerData[25]=encryptionNumber[1];
        headerData[26]=encryptionNumber[2];
    }

    /**
     * 将制作好的信息头返回
     * @return 信息头
     */
    public byte[] getHeaderData(){
        return headerData;
    }

    @Override
    public String toString() {
        return "EncryptionHeaderData{" +
                "suffix='" + suffix + '\'' +
                ", dataLength=" + dataLength +
                ", serialNumber=" + serialNumber +
                ", encryptionNumber=" + Arrays.toString(encryptionNumber) +
                '}';
    }
}
