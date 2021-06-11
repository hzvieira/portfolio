package br.com.projetointegrador.listas;

import java.util.List;

import br.com.projetointegrador.R;
import br.com.projetointegrador.adapters.VendaListAdapter;
import br.com.projetointegrador.cadastros.CadastroVenda;
import br.com.projetointegrador.controles.ControleVenda;
import br.com.projetointegrador.entidades.Pedido;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListaPedido extends Activity {
	
	private List<Pedido> vendas;
	private AutoCompleteTextView textView;
	private ListView listaView;
	
	private Pedido clickList;
	
	private int FINALIZAR_VENDA = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(br.com.projetointegrador.R.layout.pesquisar);
		
		Button botao = (Button) findViewById(R.id.incluir);
		botao.setText("Nova venda");
		
		atualizarLista();
		TextView informacao = (TextView) findViewById(br.com.projetointegrador.R.id.informcao);
		informacao.setText(br.com.projetointegrador.R.string.informacaoPedido ); 
		
		listaView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
				clickList = (Pedido) arg0.getItemAtPosition(arg2);
				startActivity(new Intent(getApplicationContext(), ListaVendaProduto.class).putExtra(Pedido.colunas[0], clickList.getId()));
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		atualizarLista();
	}

	public void atualizarLista() {
		textView = (AutoCompleteTextView) findViewById(br.com.projetointegrador.R.id.pesquisar);
		listaView = (ListView) findViewById(br.com.projetointegrador.R.id.lista);
		atualizarListaVendas();
		textView.setAdapter(new ArrayAdapter<Pedido>(this, android.R.layout.simple_dropdown_item_1line, vendas));
		listaView.setAdapter(new VendaListAdapter(getApplicationContext(), vendas));
	} 
	
	public void atualizarListaVendas() {
		vendas = new ControleVenda(getApplicationContext()).listarVendas(getApplicationContext());
	} 
	
	public void inserir(View v) {
		startActivityForResult(new Intent(ListaPedido.this, CadastroVenda.class), FINALIZAR_VENDA);
	}
	
	public void voltar(View v) {
		finish();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if ((requestCode == FINALIZAR_VENDA) && (resultCode == RESULT_OK)) {
			Toast.makeText(ListaPedido.this, "Venda concluída com sucesso!", Toast.LENGTH_LONG).show();
		}
			
	}

}
