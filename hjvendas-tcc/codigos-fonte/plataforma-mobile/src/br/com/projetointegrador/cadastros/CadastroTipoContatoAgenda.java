package br.com.projetointegrador.cadastros;

import br.com.projetointegrador.R;
import br.com.projetointegrador.entidades.TipoContatoAgenda;
import br.com.projetointegrador.listas.ListaTipoContatoAgenda;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CadastroTipoContatoAgenda extends Activity {
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
			id = extras.getLong(TipoContatoAgenda.colunas[0]);
			if (id != null) {
				TipoContatoAgenda c = buscarTipoContatoAgenda(id);
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
		TipoContatoAgenda tipoContatoAgenda = new TipoContatoAgenda();
		if (id != null) {
			tipoContatoAgenda.setId(id);
		}
		tipoContatoAgenda.setNome(campoNome.getText().toString());
		salvarTipoContatoAgenda(tipoContatoAgenda);
		setResult(RESULT_OK, new Intent());
		finish();
	}
	
	public void cancelar(View v) {
		onPause();
		CadastroTipoContatoAgenda.this.finish();
	}

	private TipoContatoAgenda buscarTipoContatoAgenda(long id) {
		return ListaTipoContatoAgenda.repositorio.buscarTipoContatoAgenda(id);
	}

	private void salvarTipoContatoAgenda(TipoContatoAgenda tipoContatoAgenda) {
		ListaTipoContatoAgenda.repositorio.salvar(tipoContatoAgenda);
	}

}
