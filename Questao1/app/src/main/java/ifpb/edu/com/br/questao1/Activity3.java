package ifpb.edu.com.br.questao1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {
    private Button btn_Resposta;
    private TextView tVSelecao;
    private RadioButton rb1, rb2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        btn_Resposta = (Button) findViewById(R.id.btn_selecao);
        tVSelecao = (TextView) findViewById(R.id.resposta);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);



        btn_Resposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (rb1.isChecked()) {
                    tVSelecao.setText(rb1.getText());
                } else if (rb2.isChecked()) {

                    tVSelecao.setText(rb2.getText());

                } else  {
                    tVSelecao.setText("Você não selecionou linguagem de programação alguma!");
                }

            }
        });
    }
}
