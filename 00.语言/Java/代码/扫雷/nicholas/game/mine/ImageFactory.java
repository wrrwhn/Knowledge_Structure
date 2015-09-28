//图片集合
 package nicholas.game.mine;

import javax.swing.ImageIcon;

public class ImageFactory {

    private static ImageFactory imagefactory;
    private static ImageIcon images[];

    //将图像导入其中    
    private ImageFactory()
    {
        images = new ImageIcon[21];
        images[0] = new ImageIcon(getClass().getResource("image/0.gif"));
        images[1] = new ImageIcon(getClass().getResource("image/1.gif"));
        images[2] = new ImageIcon(getClass().getResource("image/2.gif"));
        images[3] = new ImageIcon(getClass().getResource("image/3.gif"));
        images[4] = new ImageIcon(getClass().getResource("image/4.gif"));
        images[5] = new ImageIcon(getClass().getResource("image/5.gif"));
        images[6] = new ImageIcon(getClass().getResource("image/6.gif"));
        images[7] = new ImageIcon(getClass().getResource("image/7.gif"));
        images[8] = new ImageIcon(getClass().getResource("image/8.gif"));
        images[9] = new ImageIcon(getClass().getResource("image/normal.gif"));
        images[10] = new ImageIcon(getClass().getResource("image/flag.gif"));
        images[11] = new ImageIcon(getClass().getResource("image/mine.gif"));
        images[12] = new ImageIcon(getClass().getResource("image/onmine.gif"));
        images[13] = new ImageIcon(getClass().getResource("image/question.gif"));
        images[14] = new ImageIcon(getClass().getResource("image/topbar.gif"));
        images[15] = new ImageIcon(getClass().getResource("image/wrong.gif"));
        images[16] = new ImageIcon(getClass().getResource("image/mineico.gif"));
        images[17] = new ImageIcon(getClass().getResource("image/qq1.gif"));
        images[18] = new ImageIcon(getClass().getResource("image/qq2.gif"));
        images[19] = new ImageIcon(getClass().getResource("image/qq3.gif"));
        images[20] = new ImageIcon(getClass().getResource("image/qq4.gif"));
    }

    //获取对应的图像
    public ImageIcon getImageicon(int i)
    {
        return images[i];
    }

    public static synchronized ImageFactory getInstance()
    {
        if(imagefactory != null)
        {
            return imagefactory;
        } else {
            imagefactory = new ImageFactory();
            return imagefactory;
        }
    }
}
