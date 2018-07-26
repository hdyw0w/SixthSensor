package kr.ac.kopo.hdyw0w.sixthsensor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.start).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent_act);
                    }
                }
        );
    }
}