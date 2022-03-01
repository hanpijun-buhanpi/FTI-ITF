package v1;

import v1.file.DataFileInput;
import v1.file.DataFileOutput;
import v1.fti.FTI_Factory;
import v1.fti.FileTransformationImage;
import v1.header.DecryptHeaderData;
import v1.header.EncryptionHeaderData;
import v1.image.DataImageInput;
import v1.image.DataImageOutput;
import v1.itf.ITF_Factory;
import v1.itf.ImageTransformationFile;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        new Main().mainRun();
    }

    /**
     * 文件转图片的运行过程
     * @param filePath 文件路径
     * @param imagePath 图片路径
     * @param serialNumber 转换方式序号
     * @param encryptionNumber 转换用数字
     * @throws Exception 转换错误
     */
    public void fti(String filePath,String imagePath,short serialNumber,byte[] encryptionNumber) throws Exception {
        DataFileInput dfi=new DataFileInput(filePath);//创建文件流
        DataImageOutput dio;//创建图像流
        //获取文件的数据长度，计设置图像的宽高
        int dataLength=dfi.getFileData().length+27;
        double wh=Math.sqrt(dataLength/3.0);
        if (wh==(int) wh){
            dio=new DataImageOutput((int) wh,(int) wh,imagePath);
        } else {
            dio=new DataImageOutput(((int) wh)+1,((int) wh) +1,imagePath);
        }
        //制作信息头
        String suffix=filePath.substring(filePath.lastIndexOf(".")+1);
        EncryptionHeaderData ehd=new EncryptionHeaderData(suffix,dfi.getFileData().length,serialNumber,encryptionNumber);
        FileTransformationImage fti= FTI_Factory.getInstance(serialNumber);
        fti.startTransformation(ehd,dfi,dio);
    }

    /**
     * 图片转文件的运行过程
     * @param imagePath 图片路径
     * @param filePath 文件路径
     * @throws Exception 转换错误
     */
    public void itf(String imagePath,String filePath) throws Exception {
        DataImageInput dii=new DataImageInput(imagePath);
        byte[] headerData=new byte[27];
        System.arraycopy(dii.getAllByteRGB(),0,headerData,0,27);
        DecryptHeaderData dhd=new DecryptHeaderData(headerData);
        ImageTransformationFile itf= ITF_Factory.getInstance(dhd.getSerialNumber());
        DataFileOutput dfo=new DataFileOutput(filePath+"."+dhd.getSuffix());
        itf.startTransformation(dhd,dii,dfo);
    }

    /**
     * 用户交互部分
     */
    public void mainRun(){
        //创建Scanner类，打印提示语
        Scanner scanner=new Scanner(System.in);
        System.out.println("欢迎使用文件与bmp图片互转系统1.0版本");
        System.out.println("目前最多支持1.9GB的文件互转，且由于代码原因，对于内存的使用很大，实际上可能会低于1.9GB");
        System.out.println("且目前仅支持bmp图片的转换操作，因为其他格式读出来的数据会发生变化，暂不知道原理");
        System.out.println("下面请根据提示进行操作");
        try{
            String filePath="";
            String imagePath="";
            boolean result=true;
            System.out.println("请输入需要进行的操作：fti：文件转图片；itf：图片转文件");
            String temp="";
            if (scanner.hasNext())
                temp=scanner.nextLine();
            if (temp.equals("fti")){
                System.out.println("请输入文件路径（带后缀）：");
                if (scanner.hasNext())
                    filePath=scanner.nextLine();
                System.out.println("请输入图片路径（不带后缀）：");
                if (scanner.hasNext())
                    imagePath=scanner.nextLine();
                short serialNumber=0;
                System.out.println("请输入转换方式（目前只有1），1：顺序注入；");
                if (scanner.hasNext())
                    serialNumber=Short.parseShort(scanner.nextLine());
                if (serialNumber>0 && serialNumber<2){
                    switch (serialNumber){
                        case 1:
                            fti(filePath,imagePath,serialNumber,new byte[]{0,0,0});
                    }
                } else {
                    result=false;
                }
            } else if (temp.equals("itf")){
                System.out.println("请输入文件路径（不带后缀）：");
                if (scanner.hasNext())
                    filePath=scanner.nextLine();
                System.out.println("请输入图片路径（不带后缀）：");
                if (scanner.hasNext())
                    imagePath=scanner.nextLine();
                itf(imagePath,filePath);
                result=true;
            } else
                result=false;
            if (result)
                System.out.println("转化完成，程序结束");
            else
                System.out.println("未知的操作，退出程序");
        } catch (Exception e){
            System.out.println("程序运行出错，错误原因如下：");
            System.out.println(e.getMessage());
            System.out.println("程序退出");
        }
    }
}
