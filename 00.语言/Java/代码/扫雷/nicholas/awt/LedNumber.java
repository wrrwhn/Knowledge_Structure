//LED���ֵ�ʵ��

package nicholas.awt;

import java.awt.*;
import java.awt.image.*;

	//component ��һ������ͼ�α�ʾ�����Ķ��󣬿�����Ļ����ʾ���������û����н�����
public class LedNumber extends Component {

	// Polygon ���װ������ռ��з�յĶ�ά���������
	// Polygon(int[] xpoints, int[] ypoints, int npoints) x��������� y��������� �������
	private Polygon segmentPolygon[];

	private int numberSegment[][] = { { 0, 1, 2, 3, 4, 5 }, // 0
			{ 1, 2 }, // 1
			{ 0, 1, 3, 4, 6 }, // 2
			{ 0, 1, 2, 3, 6 }, // 3
			{ 1, 2, 5, 6 }, // 4
			{ 0, 2, 3, 5, 6 }, // 5
			{ 0, 2, 3, 4, 5, 6 }, // 6
			{ 0, 1, 2 }, // 7
			{ 0, 1, 2, 3, 4, 5, 6 }, // 8
			{ 0, 1, 2, 3, 5, 6 } // 9
	};

	private int div[] = { 1, 10, 100, 1000, 10000, 100000 };

	private Image numberImage[];

	private Color fontColor = Color.red; // the color of number

	private Color bgColor = Color.black;

	private Color maskColor = Color.darkGray;

	private int dWidth = 12;

	private int dHeight = 21;

	public LedNumber() {

		init();
	}

	//��ʼ��ǰ��ɫ
	public LedNumber(Color fc) {

		fontColor = fc;
		init();
	}
	
	//��ʼ��ǰ��ɫ�ͱ���ɫ
	public LedNumber(Color fc, Color bgc) {

		bgColor = bgc;
		fontColor = fc;
		init();
	}

	//
	public LedNumber(Color fc, Color bgc, Color mc) {

		bgColor = bgc;
		fontColor = fc;
		maskColor = mc;
		init();
	}

	
	public Image getLedImage(int dg, int bound) {
		dg %= div[bound];
		//���� 8 λ RGB ��ɫ������ͼ��
		Image image = new BufferedImage(dWidth * bound, dHeight,BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		bound--;
		for (int i = bound; i >= 0; i--) {
			g.drawImage(numberImage[dg / div[i]], (bound - i) * dWidth,	0, this);
			dg %= div[i];
		}
		return image;
	}

	public void init() {
		segmentPolygon = new Polygon[7];
		numberImage = new Image[10];
		// setup polygons
		setNumberPolygon();
		setNumberImage();
	}

	public void setBackGround(Color bgc) {
		bgColor = bgc;
	}

	public void setFontColor(Color fc) {
		fontColor = fc;
	}

	public void setMaskColor(Color mkc) {
		maskColor = mkc;
	}

	public void setDigitWidth(int w) {
		dWidth = w;
		init();
	}

	//���ֵĸ߶�	
	public void setDigitHeight(int h) {
		dHeight = h;
		init();
	}

	public void setDigitSize(int w, int h) {
		dWidth = w;
		dHeight = h;
		init();
	}

	
	private void setNumberImage() {
		int i = 0;
		int j = 0;
		int k;
		Graphics g;
		while (i < 10) {
			numberImage[i] = new BufferedImage(15, 20, BufferedImage.TYPE_INT_RGB);
			g = numberImage[i].getGraphics();
			g.setColor(bgColor);
			g.fillRect(0, 0, 15, 20);
			//�����䵱������888��led��ʾ
			g.setColor(Color.DARK_GRAY);
			j = 0;
			while (j < numberSegment[8].length) {
				k = numberSegment[8][j];
				g.fillPolygon(segmentPolygon[k]);
				j++;
			}
			
			//���i��Ӧ�����ֵ�led��ʾ
			g.setColor(fontColor);
			j = 0;
			while (j < numberSegment[i].length) {
				k = numberSegment[i][j];
				g.fillPolygon(segmentPolygon[k]);
				j++;
			}
i++;
		}
	}

	public void setNumberPolygon() {
		int mid = dHeight / 2 + 1;
		
		segmentPolygon[0] = new Polygon();
		segmentPolygon[0].addPoint(2, 1);
		segmentPolygon[0].addPoint(dWidth - 2, 1);
		segmentPolygon[0].addPoint(dWidth - 5, 4);
		segmentPolygon[0].addPoint(4, 4);
		
		segmentPolygon[1] = new Polygon();
		segmentPolygon[1].addPoint(dWidth - 1, 1);
		segmentPolygon[1].addPoint(dWidth - 1, mid - 1);
		segmentPolygon[1].addPoint(dWidth - 2, mid - 1);
		segmentPolygon[1].addPoint(dWidth - 4, mid - 3);
		segmentPolygon[1].addPoint(dWidth - 4, 4);
		
		segmentPolygon[2] = new Polygon();
		segmentPolygon[2].addPoint(dWidth - 1, mid);
		segmentPolygon[2].addPoint(dWidth - 1, dHeight - 2);
		segmentPolygon[2].addPoint(dWidth - 4, dHeight - 5);
		segmentPolygon[2].addPoint(dWidth - 4, mid + 1);
		segmentPolygon[2].addPoint(dWidth - 3, mid);
		
		segmentPolygon[3] = new Polygon();
		segmentPolygon[3].addPoint(dWidth - 2, dHeight - 1);
		segmentPolygon[3].addPoint(1, dHeight - 1);
		segmentPolygon[3].addPoint(4, dHeight - 4);
		segmentPolygon[3].addPoint(dWidth - 4, dHeight - 4);
		
		segmentPolygon[4] = new Polygon();
		segmentPolygon[4].addPoint(1, dHeight - 2);
		segmentPolygon[4].addPoint(1, mid);
		segmentPolygon[4].addPoint(3, mid);
		segmentPolygon[4].addPoint(4, mid + 1);
		segmentPolygon[4].addPoint(4, dHeight - 5);
		
		segmentPolygon[5] = new Polygon();
		segmentPolygon[5].addPoint(1, mid - 1);
		segmentPolygon[5].addPoint(1, 1);
		segmentPolygon[5].addPoint(4, 4);
		segmentPolygon[5].addPoint(4, mid - 3);
		segmentPolygon[5].addPoint(2, mid - 1);
		
		segmentPolygon[6] = new Polygon();
		segmentPolygon[6].addPoint(3, mid - 1);
		segmentPolygon[6].addPoint(4, mid - 2);
		segmentPolygon[6].addPoint(dWidth - 4, mid - 2);
		segmentPolygon[6].addPoint(dWidth - 3, mid - 1);
		segmentPolygon[6].addPoint(dWidth - 5, mid + 1);
		segmentPolygon[6].addPoint(4, mid + 1);
	}

}