package br.com.projetointegrador.cadastros;

import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.R;
import br.com.projetointegrador.controles.ControleCidade;
import br.com.projetointegrador.entidades.Fornecedor;
import br.com.projetointegrador.entidades.Cidade;
import br.com.projetointegrador.listas.ListaFornecedor;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class CadastroFornecedor extends Activity {
	
	public static ControleCidade repositorio;
	private EditText campoNome;
	private EditText campoTelefoneRes;
	private EditText campoTelefoneCel;
	private EditText campoEmail01;
	private EditText campoEmail02;
	private EditText campoRua;
	private EditText campoNumero;
	private EditText campoComplemento;
	private EditText campoBairro;
	private EditText campoCep;
	private EditText campoCNPJ;
	private EditText campoObservacao;
	private Spinner campoCidade;
	private List<Cidade> cidades = new ArrayList<Cidade>();
	
	private Long id;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.frmcadfornecedor);

		campoNome = (EditText) findViewById(R.id.campoNome);
		campoTelefoneRes = (EditText) findViewById(R.id.campoTelefone1);
		campoTelefoneCel = (EditText) findViewById(R.id.campoTelefone2);
		campoEmail01 = (EditText) findViewById(R.id.campoEmail1);
		campoEmail02 = (EditText) findViewById(R.id.campoEmail2);
		campoRua = (EditText) findViewById(R.id.campoEndereco);
		campoNumero = (EditText) findViewById(R.id.campoNumero);
		campoComplemento = (EditText) findViewById(R.id.campoComplemento);
		campoBairro = (EditText) findViewById(R.id.campoBairro);
		campoCep = (EditText) findViewById(R.id.campoCep);
		campoCNPJ = (EditText) findViewById(R.id.campoCNPJ);
		campoObservacao = (EditText) findViewById(R.id.campoObservacoes);
		campoCidade = (Spinner) findViewById(R.id.boxCidade);
		
		popularCidade();
		
		campoCidade.setAdapter(new ArrayAdapter<Cidade>(CadastroFornecedor.this,  android.R.layout.simple_spinner_item, cidades));
		
		id = null;

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			id = extras.getLong(Fornecedor.colunas[0]);
			if (id != null) {
				Fornecedor c = buscarFornecedor(id);
				campoNome.setText(c.getNome());
				campoTelefoneRes.setText(c.getTelefoneResidencial());
				campoTelefoneCel.setText(c.getTelefoneCelular());
				campoEmail01.setText(c.getEmail01());
				campoEmail02.setText(c.getEmail02());
				campoRua.setText(c.getRua());
				campoNumero.setText(String.valueOf(c.getNumero()));
				campoComplemento.setText(c.getComplemento());
				campoBairro.setText(c.getBairro());
				campoCep.setText(c.getCep());
				campoCNPJ.setText(c.getCnpj());
				Log.i("ALTERAR FORNECEDOR", "" + c.getCidade().getId());
				Log.i("ALTERAR FORNECEDOR", "" + c.getCidade().getNome());
				campoCidade.setSelection((int) selecionado(c.getCidade().getId()));
				campoObservacao.setText(c.getObservacoes());
				
			}
		}
	}

	private int selecionado(long id2) {
		int pos = 0;
		for (Cidade posicao : cidades) {	
			if (posicao.getId() == id2)
				return pos;
			else
				pos++;
		}
		return -1;
	}

	private void popularCidade() {
		repositorio = new ControleCidade(this);
		cidades = repositorio.listarCidades();
	}

	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
	}

	public void salvar(View v) {
		Fornecedor fornecedor = new Fornecedor();
		if (id != null) {
			fornecedor.setId(id);
		}
		fornecedor.setNome(campoNome.getText().toString());
		fornecedor.setTelefoneResidencial(campoTelefoneRes.getText().toString());
		fornecedor.setTelefoneCelular(campoTelefoneCel.getText().toString());
		fornecedor.setEmail01(campoEmail01.getText().toString());
		fornecedor.setEmail02(campoEmail02.getText().toString());
		fornecedor.setRua(campoRua.getText().toString());
		fornecedor.setNumero(!campoNumero.getText().toString().trim().equals("") ? Long.parseLong(campoNumero.getText().toString()) : 0);
		fornecedor.setComplemento(campoComplemento.getText().toString());
		fornecedor.setBairro(campoBairro.getText().toString());
		fornecedor.setCep(campoCep.getText().toString());
		fornecedor.setCnpj(campoCNPJ.getText().toString());
		fornecedor.setCidade((Cidade) campoCidade.getSelectedItem());
		fornecedor.setObservacoes(campoObservacao.getText().toString());
		salvarFornecedor(fornecedor);
		setResult(RESULT_OK, new Intent());
		finish();
	}
	
	public void cancelar(View v) {
		onPause();
		CadastroFornecedor.this.finish();
	}

	private Fornecedor buscarFornecedor(long id) {
		return ListaFornecedor.repositorio.buscarFornecedor(id, getApplicationContext());
	}

	private void salvarFornecedor(Fornecedor fornecedor) {
		ListaFornecedor.repositorio.salvar(fornecedor);
		
	}

}
