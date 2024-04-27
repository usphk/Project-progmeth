package base;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Duration;

public abstract class BaseCharactor {
    protected int x;
    protected int y;
    protected Image image;
    private int health;
    private int speed;

    public BaseCharactor(int health, int speed){
        this.health = health;
        this.speed = speed;
    }
    public abstract void skill(Canvas canvas);
    protected void draw(GraphicsContext gc, double size) {
        gc.clearRect(1, 1, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        gc.drawImage(image, x, y, size, size); // ใช้ขนาดที่ส่งเข้ามาในการวาดรูปภาพ
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getHealth(){
        return this.health;
    }
    public int getSpeed() {
        return this.speed;
    }
    public Image getImage() {
        return image;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
}
