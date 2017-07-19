package ifpb.edu.com.br.questao3;

/**
 * Created by welli on 16/07/2017.
 */

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Classe responsável por baixar o json contento o resultado da busca pelo CEP
 * Por conter um método estático, essa classe não precisa ser instanciada para
 * usar o método acessar.
 * Aqui é definida a URL que contém endereço é passado por parâmetro.
 */
public class Auxiliar {
    public static CEP acessar(String cep) {
       String auxiliar;
        CEP objetoCEP;

        try {

//            URL url = new URL("http://correiosapi.apphb.com/cep/" + cep);
            URL url = new URL("http://viacep.com.br/ws/" + cep + "/json");
            URLConnection urlConnection = url.openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();
            String line;

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                jsonSb.append(line);
            }


            auxiliar = jsonSb.toString();

            final JSONObject jsonObject = new JSONObject(auxiliar);

            try {
                String logradouro = jsonObject.getString("logradouro");
                String bairro = jsonObject.getString("bairro");
                String localidade = jsonObject.getString("localidade");
                String uf = jsonObject.getString("uf");
                objetoCEP = new CEP(cep, logradouro, bairro, localidade, uf);

                return  objetoCEP;

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.i("AsyncTask", "Baixando o CEP Thread: " + Thread.currentThread().getName() + "\n json");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
