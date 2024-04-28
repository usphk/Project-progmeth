package event;

import Player.Dog;
import display.Game;
import sceen.Wave;

public class Event {
	public static boolean checkHit(Dog dog, Wave wave, int dogSize, int waveHeight) {
		if (dog.getX() + dogSize > wave.getX() && dog.getX() < wave.getX() + wave.getWaveSize()) {
			if (dog.getY() + dogSize >= wave.getY() - waveHeight) {
				return true;
			}
		}
		return false;
	}
}
