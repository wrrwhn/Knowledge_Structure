package decorator.impl;

import java.io.*;

/**
 * Created by Yao on 2015/2/13.
 */
public class LowerCaseInputStream extends BufferedInputStream {
    public LowerCaseInputStream(InputStream is) throws FileNotFoundException {
        super(is);
    }


    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int result= super.read(b, off, len);
        for(int i= off; i< off+ result; i++){
            b[i]= (byte) Character.toLowerCase(b[i]);
        }
        return result;
    }

    @Override
    public int read() throws IOException {
        int c= super.read();
        return (-1== c? c: Character.toLowerCase(c));
    }
}
