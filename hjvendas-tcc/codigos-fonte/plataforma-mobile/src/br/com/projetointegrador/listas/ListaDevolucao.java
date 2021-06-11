package br.com.projetointegrador.listas;

import java.util.List;

import br.com.projetointegrador.adapters.DevolucaoListAdapter;
import br.com.projetointegrador.cadastros.CadastroDevolucao;
import br.com.projetointegrador.controles.ControleDevolucao;
import br.com.projetointegrador.entidades.Devolucao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

public class ListaDevolucao extends Activity {
	
	private List<Devolucao> devolucoes;
	private AutoCompleteTextView textView;
	private ListView listaView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(br.com.projetointegrador.R.layout.pesquisar);
		
		atualizarLista();
		TextView informacao = (TextView) findViewById(br.com.projetointegrador.R.id.informcao);
		informacao.setText(br.com.projetointegrador.R.string.informacaoDevolucao ); 
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		atualizarLista();
	}

	public void atualizarLista() {
		textView = (AutoCompleteTextView) findViewById(br.com.projetointegrador.R.id.pesquisar);
		listaView = (ListView) findViewById(br.com.projetointegrador.R.id.lista);
		atualizarListaDevolucao();
		textView.setAdapter(new ArrayAdapter<Devolucao>(this, android.R.layout.simple_dropdown_item_1line, devolucoes));
		listaView.setAdapter(new DevolucaoListAdapter(getApplicationContext(), devolucoes));
	} 
	
	public void atualizarListaDevolucao() {
		devolucoes = new ControleDevolucao(getApplicationContext()).listarDevolucao(getApplicationContext());
	} 
	
	public void inserir(View v) {
		startActivity(new Intent(ListaDevolucao.this, CadastroDevolucao.class));
	}
	
	public void voltar(View v) {
		finish();
	}
	
}
