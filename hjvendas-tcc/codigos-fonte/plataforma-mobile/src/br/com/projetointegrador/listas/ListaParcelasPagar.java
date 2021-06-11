package br.com.projetointegrador.listas;

import java.sql.Date;
import java.util.List;

import br.com.projetointegrador.R;
import br.com.projetointegrador.cadastros.CadastroParcelasPagar;
import br.com.projetointegrador.controles.ControleParcelasPagar;
import br.com.projetointegrador.entidades.ParcelasPagar;

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

public class ListaParcelasPagar extends Activity {
	public static ControleParcelasPagar repositorio;
	private ParcelasPagar clickList;
	private List<ParcelasPagar> parcelas;
	
	private AlertDialog.Builder builder;
	private AlertDialog alerta;
	private AutoCompleteTextView textView;
	private ListView listaView;
	private EditText dataPagamento;
	
	private static String informacao = new String("Informação: " +
			"Esta tela tem o objetivo de de listar e/ou " +
			"cadastrar suas contas a pagar");
	
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
				clickList = (ParcelasPagar) arg0.getItemAtPosition(arg2);
				// Alerta
				// Monta a view
				
				LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View botoes = layoutInflater.inflate(R.layout.botoeslistaquatroopcoes, null);

				builder = new AlertDialog.Builder(ListaParcelasPagar.this);
				alerta = builder.setView(botoes).create();
				alerta.setTitle("Opções");
				alerta.setMessage("Escolha uma operação");
				alerta.show();
			}
		});

		textView.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				parcelas = repositorio.autoCompleta(s, getApplicationContext());
				ListView listaView = (ListView) findViewById(R.id.lista);
				listaView.setAdapter(new ArrayAdapter<ParcelasPagar>(ListaParcelasPagar.this, android.R.layout.simple_spinner_item, parcelas));
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
		textView.setAdapter(new ArrayAdapter<ParcelasPagar>(ListaParcelasPagar.this, android.R.layout.simple_dropdown_item_1line, parcelas));
		listaView.setAdapter(new ArrayAdapter<ParcelasPagar>(ListaParcelasPagar.this, android.R.layout.simple_spinner_item, parcelas));
	}

	public void inserir(View v) {
		startActivity(new Intent(ListaParcelasPagar.this, CadastroParcelasPagar.class));
	}

	public void alterar(View v) {
		startActivityForResult(new Intent(ListaParcelasPagar.this, CadastroParcelasPagar.class).putExtra(ParcelasPagar.colunas[0], clickList.getId()), 1);

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

	public void atualizarListaParcelasPagar() {
		repositorio = new ControleParcelasPagar(this);
		parcelas = repositorio.listarParcelasPagar();
	}
	
	public void voltar(View v) {
		finish();
	}

	public static String getInformacao() {
		return informacao;
	}
	
	public void opcao(View v) {
		alerta.cancel();
		
		LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View botoes = layoutInflater.inflate(R.layout.frmpagarconta, null);
	
		builder = new AlertDialog.Builder(ListaParcelasPagar.this);
		alerta = builder.setView(botoes).create();
		alerta.setTitle("Insira a data de pagamento");
		alerta.setMessage("Vencimento: " + clickList.getDataVencimento() + "Valor: " + clickList.getValor());
		alerta.show();
		dataPagamento = (EditText) botoes.findViewById(R.id.campoDinheiro);
		
	}
	
	public void validarData(View v) {

		ParcelasPagar parcelasPagar = repositorio.buscarParcelasPagar(clickList.getId(), getApplicationContext());
		
		if (dataPagamento.getText().length() != 10) {
			Toast.makeText(getApplicationContext(), "Preencha a data do pagamento corretamente no formato dd/MM/aaaa!", Toast.LENGTH_LONG).show();
		} else if (Date.parse(dataPagamento.getText().toString()) < Date.parse(parcelasPagar.getDataCompra())) { 
			Toast.makeText(getApplicationContext(), "A data do pagamento não pode ser menor que a data de compra!", Toast.LENGTH_LONG).show();
		} else {
			parcelasPagar.setDataPagamento(dataPagamento.getText().toString());
			repositorio.salvar(parcelasPagar);
			alerta.cancel();
			Toast.makeText(getApplicationContext(), "Data atualizada!", Toast.LENGTH_LONG).show();
			atualizarLista();
		}
	}
	
	
}
