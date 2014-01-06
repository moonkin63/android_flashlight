package com.moonkin63.flashlight;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class flashlight extends Activity {
    private boolean status = false;
    private Camera cam = Camera.open();

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
                } else {
                    bt.setText(R.string.turn_off);
                    Camera.Parameters p = cam.getParameters();
                    p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    cam.setParameters(p);
                    cam.startPreview();
                }
                status = !status;
            }
        });
    }
}
