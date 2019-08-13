package dk.dtu.mitprojektarkiv;

import android.view.KeyEvent;

// KeyInput Constructor for the X and Y Position
public class KeyInput {

    public float xPosition;
    public float yPosition;

    public KeyInput(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public void Keys(int keyCode) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_D: // move right
                this.xPosition += 5;
                System.out.println("PosX Right = " + this.xPosition);
                break;
            case KeyEvent.KEYCODE_S: // move left
                this.yPosition += 5;
                System.out.println("PosY left = " + this.yPosition);
                break;
            case KeyEvent.KEYCODE_A: // move down
                this.xPosition -= 5;
                System.out.println("PosX down = " + this.xPosition);
                break;
            case KeyEvent.KEYCODE_W: // move up
                this.yPosition -= 5;
                System.out.println("PosY up = " + this.yPosition);
                break;
            case KeyEvent.KEYCODE_SPACE: // Stop ball
                this.xPosition = 0;
                this.yPosition = 0;
                System.out.println(" yPosition and xPosition  " + this.xPosition + this.yPosition);
                break;
        }
    }


}
