package GameMechanics;

//Omri Cohen 318673407

import Interfaces.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type Level background.
 */
public class BackgroundSetter implements Sprite {
    //fields
    private Color color;
    private String levelName;

    /**
     * Instantiates a new Level background.
     *
     * @param color  the color
     * @param string the level Name
     */
    public BackgroundSetter(Color color, String string) {
        this.color = color;
        this.levelName = string;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        switch (levelName) {
            case "Direct Hit":
                d.setColor(Color.black);
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(Color.blue);
                d.drawCircle(400, 220, 55);
                d.drawCircle(400, 220, 80);
                d.drawCircle(400, 220, 105);

                d.drawLine(400, 340, 399, 260);
                d.drawLine(400, 180, 399, 100);
                d.drawLine(280, 217, 370, 217);
                d.drawLine(430, 217, 520, 217);

                break;
            case "Wide Easy":
                Color sky = new Color(51, 153, 255);
                d.setColor(sky);
                d.fillRectangle(0, 0, 800, 600);
                //left rays
                d.setColor(Color.YELLOW);
                for (int i = 1; i < 15; i++) {
                    d.drawLine(150, 150, 200 - 10 * i, 275);
                }
                //right rays
                for (int i = 1; i < 20; i++) {
                    d.drawLine(150, 150, 200 + 20 * i, 275);
                }
                for (int i = 1; i < 20; i++) {
                    d.drawLine(150, 150, 400 + 20 * i, 275);
                }
                d.drawLine(150, 150, 200, 275);
                //setting the sun
                Color orange = new Color(255, 153, 51);
                d.setColor(orange);
                d.fillCircle(150, 150, 75);
                d.setColor(Color.YELLOW);
                d.fillCircle(150, 150, 65);
                //making a rainbow
                d.setColor(Color.red);
                d.fillCircle(300, 515, 115);
                d.setColor(Color.orange);
                d.fillCircle(300, 515, 100);
                d.setColor(Color.yellow);
                d.fillCircle(300, 515, 85);
                d.setColor(Color.green);
                d.fillCircle(300, 515, 70);
                d.setColor(Color.blue);
                d.fillCircle(300, 515, 55);
                d.setColor(sky);
                d.fillCircle(300, 515, 40);
                Color grass = new Color(0, 204, 0);
                d.setColor(grass);
                d.fillRectangle(0, 500, 800, 600);
                break;
            case "Green 3":
                Color green = new Color(0, 105, 0);
                d.setColor(green);
                d.fillRectangle(0, 0, 800, 600);

                d.setColor(Color.BLACK);
                d.fillRectangle(50, 250, 100, 600);

                d.setColor(Color.lightGray);
                for (int row = 0; row < 40; row++) {
                    for (int col = 0; col < 14; col++) {
                        int x1 = 52 + col * 6;
                        int y1 = 252 + row * 9;
                        int x2 = x1 + 5;
                        int y2 = y1 + 7;
                        d.fillRectangle(x1, y1, 5, 7);
                    }
                }
                d.setColor(Color.darkGray);
                d.fillRectangle(90, 175, 20, 75);
                d.setColor(Color.gray);
                d.fillRectangle(95, 125, 10, 50);
                //setting the antenna
                d.setColor(Color.orange);
                d.fillCircle(100, 115, 10);
                d.setColor(Color.red);
                d.fillCircle(100, 115, 5);
                d.setColor(Color.black);
                d.drawRectangle(90, 175, 20, 75);
                d.drawRectangle(95, 125, 10, 50);
                d.drawCircle(100, 115, 10);
                break;
            default:
                break;
        }
    }

    @Override
    public void timePassed() {
    }
}
