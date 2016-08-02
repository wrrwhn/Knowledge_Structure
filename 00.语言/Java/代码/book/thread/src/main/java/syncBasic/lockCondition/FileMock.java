package syncBasic.lockCondition;

/**
 * Created by Yao on 2015/8/26.
 */
public class FileMock {

    private String content[];
    private int index;

    public FileMock(int size, int length) {
        content= new String[size];

        for (int i = 0; i < size; i++) {
            StringBuffer buffer= new StringBuffer(length);
            for (int j = 0; j < length; j++) {
                buffer.append((char)(Math.random()* 255));
            }
            content[i]= buffer.toString();
        }
        index= 0;
    }

    public boolean hasMoreLines(){
        return index< content.length;
    }

    public String getLine(){
        if(this.hasMoreLines()){
            System.out.printf("Mock - %d\n", content.length- index);
            return content[index++];
        }else{
            return null;
        }
    }
}
