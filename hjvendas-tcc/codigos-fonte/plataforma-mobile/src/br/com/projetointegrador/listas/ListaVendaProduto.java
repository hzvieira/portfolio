package br.com.projetointegrador.listas;

import java.util.ArrayList; 
import java.util.List;

import br.com.projetointegrador.R;
import br.com.projetointegrador.adapters.VendaProdutoListAdapter;
import br.com.projetointegrador.controles.ControleItemPedido;
import br.com.projetointegrador.entidades.ItemPedido;
import br.com.projetointegrador.entidades.Pedido;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ListaVendaProduto extends Activity {

	private List<ItemPedido> itens = new ArrayList<ItemPedido>();
	private ListView listaView;
	private Long id;
	private static String informacao = new String("Informação: " +
			"Esta tela tem o objetivo de de listar os itens de uma venda, " +
			"cuidado, ao clicar em um item, voce mudara a situação de entrega do mesmo."
			);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pesquisar);
		
		Button botao = (Button) findViewById(R.id.incluir);
		botao.setVisibility(4);
		
		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			id = extras.getLong(Pedido.colunas[0]);
			if (id != null) {
				
				atualizarLista(id);
				
				TextView informacao = (TextView) findViewById(R.id.informcao);
				informacao.setText((CharSequence) getInformacao());

			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		atualizarLista(id);
	}
	
	public void atualizarLista(long id2) {
		listaView = (ListView) findViewById(R.id.lista);
		atualizarListaItens();
		listaView.setAdapter(new VendaProdutoListAdapter(getApplicationContext(), itens));
	}

	public void atualizarListaItens() {
		itens = new ControleItemPedido(getApplicationContext()).listarItens(getApplicationContext(), id);
	}
	
	public void voltar(View v) {
		finish();
	}

	public static String getInformacao() {
		return informacao;
	} 
}
