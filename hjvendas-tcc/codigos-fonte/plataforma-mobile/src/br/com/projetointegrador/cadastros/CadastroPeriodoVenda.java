package br.com.projetointegrador.cadastros;

import java.sql.Date;

import br.com.projetointegrador.R;
import br.com.projetointegrador.controles.ControleFornecedor;
import br.com.projetointegrador.controles.ControlePeriodoVendas;
import br.com.projetointegrador.entidades.Fornecedor;
import br.com.projetointegrador.entidades.PeriodoVendas;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CadastroPeriodoVenda extends Activity {
	private EditText campoInicio;
	private EditText campoFim;
	private Spinner boxFornecedor;
	private Long id;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.frmcadperiodovenda);

		campoInicio = (EditText) findViewById(R.id.campoDataInicio);
		campoFim = (EditText) findViewById(R.id.campoDataFim);
		boxFornecedor = (Spinner) findViewById(R.id.boxFornecedor);
		
		popularFornecedor();
		
		id = null;

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			id = extras.getLong(PeriodoVendas.colunas[0]);
			if (id != null) {
				PeriodoVendas c = buscarPeriodoVendas(id);
				campoInicio.setText(c.getDataInicial());
				campoFim.setText(c.getDataFinal());
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
		PeriodoVendas periodoVendas = new PeriodoVendas();
		if (id != null) {
			periodoVendas.setId(id);
		}

		periodoVendas.setDataInicial(campoInicio.getText().toString());
		periodoVendas.setDataFinal(campoFim.getText().toString());
		periodoVendas.setFornecedor((Fornecedor) boxFornecedor.getSelectedItem());
		long dataFinal = Date.parse(periodoVendas.getDataFinal());
		long dataInicial = Date.parse(periodoVendas.getDataInicial());
		
		Log.i("DAtas", "inicial : " + dataInicial);
		Log.i("DAtas", "Final   : " + dataFinal);
		
		
		if (periodoVendas.getDataInicial().length() != 10) {
			Toast.makeText(getApplicationContext(), "Preencha a data de inicio corretamente no formato dd/MM/aaaa!", Toast.LENGTH_LONG).show();
		} else if (periodoVendas.getDataFinal().length() != 10) {
			Toast.makeText(getApplicationContext(), "Preencha a data final corretamente no formato dd/MM/aaaa!", Toast.LENGTH_LONG).show();			
		} else if (Date.parse(periodoVendas.getDataFinal()) < Date.parse(periodoVendas.getDataInicial())) {
			Toast.makeText(getApplicationContext(), "Data final não pode ser menor que a data de inicial", Toast.LENGTH_LONG).show();
//		} else if (new ControlePeriodoVendas(getApplicationContext()).verificaPeriodo(periodoVendas.getDataInicial(), (Fornecedor) boxFornecedor.getSelectedItem())) {
//			Toast.makeText(getApplicationContext(), "Não é possível por que a data inicial não pode ser maior que uma data final já existente", Toast.LENGTH_LONG).show();
		} else if (periodoVendas.isPedidoFeito()) {
			Toast.makeText(getApplicationContext(), "Não é possível realizar a alteração se o pedido já foi feito", Toast.LENGTH_LONG).show();
		} else	{
			salvarPeriodoVendas(periodoVendas);
			setResult(RESULT_OK, new Intent());
			finish();
		}
	}
	
	public void cancelar(View v) {
		onPause();
		CadastroPeriodoVenda.this.finish();
	}

	private PeriodoVendas buscarPeriodoVendas(long id) {
		return new ControlePeriodoVendas(getApplicationContext()).buscarPeriodoVendas(id, getApplicationContext());
	}

	private void salvarPeriodoVendas(PeriodoVendas periodoVendas) {
		new ControlePeriodoVendas(getApplicationContext()).salvar(periodoVendas);
	}

}
