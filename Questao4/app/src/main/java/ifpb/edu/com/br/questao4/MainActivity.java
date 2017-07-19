package ifpb.edu.com.br.questao4;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

/**
 * A aplicação recebe uma URL contendo apenas uma imagem que será baixada
 * e mostrada na tela do usuário.
 * Todo processamento será feito em uma Thread separada.
 */
public class MainActivity extends Activity {
    private ImageView imagem;
    private Button baixar;
    private EditText endereco;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagem = (ImageView)findViewById(R.id.imageView);
        baixar = (Button)findViewById(R.id.button);
        endereco = (EditText)findViewById(R.id.editText);
// Utilizado para mostrar no log em qual Thread cada parte do código está executando.
        Log.i("AsyncTask", "Elementos de tela criados e atribuidos Thread: " + Thread.currentThread().getName());
        /**
         * Criando o evento para quando o botão for clicado , assim disparando o
         * processo que inicia o download
         */
        baixar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {                      //Pegando o nome da Thread
                Log.i("AsyncTask", "Botão clicado. Thread: " + Thread.currentThread().getName());
                chamarAsyncTask(endereco.getText().toString());
            }
        });
    }

    /**
     * Método que cria uma instancia da classe interna TarefaDownload que extend AsyncTask
     */
    private void chamarAsyncTask(String endereco){
        TarefaDownload download = new TarefaDownload();
        Log.i("AsyncTask", "AsyncTask sendo chamado. Thread: " + Thread.currentThread().getName());
        download.execute(endereco);//Chamando o método execute da classe abstrata AsyncTask
    }

    /**
     * A classe AsyncTask foi criada para facilitar o processamento em background e
     * atualizar os dados na Interface.AsyncTask permite o gerenciamento e a criação
     * de threads para a execução concorrente de aplicações.
     * AsyncTask<Parâmetros,Progresso,Resultado>
     */
    private class TarefaDownload extends AsyncTask<String, Void, Bitmap>{
        /**
         * O método onPreExecute é executado sempre antes da Thread ser iniciada.
         *  Aqui são colocadas as mensagens a serem exibidas na tela para o usuário.
         * Não é obrigatório e executa na mesma Thread que a interface gráfica;
         */
        @Override
        protected void onPreExecute(){
            Log.i("AsyncTask", "Exibindo ProgressDialog na tela Thread: " + Thread.currentThread().getName());
            load = ProgressDialog.show(MainActivity.this, "Por favor Aguarde ...",
                    "Baixando Imagem ...");
        }

        /**
         * Responsável por todo o processamento pesado, pois ele é executado em uma Thread separada.
         *  Enquanto é processada a informação, a tela do usuário não ficará travada. Seu retorno
         *  é passado por parâmetro para o método onPostExecute;
         *  Implementação é Obrigatória.
         */
        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap imagemBitmap = null;
            try{
                imagemBitmap = null;
                Log.i("AsyncTask", "Baixando a imagem Thread: " + Thread.currentThread().getName());
                imagemBitmap = Auxiliar.baixarImagem(params[0]);//pegando o 1 parametro

            }catch (IOException e){
                Log.i("AsyncTask", e.getMessage());
            }

            return imagemBitmap;
        }

        /**
         *Recebe o retorno do doInBackground e é chamado utilizando um Handler.
         * Sua execução se dá na mesma Thread que a interface gráfica;
         * Não é obrigátorio sua implementação
         */
        @Override
        protected void onPostExecute(Bitmap bitmap){
            if(bitmap!=null) {
                imagem.setImageBitmap(bitmap);
                Log.i("AsyncTask", "Exibindo Bitmap Thread: " + Thread.currentThread().getName());
            }else{
                Log.i("AsyncTask", "Erro ao baixar a imagem " + Thread.currentThread().getName());
            }
            Log.i("AsyncTask", "Tirando ProgressDialog da tela Thread: " + Thread.currentThread().getName());
            load.dismiss();
        }
    }

}