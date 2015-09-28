import java.io.IOException;
import java.util.Properties;

public class PropertyTc {
	private static Properties pros = new Properties();
	static {
		try {
			pros.load(PropertyTc.class.getClassLoader().getResourceAsStream(
					"config/tank.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private PropertyTc(){}
	
	public static int getProperty(String key) {
		return Integer.parseInt(pros.getProperty(key));
	}
}
