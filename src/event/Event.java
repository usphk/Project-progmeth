package event;

import Player.*;
import sceen.Environment;
import sceen.Wave;

public class Event {
			public static boolean checkHit(Dog dog, Wave wave, int dogSize, int waveHeight){
							if(dog.getX() +dogSize> wave.getX() && dog.getX() < wave.getX()) {
								if(dog.getY() +dogSize>= wave.getY() -waveHeight) {
									return true;
								}
							}
							return false;
			}

			public static void gameStop(Wave[] wave, Environment[] env) {

			}

}
