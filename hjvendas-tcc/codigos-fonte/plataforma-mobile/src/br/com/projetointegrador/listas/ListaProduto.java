package br.com.projetointegrador.listas;

import java.util.List;

import br.com.projetointegrador.R;
import br.com.projetointegrador.adapters.ProdutoListAdapter;
import br.com.projetointegrador.cadastros.CadastroProdutoAcessorio;
import br.com.projetointegrador.cadastros.CadastroProdutoCalcado;
import br.com.projetointegrador.cadastros.CadastroProdutoCosmetico;
import br.com.projetointegrador.controles.ControleProduto;
import br.com.projetointegrador.entidades.Produto;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ListaProduto extends Activity {
	public static ControleProduto repositorio;
	private Produto clickList;
	private List<Produto> produtos;
	
	private AlertDialog.Builder builder;
	private AlertDialog alerta;
	private AutoCompleteTextView textView;
	private ListView listaView;
	
	private static String informacao = new String("Informação: " +
			"Esta tela tem o objetivo de de listar e/ou " +
			"cadastrar os produtos que voce pode vender.");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pesquisarproduto);
		
		atualizarLista();
		
		TextView informacao = (TextView) findViewById(R.id.informcao);
		informacao.setText((CharSequence) getInformacao());
		
		listaView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
				clickList = (Produto) arg0.getItemAtPosition(arg2);
				// Alerta
				// Monta a view
				
				LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View botoes = layoutInflater.inflate(R.layout.botoestela, null);

				builder = new AlertDialog.Builder(ListaProduto.this);
				alerta = builder.setView(botoes).create();
				alerta.setTitle("Opções");
				alerta.setMessage("Escolha uma operação");
				alerta.show();
			}
		});

		textView.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				produtos = repositorio.autoCompleta(s);
				ListView listaView = (ListView) findViewById(R.id.lista);
				listaView.setAdapter(new ProdutoListAdapter(getApplicationContext(), produtos));
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
		atualizarListaProduto();
		textView.setAdapter(new ArrayAdapter<Produto>(ListaProduto.this, android.R.layout.simple_dropdown_item_1line, produtos));
		listaView.setAdapter(new ProdutoListAdapter(getApplicationContext(), produtos));
	}

	public void incluirAcessorio(View v) {
		startActivity(new Intent(ListaProduto.this, CadastroProdutoAcessorio.class));
	}
	
	public void inserirCalcado(View v) {
		startActivity(new Intent(ListaProduto.this, CadastroProdutoCalcado.class));
	}
	
	public void inserirCosmetico(View v) {
		startActivity(new Intent(ListaProduto.this, CadastroProdutoCosmetico.class));
	}
	
	public void alterar(View v) {
		
		if (clickList.getProduto().equals("ACESSORIO")) {
			startActivityForResult(new Intent(ListaProduto.this, CadastroProdutoAcessorio.class).putExtra(Produto.colunas[0], clickList.getId()), 1);		
		} else if (clickList.getProduto().equals("CALCADO")) {
			startActivityForResult(new Intent(ListaProduto.this, CadastroProdutoCalcado.class).putExtra(Produto.colunas[0], clickList.getId()), 1);		
		} else if (clickList.getProduto().equals("COSMETICO")) {
			startActivityForResult(new Intent(ListaProduto.this, CadastroProdutoCosmetico.class).putExtra(Produto.colunas[0], clickList.getId()), 1);		
		} else {
			Log.i("ListaProduto", "Nenhum produto encontrado!");
		}

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

	public void atualizarListaProduto() {
		repositorio = new ControleProduto(this);
		produtos = repositorio.listarProdutos();
	}
	
	public void voltar(View v) {
		finish();
	}
	
	public void selecionar(View v){
		setResult(RESULT_OK, new Intent().putExtra(Produto.colunas[0], clickList.getId()));
		alerta.cancel();
		finish();
	}

	public static String getInformacao() {
		return informacao;
	}
}
