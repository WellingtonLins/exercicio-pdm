package ifpb.edu.com.br.questao1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Activity1 extends AppCompatActivity {

    private EditText nomeEditText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
        this.nomeEditText = (EditText) findViewById(R.id.nomeEditText);

    }

    public void surpreenderUsuario(View v) {
        Intent intent = new Intent(SaudacaoActivity.ACAO_EXIBIR_SAUDACAO);
        intent.addCategory(SaudacaoActivity.CATEGORIA_SAUDACAO);
        String texto = nomeEditText.getText().toString();
        intent.putExtra(SaudacaoActivity.EXTRA_NOME_USUARIO, texto);
        startActivity(intent);

    }
    public void mostrarToast(View v) {
        Intent intent = new Intent(this,Activity2.class);
        startActivity(intent);


    }
}
