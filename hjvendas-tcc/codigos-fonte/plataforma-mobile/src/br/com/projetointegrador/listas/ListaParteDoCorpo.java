package br.com.projetointegrador.listas;

import java.util.List;

import br.com.projetointegrador.R;
import br.com.projetointegrador.cadastros.CadastroParteDoCorpo;
import br.com.projetointegrador.controles.ControleParteDoCorpo;
import br.com.projetointegrador.entidades.ParteDoCorpo;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ListaParteDoCorpo extends Activity {
	public static ControleParteDoCorpo repositorio;
	private ParteDoCorpo clickList;
	private List<ParteDoCorpo> lista;
	
	private AlertDialog.Builder builder;
	private AlertDialog alerta;
	private AutoCompleteTextView textView;
	private ListView listaView;
	
	public static String informacao = new String("Informação: " +
			"Esta tela tem o objetivo de de listar e/ou " +
			"cadastrar para qual área corporal o " +
			"produto sera destinado. Ex: olhos, labios, pés");
	
	public static String getInformacao() {
		return informacao;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pesquisar);
		
		atualizarLista();
		
		TextView informacao = (TextView) findViewById(R.id.informcao);
		informacao.setText(getInformacao());
		
		listaView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
				clickList = (ParteDoCorpo) arg0.getItemAtPosition(arg2);
				// Alerta
				// Monta a view
				
				LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View botoes = layoutInflater.inflate(R.layout.botoestelasimples, null);

				builder = new AlertDialog.Builder(ListaParteDoCorpo.this);
				alerta = builder.setView(botoes).create();
				alerta.setTitle("Opções");
				alerta.setMessage("Escolha uma operação");
				alerta.show();
			}
		});

		textView.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				lista = repositorio.autoCompleta(s);
				ListView listaView = (ListView) findViewById(R.id.lista);
				listaView.setAdapter(new ArrayAdapter<ParteDoCorpo>(ListaParteDoCorpo.this, android.R.layout.simple_spinner_item, lista));
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,	int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		}); 

	}

	@Override
	protected void onResume() {
		super.onResume();
		atualizarLista();
	}
	
	public void atualizarLista() {
		textView = (AutoCompleteTextView) findViewById(R.id.pesquisar);
		listaView = (ListView) findViewById(R.id.lista);
		atualizarListaParteDoCorpo();
		textView.setAdapter(new ArrayAdapter<ParteDoCorpo>(ListaParteDoCorpo.this, android.R.layout.simple_dropdown_item_1line, lista));
		listaView.setAdapter(new ArrayAdapter<ParteDoCorpo>(ListaParteDoCorpo.this, android.R.layout.simple_spinner_item, lista));
	}

	public void inserir(View v) {
		startActivity(new Intent(ListaParteDoCorpo.this, CadastroParteDoCorpo.class));
	}

	public void alterar(View v) {
		startActivityForResult(new Intent(ListaParteDoCorpo.this, CadastroParteDoCorpo.class).putExtra(ParteDoCorpo.colunas[0], clickList.getId()), 1);

		alerta.cancel();
		atualizarLista();
	}

	public void excluir(View v) {
		repositorio.deletar(clickList.getId());
		alerta.cancel();
		atualizarLista();
	}

	public void cancelar(View v) {
		alerta.cancel();
	}

	public void atualizarListaParteDoCorpo() {
		repositorio = new ControleParteDoCorpo(this);
		lista = repositorio.listarParteDoCorpo();
	}
	
	public void voltar(View v) {
		finish();
	}
	
}
