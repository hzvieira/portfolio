package br.com.projetointegrador.listas;

import java.util.List;

import br.com.projetointegrador.R;
import br.com.projetointegrador.adapters.PeriodoListAdapter;
import br.com.projetointegrador.cadastros.CadastroPeriodoVenda;
import br.com.projetointegrador.controles.ControlePeriodoVendas;
import br.com.projetointegrador.entidades.PeriodoVendas;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListaPeriodoVenda extends Activity {
	public static ControlePeriodoVendas repositorio;
	private PeriodoVendas clickList;
	private List<PeriodoVendas> periodos;
	
	private AlertDialog.Builder builder;
	private AlertDialog alerta;
	private AutoCompleteTextView textView;
	private ListView listaView;

	private static String informacao = new String("Informação: " +
			"Esta tela tem o objetivo de de listar e/ou " +
			"cadastrar os periodos de vendas impostos pelos fornecedores," +
			"bem como se os pedidos foram realizados e as entregas também.");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pesquisar);
		
		atualizarLista();
		
		TextView informacao = (TextView) findViewById(R.id.informcao);
		informacao.setText((CharSequence) getInformacao());
		
		listaView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2, long arg3) {
				clickList = (PeriodoVendas) arg0.getItemAtPosition(arg2);
		
				LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View botoes = layoutInflater.inflate(R.layout.botoeslistaquatroopcoes, null);
				Button botao = (Button) botoes.findViewById(R.id.btOpcao);	
				
				if (!clickList.isPedidoFeito()) {
					botao.setText("Fazer pedido final"); 
				} else if (!clickList.isRecebeuEncomenda()) {
					botao.setText("Receber encomenda");					
				} else {
					botao.setVisibility(Button.INVISIBLE);
				}
				
				// Alerta
				// Monta a view
				
				builder = new AlertDialog.Builder(ListaPeriodoVenda.this);
				alerta = builder.setView(botoes).create();
				alerta.setTitle("Opções");
				alerta.setMessage("Escolha uma operação");
				alerta.show();
				
			}
		});

		textView.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				periodos = repositorio.autoCompleta(s, getApplicationContext());
				ListView listaView = (ListView) findViewById(R.id.lista);
				listaView.setAdapter(new PeriodoListAdapter(getApplicationContext(), periodos));
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
		textView.setHint("Digite o nome do fornecedor");
		atualizarListaParcelasPagar();
		textView.setAdapter(new ArrayAdapter<PeriodoVendas>(ListaPeriodoVenda.this, android.R.layout.simple_dropdown_item_1line, periodos));
		listaView.setAdapter(new PeriodoListAdapter(getApplicationContext(), periodos));
	}

	public void inserir(View v) {
		startActivity(new Intent(ListaPeriodoVenda.this, CadastroPeriodoVenda.class));
	}

	public void alterar(View v) {
		startActivityForResult(new Intent(ListaPeriodoVenda.this, CadastroPeriodoVenda.class).putExtra(PeriodoVendas.colunas[0], clickList.getId()), 1);

		alerta.cancel();
		atualizarLista();
	}

	public void excluir(View v) {
		if (clickList.isPedidoFeito()) {
			Toast.makeText(getApplicationContext(), "Não é possivel deletar um periodo quando já foi realizado o pedido final!", Toast.LENGTH_LONG).show();
		} else {
			repositorio.deletar(clickList.getId());
			alerta.cancel();
			atualizarLista();
		}
	}

	public void cancelar(View v) {
		alerta.cancel();
	}

	public void atualizarListaParcelasPagar() {
		repositorio = new ControlePeriodoVendas(this);
		periodos = repositorio.listarPeriodoVendas(getApplicationContext());
	}
	
	public void voltar(View v) {
		finish();
	}
	
	public void opcao(View v) {
		Toast.makeText(getApplicationContext(), "CLick: " + clickList.isPedidoFeito(), Toast.LENGTH_LONG).show();
		if (!clickList.isPedidoFeito()) {
			atualizarPedidoFeito(clickList);
		} else if (!clickList.isRecebeuEncomenda()) {
			clickList.setRecebeuEncomenda(true);
			salvarReceberEncomenda(clickList);
		} else {
			Toast.makeText(getApplicationContext(), "Não há opções para sua solicitação!", Toast.LENGTH_LONG).show();
		}
	}

	private void salvarReceberEncomenda(PeriodoVendas clickList2) {
		new ControlePeriodoVendas(getApplicationContext()).salvar(clickList2);
	}

	private void atualizarPedidoFeito(PeriodoVendas clickList2) {
		Long c = new ControlePeriodoVendas(getApplicationContext()).atualizarFazerPedido(clickList2,getApplicationContext());
		if (c > 0) {
			Toast.makeText(getApplicationContext(), "Saldo dos clientes atulizados!", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(getApplicationContext(), "Não foram ralizadas vendas para o periodo", Toast.LENGTH_LONG).show();
		}
	}

	public static String getInformacao() {
		return informacao;
	}	
}
