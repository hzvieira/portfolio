package br.com.projetointegrador.cadastros;

import br.com.projetointegrador.R;
import br.com.projetointegrador.entidades.TipoProduto;
import br.com.projetointegrador.listas.ListaTipoProduto;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CadastroTipoProduto extends Activity {
	private EditText campoNome;
	private Long id;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.frmcadastrosimples);

		campoNome = (EditText) findViewById(R.id.campoNome);
		id = null;

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			id = extras.getLong(TipoProduto.colunas[0]);
			if (id != null) {
				TipoProduto c = buscarTipoProduto(id);
				campoNome.setText(c.getNome());
			}
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
	}

	public void salvar(View v) {
		TipoProduto tipoProduto = new TipoProduto();
		if (id != null) {
			tipoProduto.setId(id);
		}
		tipoProduto.setNome(campoNome.getText().toString());
		salvarTipoProduto(tipoProduto);
		setResult(RESULT_OK, new Intent());
		finish();
	}
	
	public void cancelar(View v) {
		onPause();
		CadastroTipoProduto.this.finish();
	}

	private TipoProduto buscarTipoProduto(long id) {
		return ListaTipoProduto.repositorio.buscarTipoProduto(id);
	}

	private void salvarTipoProduto(TipoProduto tipoProduto) {
		ListaTipoProduto.repositorio.salvar(tipoProduto);
	}

}
