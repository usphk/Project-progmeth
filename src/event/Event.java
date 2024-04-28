package event;

import Player.*;
import sceen.Wave;

public class Event {
	public static boolean checkHit(Dog dog, Wave wave, int dogSize, int waveHeight) {
		double dogRight = dog.getX() + dogSize; // ตำแหน่งของด้านขวาของหมา
		double waveRight = wave.getX() + 50; // ตำแหน่งของด้านขวาของคลื่น (ความกว้างของคลื่น)

		// ตรวจสอบการชนกันโดยเปรียบเทียบตำแหน่งของด้านขวาของหมาและคลื่น
		if (dogRight >= wave.getX() && dog.getX() <= waveRight) {
			double dogBottom = dog.getY() + dogSize; // ตำแหน่งด้านล่างของหมา
			double waveTop = wave.getY(); // ตำแหน่งด้านบนของคลื่น

			// ตรวจสอบว่าหมาชนกับคลื่นด้านบนของคลื่นหรือไม่
			if (dogBottom >= waveTop - waveHeight) {
				return true; // หมาชนกับคลื่น
			}
		}

		return false; // หมาไม่ชนกับคลื่น
	}
}
