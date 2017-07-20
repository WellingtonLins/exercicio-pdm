package ifpb.edu.com.br.questao4;

/**
 * Created by welli on 16/07/2017.
 */


import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Utilizando o IntentService, a operação ocorre sem estar vinculada a nenhuma Activity.
 * Assim usuário não precisa ficar esperando o download terminar, como é uma imagem pequena
 * o download é instantaneo, mas  se fosse um filme  isso levaria muito tempo.
 * Esta classe faz todo o trabalho  em background sem fazer o usuario ficar esperando
 * pelo termino do download para fazer outra atividade
 */
public class Auxiliar extends IntentService {
    String name;

    public Auxiliar() {
        super("MyService");
    }

    /**
     *Método de implementação obrigatoria!
     * Aqui é feito toda a parte de busca e download da imagem
     * a partir de uma url
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        String img_url = intent.getStringExtra("image_url");
        String fileName = intent.getStringExtra("file_name");
        try {

            URL endereco;
            InputStream inputStream;
            Bitmap imagem;

            endereco = new URL(img_url);
            inputStream = endereco.openStream();
            imagem = BitmapFactory.decodeStream(inputStream);//obtendo a imagem

            File SDCardRoot = Environment.getExternalStorageDirectory();
            File file = new File(SDCardRoot, fileName);//local, nome_do_Arquivo
            FileOutputStream fileOutput = new FileOutputStream(file);
//            salvando a nossa querida imagem, 5 horas de pesquisa...
            imagem.compress(Bitmap.CompressFormat.PNG, 100, fileOutput);//formato , qualidade, arquivo

            fileOutput.close();
            inputStream.close();//conexão encerrada

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
