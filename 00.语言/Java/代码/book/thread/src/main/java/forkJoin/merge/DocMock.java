package forkJoin.merge;

import java.util.Random;

/**
 * Created by Yao on 2015/11/25.
 */
public class DocMock {

    private String words[]= {"hello", "world", "java", "oracle", "google", "android", "go", "c", "c++"};

    public String [][] GeneDoc(int iRows, int iCells, String keyWord) {

        int couter = 0;
        String word;
        String doc[][] = new String[iRows][iCells];
        Random random = new Random();

        for (int i = 0; i < iRows; i++) {
            for (int j = 0; j < iCells; j++) {
                word= words[random.nextInt(words.length)];
                doc[i][j]= word;
                couter+= (word.equals(keyWord)? 1: 0);
            }
        }
        System.out.printf("DocMock.GeneDoc: keyWord is %s and the count is %d\n", keyWord, couter);

        return doc;
    }
}
