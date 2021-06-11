package br.com.projetointegrador.cadastros;

import br.com.projetointegrador.R;
import br.com.projetointegrador.entidades.ParteDoCorpo;
import br.com.projetointegrador.listas.ListaParteDoCorpo;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CadastroParteDoCorpo extends Activity {
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
			id = extras.getLong(ParteDoCorpo.colunas[0]);
			if (id != null) {
				ParteDoCorpo c = buscarParteDoCorpo(id);
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
		ParteDoCorpo parteDoCorpo = new ParteDoCorpo();
		if (id != null) {
			parteDoCorpo.setId(id);
		}
		parteDoCorpo.setNome(campoNome.getText().toString());
		salvarParteDoCorpo(parteDoCorpo);
		setResult(RESULT_OK, new Intent());
		finish();
	}
	
	public void cancelar(View v) {
		onPause();
		CadastroParteDoCorpo.this.finish();
	}

	private ParteDoCorpo buscarParteDoCorpo(long id) {
		return ListaParteDoCorpo.repositorio.buscarParteDoCorpo(id);
	}

	private void salvarParteDoCorpo(ParteDoCorpo parteDoCorpo) {
		ListaParteDoCorpo.repositorio.salvar(parteDoCorpo);
	}

}
