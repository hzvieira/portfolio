package br.com.projetointegrador.cadastros;

import java.util.Date;

import br.com.projetointegrador.R;
import br.com.projetointegrador.controles.ControleFornecedor;
import br.com.projetointegrador.controles.ControleParcelasPagar;
import br.com.projetointegrador.entidades.Fornecedor;
import br.com.projetointegrador.entidades.ParcelasPagar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CadastroParcelasPagar extends Activity {
	private EditText campoCompra;
	private EditText campoVencimento;
	private EditText campoPagamento;
	private EditText campoValor;
	private Spinner boxFornecedor;
	private Long id;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.frmcadcontaspagar);

		campoCompra = (EditText) findViewById(R.id.campoDataCompra);
		campoVencimento = (EditText) findViewById(R.id.campoDataVencimento);
		campoPagamento = (EditText) findViewById(R.id.campoDataPagamento);
		campoValor = (EditText) findViewById(R.id.campoValor);
		boxFornecedor = (Spinner) findViewById(R.id.boxFornecedor);
		
		popularFornecedor();
		
		id = null;

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			id = extras.getLong(ParcelasPagar.colunas[0]);
			if (id != null) {
				ParcelasPagar c = buscarParcelasPagar(id);
				campoCompra.setText(c.getDataCompra());
				campoVencimento.setText(c.getDataVencimento());
				campoPagamento.setText(c.getDataPagamento());
				campoValor.setText(String.valueOf(c.getValor()));
				boxFornecedor.setSelection(selecionadoFornecedor(c.getFornecedor().getId()));
			}
		}
	}

	private void popularFornecedor() {
		boxFornecedor.setAdapter(new ArrayAdapter<Fornecedor>(getApplicationContext(), android.R.layout.simple_spinner_item, new ControleFornecedor(getApplicationContext()).listarFornecedor()));
	}

	private int selecionadoFornecedor(long id2) {
		int pos = 0;
		for (Fornecedor posicao : new ControleFornecedor(getApplicationContext()).listarFornecedor()) {	
			if (posicao.getId() == id2)
				return pos;
			else
				pos++;
		}
		return -1;
	}

	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
	}

	public void salvar(View v) {
		ParcelasPagar parcelasPagar = new ParcelasPagar();
		if (id != null) {
			parcelasPagar.setId(id);
		}
		
		Log.i("Cadastro", "Salvando");
		parcelasPagar.setDataCompra(campoCompra.getText().toString());
		parcelasPagar.setDataVencimento(campoVencimento.getText().toString());
		parcelasPagar.setDataPagamento(campoPagamento.getText().toString());
		parcelasPagar.setValor(Double.parseDouble(campoValor.getText().toString()));
		parcelasPagar.setFornecedor((Fornecedor) boxFornecedor.getSelectedItem());
		
		Log.i("Cadastro", "Salvando depois de inserir dados");
		
		if (parcelasPagar.getDataCompra().length() != 10) {
			Toast.makeText(getApplicationContext(), "Preencha a data da compra corretamente no formato dd/MM/aaaa!", Toast.LENGTH_LONG).show();
		} else if (parcelasPagar.getDataVencimento().length() != 10) {
			Toast.makeText(getApplicationContext(), "Preencha a data do vencimento corretamente no formato dd/MM/aaaa!", Toast.LENGTH_LONG).show();			
		} else if (parcelasPagar.getValor() <= 0) {
			Toast.makeText(getApplicationContext(), "Preencha um valor maior que 0", Toast.LENGTH_LONG).show();			
		} else if (Date.parse(parcelasPagar.getDataVencimento()) < Date.parse(parcelasPagar.getDataCompra())) {
			Toast.makeText(getApplicationContext(), "Data do vencimento não pode ser menor que a data de compra", Toast.LENGTH_LONG).show();
		} else {
			salvarParcelasPagar(parcelasPagar);
			setResult(RESULT_OK, new Intent());
			finish();
		}
	}
	
	public void cancelar(View v) {
		onPause();
		CadastroParcelasPagar.this.finish();
	}

	private ParcelasPagar buscarParcelasPagar(long id) {
		return new ControleParcelasPagar(getApplicationContext()).buscarParcelasPagar(id, getApplicationContext());
	}

	private void salvarParcelasPagar(ParcelasPagar parcelasPagar) {
		new ControleParcelasPagar(getApplicationContext()).salvar(parcelasPagar);
	}

}
