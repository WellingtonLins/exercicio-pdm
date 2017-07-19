package ifpb.edu.com.br.questao2;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final static Double BOLO = 0.6;
    private final static Double SALGADOS = 6d;
    private final static Double DOCES = 8d;
    private final static Double REFRIGERANTE = 0.6;
    private final static Double BOLO_C = 0.4;
    private final static Double SALGADOS_C = 4d;
    private final static Double DOCES_C = 6d;
    private final static Double REFRIGERANTE_C = 0.5;

    private EditText adultoQuant, criancaQuant;
    private TextView tVBolo, tVDoce, tVSalgado, tVRefrigerante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adultoQuant = (EditText) findViewById(R.id.edText_adulto);
        criancaQuant = (EditText) findViewById(R.id.editText_crianca);
        tVBolo = (TextView) findViewById(R.id.textBolo);
        tVDoce = (TextView) findViewById(R.id.textDoce);
        tVSalgado = (TextView) findViewById(R.id.textSalgado);
        tVRefrigerante = (TextView) findViewById(R.id.textSoda);

        Button btsomar = (Button) findViewById(R.id.btn_resposta);

        btsomar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                double adulto = Double.parseDouble(adultoQuant.getText().toString());
                double crianca = Double.parseDouble(criancaQuant.getText().toString());


                tVBolo.setText(String.valueOf((double) (adulto * BOLO) +  (crianca * BOLO_C)));
                tVDoce.setText(String.valueOf((double)  (adulto * DOCES)+ (crianca * DOCES_C)));
                tVSalgado.setText(String.valueOf((double) (adulto * SALGADOS)+ (crianca * SALGADOS_C)));
                tVRefrigerante.setText(String.valueOf((double) (adulto * REFRIGERANTE)+ (crianca * REFRIGERANTE_C)));


            }
        });

    }
}