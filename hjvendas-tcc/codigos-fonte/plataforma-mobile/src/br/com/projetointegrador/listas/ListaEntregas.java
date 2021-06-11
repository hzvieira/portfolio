package br.com.projetointegrador.listas;

import java.util.List;

import br.com.projetointegrador.R;
import br.com.projetointegrador.adapters.EntregaListAdapter;
import br.com.projetointegrador.controles.ControleProntaEntrega;
import br.com.projetointegrador.entidades.ItemPedido;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ListaEntregas extends Activity {
	public static ControleProntaEntrega repositorio;
	private List<ItemPedido> itens;
	
	private AutoCompleteTextView textView;
	private ListView listaView;
	
	private static String informacao = new String("Informação: " +
			"Esta tela tem o objetivo de de listar " +
			"os produtos a serem entrgues.");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pesquisar);
		
		Button botao = (Button) findViewById(R.id.incluir);
		botao.setVisibility(4);
		
		atualizarLista();
		
		TextView informacao = (TextView) findViewById(R.id.informcao);
		informacao.setText((CharSequence) getInformacao());
		
		textView.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				itens = repositorio.autoCompletaEntregas(s, getApplicationContext());
				ListView listaView = (ListView) findViewById(R.id.lista);
				listaView.setAdapter(new EntregaListAdapter(getApplicationContext(), itens));
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,	int after) { }

			@Override
			public void afterTextChanged(Editable s) {	}
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
		atualizarListaProntaEntrega();
		textView.setAdapter(new ArrayAdapter<ItemPedido>(ListaEntregas.this, android.R.layout.simple_dropdown_item_1line, itens));
		listaView.setAdapter(new EntregaListAdapter(getApplicationContext(), itens));
	}

	public void atualizarListaProntaEntrega() {
		repositorio = new ControleProntaEntrega(this);
		itens = repositorio.listarEntregas(getApplicationContext());
	}
	
	public void voltar(View v) {
		finish();
	}

	public static String getInformacao() {
		return informacao;
	}
}
