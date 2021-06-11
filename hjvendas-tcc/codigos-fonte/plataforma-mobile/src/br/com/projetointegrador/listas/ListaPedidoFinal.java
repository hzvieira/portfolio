package br.com.projetointegrador.listas;

import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.R;
import br.com.projetointegrador.adapters.VendaProdutoListAdapter;
import br.com.projetointegrador.controles.ControleItemPedido;
import br.com.projetointegrador.controles.ControlePeriodoVendas;
import br.com.projetointegrador.entidades.ItemPedido;
import br.com.projetointegrador.entidades.PeriodoVendas;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ListaPedidoFinal extends Activity {

	private List<ItemPedido> itens = new ArrayList<ItemPedido>();
	private ListView listaView;
	private Long id;
	private static String informacao = new String(
			"Informação: "
					+ "Esta tela tem o objetivo de listar os itens de um determinado fornecedor, "
					+ "clique em 'Fazer pedido final' caso ja tenha tenha concluido todo o processo "
					+ "para pedir os produtos para fornecedor.");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pesquisar);

		Button botao = (Button) findViewById(R.id.incluir);

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			id = extras.getLong(PeriodoVendas.colunas[0]);
			if (id != null) {
				atualizarLista(id);

				if (!(new ControlePeriodoVendas(getApplicationContext()).buscarPeriodoVendas(id, getApplicationContext()).isPedidoFeito())) {
					botao.setText("Fazer pedido final");					
				} else {
					botao.setText("Receber encomenda");
				}

				
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
		listaView.setAdapter(new VendaProdutoListAdapter(
				getApplicationContext(), itens));
	}

	public void atualizarListaItens() {
		itens = new ControleItemPedido(getApplicationContext())
				.listarPedidoFinal(getApplicationContext(),
						new ControlePeriodoVendas(getApplicationContext())
								.buscarPeriodoVendas(id,
										getApplicationContext()));
	}

	public void voltar(View v) {
		finish();
	}

	public static String getInformacao() {
		return informacao;
	}

	public void inserir(View v) {
		PeriodoVendas periodo = new PeriodoVendas();
		periodo = new ControlePeriodoVendas(getApplicationContext()).buscarPeriodoVendas(id, getApplicationContext());
		
		if (!periodo.isPedidoFeito()) {
			periodo.setPedidoFeito(true);
			new ControlePeriodoVendas(getApplicationContext()).salvar(periodo);
		} else if (!periodo.isRecebeuEncomenda()) {
			periodo.setRecebeuEncomenda(true);			
			new ControlePeriodoVendas(getApplicationContext()).salvar(periodo);
		} 
		

	}
}
