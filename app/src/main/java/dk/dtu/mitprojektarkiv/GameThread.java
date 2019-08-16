package dk.dtu.mitprojektarkiv;

import android.graphics.Canvas;

public class GameThread extends Thread {

    private GameView gameView;
    private boolean running = false;

    public GameThread(GameView gameView){
        this.gameView = gameView;
    }

    public void setRunning(boolean run){
        running = run;
    }

    public void run(){
        while (running){
            Canvas canvas = null;
            try {
                canvas = gameView.getHolder().lockCanvas();
                synchronized (gameView.getHolder()) {
                    gameView.draw(canvas);
                    gameView.OnDraw(canvas);
                }
            } finally {
                if ( canvas != null){
                    gameView.getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }
    }


}
