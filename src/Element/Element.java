package Element;

import javafx.scene.image.Image;
import javafx.scene.text.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Element {

	public static Font getFont(int size) {
		try {
			return Font.loadFont(new FileInputStream(new File("font/Mali-Bold.ttf")), size);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return Font.getDefault(); //fontเริ่มต้นถ้าหาไม่เจอ
		}
	}

	public static Image createPointImage(long point) {
		String imagePath = "img/point_" + point + ".png";
		try {
			return new Image(new FileInputStream(imagePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null; // ถ้าไม่มีภาพให้ null
		}
	}
}
