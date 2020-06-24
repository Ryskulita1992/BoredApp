package kg.geektech.todo.data.abstractActivityAndFrag;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import kg.geektech.todo.R;

abstract public class BaseActivity  extends AppCompatActivity {

    protected void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
