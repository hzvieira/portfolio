package br.com.projetointegrador.cadastros;

import br.com.projetointegrador.R;
import br.com.projetointegrador.entidades.TipoRelacionamento;
import br.com.projetointegrador.listas.ListaTipoRelacionamento;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CadastroTipoRelacionamento extends Activity {
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
			id = extras.getLong(TipoRelacionamento.colunas[0]);
			if (id != null) {
				TipoRelacionamento c = buscarTipoRelacionamento(id);
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
		TipoRelacionamento tipoRelacionamento = new TipoRelacionamento();
		if (id != null) {
			tipoRelacionamento.setId(id);
		}
		tipoRelacionamento.setNome(campoNome.getText().toString());
		salvarTipoRelacionamento(tipoRelacionamento);
		setResult(RESULT_OK, new Intent());
		finish();
	}
	
	public void cancelar(View v) {
		onPause();
		CadastroTipoRelacionamento.this.finish();
	}

	private TipoRelacionamento buscarTipoRelacionamento(long id) {
		return ListaTipoRelacionamento.repositorio.buscarTipoRelacionamento(id);
	}

	private void salvarTipoRelacionamento(TipoRelacionamento tipoRelacionamento) {
		ListaTipoRelacionamento.repositorio.salvar(tipoRelacionamento);
	}

}
