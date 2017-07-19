package ifpb.edu.com.br.questao1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Activity4 extends AppCompatActivity {


    private final String TAG = "CADASTRO_ALUNO";
    private final String ALUNOS_KEY = "LISTA";

    private EditText edNome;
    private Button botao;
    private ListView lvListagem;
    private List<String> listaAlunos;
    private ArrayAdapter<String> adapter;
    private int adapterLayout = android.R.layout.simple_list_item_1;



    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        inclusão da lista no objeto Bundle.Map
        outState.putStringArrayList(ALUNOS_KEY, (ArrayList<String>) listaAlunos);
//        persistindo o objeto
        super.onSaveInstanceState(outState);
        Log.i(TAG, " onSaveInstanceState() : " + listaAlunos);

    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //      Recupera o estado do objeto Bundle.map
        super.onRestoreInstanceState(savedInstanceState);
        listaAlunos = savedInstanceState.getStringArrayList(ALUNOS_KEY);
//        mensagem de log
        Log.i(TAG, "  onRestoreInstanceState() : " + listaAlunos);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);



        edNome = (EditText) findViewById(R.id.edNomeListagem);
        botao = (Button) findViewById(R.id.btAddListagem);
        lvListagem = (ListView) findViewById(R.id.lvListagem);

        listaAlunos = new ArrayList<String>();


        if(savedInstanceState != null){
            onRestoreInstanceState(savedInstanceState);
        }
        adapter = new ArrayAdapter<String>(this, adapterLayout, listaAlunos);

        lvListagem.setAdapter(adapter);


        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaAlunos.add(edNome.getText().toString());
                edNome.setText("");
                adapter.notifyDataSetChanged();
            }
        });

        lvListagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view,
                                    int posicao, long id) {
                Toast.makeText(Activity4.this,
                        "Aluno: " + listaAlunos.get(posicao), Toast.LENGTH_LONG).show();
            }
        });

        lvListagem.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view,
                                           int posicao, long id) {
                Toast.makeText(Activity4.this,
                        "Aluno: " + listaAlunos.get(posicao) + " [click long] ",
                        Toast.LENGTH_LONG).show();

                return true;
            }
        });


    }
}