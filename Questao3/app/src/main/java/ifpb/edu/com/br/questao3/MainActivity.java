package ifpb.edu.com.br.questao3;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.BreakIterator;

/**
 * A aplicação recebe uma URL contendo apenas um json que será baixada
 * e mostrada na tela do usuário.
 * Todo processamento será feito em uma Thread separada.
 */
public class MainActivity extends Activity {

    private TextView textViewLogradouro;
    private TextView textViewBairro;
    private TextView textViewMunicipio;
    private TextView textViewUF;
    private Button baixar;
    private EditText endereco;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewLogradouro = (TextView)findViewById(R.id.textLogradouro);
        textViewBairro = (TextView)findViewById(R.id.textBairro);
        textViewMunicipio = (TextView)findViewById(R.id.textMunicipio);
        textViewUF = (TextView) findViewById(R.id.textUF);
        baixar = (Button)findViewById(R.id.button);
        endereco = (EditText)findViewById(R.id.editText);

// Utilizado para mostrar no log em qual Thread cada parte do código está executando.
        Log.i("AsyncTask", "Elementos de tela criados e atribuidos Thread: " + Thread.currentThread().getName());
        /**
         * Criando o evento para quando o botão for clicado , assim disparando o
         * processo que inicia a busca pelo endereço a partir do CEP
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
    private class TarefaDownload extends AsyncTask<String, Void, CEP>{
        /**
         * O método onPreExecute é executado sempre antes da Thread ser iniciada.
         *  Aqui são colocadas as mensagens a serem exibidas na tela para o usuário.
         * Não é obrigatório e executa na mesma Thread que a interface gráfica;
         */
        @Override
        protected void onPreExecute(){
            Log.i("AsyncTask", "Exibindo ProgressDialog na tela Thread: " + Thread.currentThread().getName());
            load = ProgressDialog.show(MainActivity.this, "Por favor Aguarde ...",
                    "Buscando o CEP...");
        }

        /**
         * Responsável por todo o processamento pesado, pois ele é executado em uma Thread separada.
         *  Enquanto é processada a informação, a tela do usuário não ficará travada. Seu retorno
         *  é passado por parâmetro para o método onPostExecute;
         *  Inico metodo da classe cuja a Implementação é obrigatória.
         */
        @Override
        protected CEP doInBackground(String... params) {

            try{
                Log.i("AsyncTask", "Baixando o CEP Thread: " + Thread.currentThread().getName());

                String filtro = params[0];
            if (TextUtils.isEmpty(filtro)) {
                return null;
            }

            CEP cep = Auxiliar.acessar(filtro);
//            System.out.println(json);

//            Map<String, String> mapa = new HashMap<String, String>();
////                                              //Expressão regular regex
////            Matcher matcher = Pattern.compile("\"\\D.*?\": \".*?\"").matcher(json);
////            while (matcher.find()) {
////                String[] group = matcher.group().split(":");
////                mapa.put(group[0].replaceAll("\"", "").trim(), group[1].replaceAll("\"", "").trim());
////            }
//////
////            System.out.println(mapa);
//
            return cep;


          }catch (Exception e){
                Log.i("AsyncTask", e.getMessage());
            }

            return  null;
        }

        /**
         *Recebe o retorno do doInBackground e é chamado utilizando um Handler.
         * Sua execução se dá na mesma Thread que a interface gráfica;
         * Não é obrigátorio sua implementação
         */
        @Override
        protected void onPostExecute(CEP resposta){
            if(endereco!=null) {
//                limpando os campos
                textViewLogradouro.setText("");
                textViewBairro.setText("");
                textViewMunicipio.setText("");
                textViewUF.setText("");
                textViewLogradouro.setText(resposta.getLogradouro());
                textViewBairro.setText(resposta.getBairro());
                textViewMunicipio.setText(resposta.getLocalidade());
                textViewUF.setText(resposta.getUf());

                Log.i("AsyncTask", "Exibindo CEP Thread: " + Thread.currentThread().getName());
            }else{
                Log.i("AsyncTask", "Erro ao baixar o CEP " + Thread.currentThread().getName());
            }
            Log.i("AsyncTask", "Tirando ProgressDialog da tela Thread: " + Thread.currentThread().getName());
            load.dismiss();
        }
    }

}