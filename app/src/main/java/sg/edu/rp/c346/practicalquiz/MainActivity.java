package sg.edu.rp.c346.practicalquiz;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tvName, tvAge, tvClass;
    EditText etName, etAge;
    Spinner spnClass;
    Button btnSave;

    @Override
    protected void onPause() {
        super.onPause();

        String strName = etName.getText().toString();
        int intAge = Integer.parseInt(etAge.getText().toString());
        int selectClass = spnClass.getDropDownVerticalOffset();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putString("Name", strName);
        prefEdit.putInt("Age", intAge);
        prefEdit.putInt("Class", selectClass);
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String name = prefs.getString("Name", "No Name!");
        int age = prefs.getInt("Age", 0);
        int select = prefs.getInt("Class", 0);
        etName.setText(name);
        etAge.setText(age);
        spnClass.setDropDownVerticalOffset(select);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName = findViewById(R.id.textViewName);
        etName = findViewById(R.id.editTextName);
        tvAge = findViewById(R.id.textViewAge);
        etAge = findViewById(R.id.editTextAge);
        tvClass = findViewById(R.id.textViewClass);
        spnClass = findViewById(R.id.spinnerClass);
        btnSave = findViewById(R.id.buttonSave);

        spnClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                switch (i) {
                    case 0:
                        spnClass.setDropDownVerticalOffset(0);
                        break;
                    case 1:
                        spnClass.setDropDownVerticalOffset(1);
                        break;
                    case 2:
                        spnClass.setDropDownVerticalOffset(2);
                        break;
                    case 3:
                        spnClass.setDropDownVerticalOffset(3);
                        break;
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectClass = spnClass.getDropDownVerticalOffset();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.putInt("Class", selectClass);
                Toast toast = Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT);
            }
        });
    }
}
