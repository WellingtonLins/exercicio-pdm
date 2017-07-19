package ifpb.edu.com.br.questao1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
    }
    public void mostrarToast(View v) {
        Toast.makeText(this,"Um dia eu fui útil!",Toast.LENGTH_LONG).show();


    }
}
