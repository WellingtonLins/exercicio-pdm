package ifpb.edu.com.br.questao1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SaudacaoActivity extends AppCompatActivity {

    public static final String EXTRA_NOME_USUARIO = "EXTRA_NOME_USUARIO";
    public static final String ACAO_EXIBIR_SAUDACAO = "ACAO_EXIBIR_SAUDACAO";
    public static final String CATEGORIA_SAUDACAO = "CATEGORIA_SAUDACAO";

    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saudacao);
        String nome = getIntent().getStringExtra(EXTRA_NOME_USUARIO);
        textView = (TextView) findViewById(R.id.textView);

        if (!nome.trim().isEmpty()) {
//        if (!nome.matches("\\s*")) {
            String saudacao = getResources().getString(R.string.saudacao);
            textView.setText(saudacao + nome);
        } else {
            textView.setText("Cadê o nome cabra?");

        }


////Verifica se a intent foi passada como parametro
//        Intent intent = getIntent();
//        if (intent.hasExtra(EXTRA_NOME_USUARIO)) {
//            String saudacao = getResources().getString(R.string.saudacao);
//            textView.setText(saudacao + " " +
//                    intent.getStringExtra(EXTRA_NOME_USUARIO));
//        } else {
//            textView.setText("O nome do usuário não foi informado");
//        }
    }
}