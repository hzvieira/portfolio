package br.com.projetointegrador.cadastros;

import br.com.projetointegrador.R;
import br.com.projetointegrador.entidades.Cidade;
import br.com.projetointegrador.listas.ListaCidade;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CadastroCidade extends Activity {
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
			id = extras.getLong(Cidade.colunas[0]);
			if (id != null) {
				Cidade c = buscarCidade(id);
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
		Cidade cidade = new Cidade();
		if (id != null) {
			cidade.setId(id);
		}
		cidade.setNome(campoNome.getText().toString());
		salvarCidade(cidade);
		setResult(RESULT_OK, new Intent());
		finish();
	}
	
	public void cancelar(View v) {
		onPause();
		CadastroCidade.this.finish();
	}

	private Cidade buscarCidade(long id) {
		return ListaCidade.repositorio.buscarCidade(id);
	}

	private void salvarCidade(Cidade cidade) {
		ListaCidade.repositorio.salvar(cidade);
	}

}
