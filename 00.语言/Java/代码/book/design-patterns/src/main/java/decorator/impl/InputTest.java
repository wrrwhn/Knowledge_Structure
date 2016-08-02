package decorator.impl;

import java.io.*;

/**
 * Created by Yao on 2015/2/13.
 */
public class InputTest {

    public static void main(String[] args) throws Exception{

        int c;

        File file= new File("c:\\inputTest.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        fos.write("aA1!".getBytes());
        fos.flush();
        fos.close();

        InputStream is= new LowerCaseInputStream(new BufferedInputStream(new FileInputStream("c:\\inputTest.txt")));
        while((c= is.read())>= 0){
            System.out.print((char)c);
        }
        is.close();
    }
}
