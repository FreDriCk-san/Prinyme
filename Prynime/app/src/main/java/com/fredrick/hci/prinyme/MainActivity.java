package com.fredrick.hci.prinyme;

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

        //Button pButton = (Button)findViewById(R.id.Button1);
        //pButton.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View v) {
               // setContentView(R.layout.pictures_layout);
            //}
        //});
    }

    public void onPictureButtonClick(View view)
    {
        Intent intent = new Intent(this, DisplayPicturesActivity.class);
        startActivity(intent);
    }
}
