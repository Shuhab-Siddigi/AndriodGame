package dk.dtu.mitprojektarkiv;

import android.graphics.Canvas;

public class GameThread extends Thread {

    private GameView gameView;
    private boolean running = false;

    public GameThread(GameView gameView) {
        this.gameView = gameView;
    }

    public void setRunning(boolean run) {
        running = run;
    }

    public void run() {
        while (running) {
            Canvas canvas = null;
            try {
                canvas = gameView.getHolder().lockCanvas();
                synchronized (gameView.getHolder()) {
                    if (canvas != null) {
                        gameView.draw(canvas); // Ticks
                      //  gameView.OnDraw(canvas); // Ticks
                        gameView.update(canvas);
                    }
                }
            } finally {
                if (canvas != null) {
                    gameView.getHolder().unlockCanvasAndPost(canvas);
                }
            }
        }
    }


}
