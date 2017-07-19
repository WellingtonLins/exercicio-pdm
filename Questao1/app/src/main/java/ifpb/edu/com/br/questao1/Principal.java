package ifpb.edu.com.br.questao1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);
    }

    public void chamarTela_1(View v) {
        Intent intent = new Intent(this,Activity1.class);

        startActivity(intent);

    }
    public void chamarTela_2(View v) {
        Intent intent = new Intent(this,Activity2.class);
        startActivity(intent);

    }
    public void chamarTela_3(View v) {
        Intent intent = new Intent(this,Activity3.class);

        startActivity(intent);

    }
    public void chamarTela_4(View v) {
        Intent intent = new Intent(this,Activity4.class);

        startActivity(intent);

    }
}
