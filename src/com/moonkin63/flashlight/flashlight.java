package com.moonkin63.flashlight;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class flashlight extends Activity {
    /**
     * when status = false then flashlight is turned off
     */
    private boolean status = false;
    private Camera cam;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final Button bt = (Button) findViewById(R.id.buttonTurnOn);
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (status) {
                    bt.setText(R.string.turn_on);
                    Camera.Parameters p = cam.getParameters();
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    cam.setParameters(p);
                    cam.stopPreview();
                    cam.release();
                } else {
                    cam = Camera.open();
                    bt.setText(R.string.turn_off);
                    Camera.Parameters p = cam.getParameters();
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    cam.setParameters(p);
                    cam.startPreview();
                }
                updateScreenState();
                status = !status;
            }
        });
    }

    private void updateScreenState() {
        if (status) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cam.release();
    }
}
