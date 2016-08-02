package yao.study.java.security;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class FileMD5 {

    public static void main(String[] args) {

        // Get MD5
        digestFileMD5("d:\\desk\\PPT\\MD5.pptx");

        // File Copy
        digestFileMD5("d:\\desk\\PPT\\MD5-Copy.pptx");

        // File Save - change
        digestFileMD5("d:\\desk\\PPT\\MD5-Save-Nothing.pptx");

        // File Change - change
        digestFileMD5("d:\\desk\\PPT\\MD5-Del-Page-1.pptx");
    }

    private static void digestFileMD5(String filePath) {

        String md5Val = null;
        Date start = new Date();

        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            MappedByteBuffer buffer = fis.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(buffer);
            md5Val = new BigInteger(1, md5.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Date end= new Date();
        System.out.printf("File[%-30s].MD5= %s, spend %d milliseconds\n", FilenameUtils.getName(filePath), md5Val, end.getTime()- start.getTime());
    }

}
