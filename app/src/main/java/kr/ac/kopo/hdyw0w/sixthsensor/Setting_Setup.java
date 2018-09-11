package kr.ac.kopo.hdyw0w.sixthsensor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class Setting_Setup extends AppCompatActivity{

    private Switch soundSwitch,vibratorSwitch;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item_setting);

        soundSwitch = (Switch)findViewById(R.id.fis_soundSwitch);
        vibratorSwitch = (Switch)findViewById(R.id.fis_VibratorSwitch);

        soundSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1,str2;
                if (soundSwitch.isChecked())
                    str1 = soundSwitch.getTextOn().toString();
                else
                    str1 = soundSwitch.getTextOff().toString();
                Toast.makeText(getApplicationContext(),"Switch - "+str1, Toast.LENGTH_SHORT).show();

                if (vibratorSwitch.isChecked())
                    str2 = vibratorSwitch.getTextOn().toString();

                else
                    str2 = vibratorSwitch.getTextOff().toString();
                Toast.makeText(getApplicationContext(),"Switch - "+str2, Toast.LENGTH_SHORT).show();

            }
        });
    }


}
