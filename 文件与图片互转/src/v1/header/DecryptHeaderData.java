package v1.header;

import java.util.Arrays;

/**
 * 拆解信息头
 */
public class DecryptHeaderData {
    private String suffix;
    private int dataLength;
    private short serialNumber;
    private byte[] encryptionNumber;
    private byte[] headerData;

    /**
     * 解密信息头
     * @param headerData 信息头
     */
    public DecryptHeaderData(byte[] headerData){
        this.headerData=headerData;
        decryptHeaderData();
    }

    /**
     * 从信息头获取数据并保存
     */
    private void decryptHeaderData(){
        char[] chars=new char[9];
        for (int i=0;i<9;i++){
            chars[i]=(char) (((headerData[i*2] & 0xff) << 8) + (headerData[i*2+1] & 0xff));
        }
        StringBuffer sb=new StringBuffer();
        for (char c:chars){
            if (c!=' ')
                sb.append(c);
            else
                break;
        }
        suffix=sb.toString();
        dataLength=(((headerData[18] & 0xff) << 24) + ((headerData[19] & 0xff) <<16) + ((headerData[20] & 0xff) << 8) + (headerData[21] & 0xff));
        serialNumber=(short) (((headerData[22] & 0xff) << 8) + (headerData[23] & 0xff));
        encryptionNumber=new byte[]{headerData[24],headerData[25],headerData[26]};
    }

    /**
     * get方法
     * @return 成员变量
     */
    public String getSuffix() {
        return suffix;
    }

    public int getDataLength() {
        return dataLength;
    }

    public short getSerialNumber() {
        return serialNumber;
    }

    public byte[] getEncryptionNumber() {
        return encryptionNumber;
    }

    @Override
    public String toString() {
        return "DecryptHeaderData{" +
                "suffix='" + suffix + '\'' +
                ", dataLength=" + dataLength +
                ", serialNumber=" + serialNumber +
                ", encryptionNumber=" + Arrays.toString(encryptionNumber) +
                '}';
    }
}
