package pk.edu.pucit.filehandling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    final int PERMISSION_REQUEST_CODE = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);

        Log.i("Filesdir", getFilesDir().getAbsolutePath());
        File file = new File(getFilesDir().getAbsolutePath() + "/first_file.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write("This is first line in file".getBytes());
            for (int i = 0; i < 3+(Math.random()*10) % 5; i++) {
                fos.write(("\nThis is "+(i+1)+" line in file").getBytes());
            }
            fos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            TextView read_data = findViewById(R.id.read_data);
            read_data.setText(new String(buffer));
            fis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }
}
