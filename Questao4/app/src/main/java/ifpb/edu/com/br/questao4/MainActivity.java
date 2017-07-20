package ifpb.edu.com.br.questao4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * A aplicação recebe uma URL contendo apenas uma imagem que será baixada
 * e mostrada na tela do usuário.
 * Todo processamento será feito em uma Thread separada.
 */

public class MainActivity extends Activity {

    public final String IMAGE_URL_1 =
            "https://s-media-cache-ak0.pinimg.com/564x/22/c5/b5/22c5b596478ca0fc4e18bfe715b3fb0a.jpg";

    private ImageView imagem;
    private Button baixar;
    private EditText endereco;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**
         * Criand o Intent para chamar a classe Auxiliar Responsável por todo o processamento pesado,
         *  pois ele é executado em uma Thread "separada".
         *  Enquanto é processada a informação, a tela do usuário não ficará travada.
         */

        Intent intent = new Intent(this, Auxiliar.class);
        intent.putExtra("image_url", IMAGE_URL_1);
        intent.putExtra("file_name", "file_1.png");
        startService(intent);

        Toast.makeText(MainActivity.this,"A sua imagem esta do diretorio SDCard",Toast.LENGTH_LONG).show();

    }

}