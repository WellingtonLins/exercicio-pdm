package ifpb.edu.com.br.questao4;

/**
 * Created by welli on 16/07/2017.
 */


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Classe responsável por baixar a imagem
 * Por conter um método estático, essa classe não precisa ser instanciada para
 * acessar o método baixarImagem. Aqui é definida a URL que contém a imagem e
 * seu endereço é passado por parâmetro. Um objeto do tipo InputStream é responsável por
 * receber as informações da imagem, que por enquanto ainda está codificada.
 * Por fim, o objeto do tipo Bitmap recebe o retorno do método estático decodeStream
 * contido dentro da classe BitmapFactory, que é responsável por converter para Bitmap
 * o conteudo do inputStream.
 */
  public class Auxiliar {
    public static Bitmap baixarImagem(String url) throws IOException {
        URL endereco;
        InputStream inputStream;
        Bitmap imagem;

        endereco = new URL(url);
        inputStream = endereco.openStream();
        imagem = BitmapFactory.decodeStream(inputStream);

        inputStream.close();//conexão encerrada

        return imagem;
    }
}