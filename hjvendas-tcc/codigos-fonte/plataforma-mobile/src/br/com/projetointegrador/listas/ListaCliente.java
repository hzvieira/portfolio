package br.com.projetointegrador.listas;

import java.util.List;

import br.com.projetointegrador.R;
import br.com.projetointegrador.adapters.ClienteListAdapter;
import br.com.projetointegrador.cadastros.CadastroCliente;
import br.com.projetointegrador.controles.ControleCliente;
import br.com.projetointegrador.entidades.Cliente;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListaCliente extends Activity {
	public static ControleCliente repositorio;
	private Cliente clickList;
	private List<Cliente> lista;
	
	private AlertDialog.Builder builder;
	private AlertDialog alerta;
	private AutoCompleteTextView textView;
	private ListView listaView;
	EditText valorRecebido;
	
	private static String informacao = new String("Informação: " +
			"Esta tela tem o objetivo de de listar e/ou " +
			"cadastrar os clintes, atualizar saldo e " +
			"começar uma nova venda selecionando um deles.");
	
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
				clickList = (Cliente) arg0.getItemAtPosition(arg2);
				// Alerta
				// Monta a view
				
				LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View botoes = layoutInflater.inflate(R.layout.botoeslistacliente, null);

				builder = new AlertDialog.Builder(ListaCliente.this);
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
				listaView.setAdapter(new ClienteListAdapter(getApplicationContext(), lista));
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
		atualizarListaCliente();
		textView.setAdapter(new ArrayAdapter<Cliente>(ListaCliente.this, android.R.layout.simple_dropdown_item_1line, lista));
		listaView.setAdapter(new ClienteListAdapter(getApplicationContext(), lista));
	}

	public void inserir(View v) {
		startActivity(new Intent(ListaCliente.this, CadastroCliente.class));
	}

	public void alterar(View v) {
		startActivityForResult(new Intent(ListaCliente.this, CadastroCliente.class).putExtra(Cliente.colunas[0], clickList.getId()), 1);

		alerta.cancel();
		atualizarLista();
	}
	
	public void selecionar(View v){
		setResult(RESULT_OK, new Intent().putExtra(Cliente.colunas[0], clickList.getId()));
		alerta.cancel();
		finish();
	}

	public void excluir(View v) {
		repositorio.deletar(clickList.getId());
		alerta.cancel();
		atualizarLista();
	}

	public void cancelar(View v) {
		alerta.cancel();
	}

	public void atualizarListaCliente() {
		repositorio = new ControleCliente(this);
		lista = repositorio.listarCliente();
	}
	
	public void receberDinheiro(View v) {
		alerta.cancel();
		
		LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View botoes = layoutInflater.inflate(R.layout.frmreceberdinheiro, null);
	
		builder = new AlertDialog.Builder(ListaCliente.this);
		alerta = builder.setView(botoes).create();
		alerta.setTitle("Insira o valor recebido");
		alerta.setMessage("Cliente: " + clickList.getNome());
		alerta.show();
		
		valorRecebido = (EditText) botoes.findViewById(R.id.campoDinheiro);
		
	}
	
	public void validarDinheiro(View v) {

		Cliente atualizarSaldo = repositorio.buscarCliente(clickList.getId(), getApplicationContext());
		
		if(valorRecebido.getText().length() < 1) {
			Toast.makeText(getApplicationContext(), "Preencha um valor maior que 0!", Toast.LENGTH_LONG).show();
		} else if (Double.parseDouble(valorRecebido.getText().toString()) == 0) {
			Toast.makeText(getApplicationContext(), "Preencha um valor maior que 0!", Toast.LENGTH_LONG).show();			
		} else { 
			Double dinheiro = Double.parseDouble(valorRecebido.getText().toString());
			atualizarSaldo.setSaldo(atualizarSaldo.getSaldo() + dinheiro);
			repositorio.salvar(atualizarSaldo);
			alerta.cancel();
			Toast.makeText(getApplicationContext(), "Saldo atualizado!", Toast.LENGTH_LONG).show();
			atualizarLista();
		}
	}
	
	public void voltar(View v) {
		finish();
	}
	
}
