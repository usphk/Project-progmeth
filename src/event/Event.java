package event;

import Player.Dog;
import sceen.Wave;

public class Event {
	public static boolean checkHit(Dog dog, Wave wave, int dogSize, int waveHeight) {
		double dogLeft = dog.getX();
		double dogRight = dog.getX() + dogSize;
		double dogTop = dog.getY();
		double dogBottom = dog.getY() + dogSize;

		double waveLeft = wave.getX();
		double waveRight = wave.getX() + wave.getWaveSize();
		double waveTop = wave.getY();
		double waveBottom = wave.getY() + wave.getWaveSize();

		// ตรวจสอบการชน
		boolean horizontalCollision = dogLeft < waveRight && dogRight > waveLeft;
		boolean verticalCollision = dogBottom > waveTop && dogTop < waveBottom;

		return horizontalCollision && verticalCollision;
	}
}
