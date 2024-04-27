package Player;

import base.BaseCharactor;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.util.Duration;
import usage.Healable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Armor extends BaseCharactor implements Healable {

    public Armor(int x, int y) {
        super(70,50);
        this.x = x;
        this.y = y;
        try {
            this.image = new Image(new FileInputStream("img/dog.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void skill(Canvas canvas) {
        this.y -= getSpeed();
        draw(canvas.getGraphicsContext2D(), 70); // ส่งขนาดเข้าไปในเมธอด draw
        // Fall
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(450), event -> {
                    y += getSpeed();
                    draw(canvas.getGraphicsContext2D(), 70); // ส่งขนาดเข้าไปในเมธอด draw
                })
        );
        timeline.play();
    }

    @Override
    public void addHealPoint(int Hp) {
        setHealth(Hp + 50);
    }
}
