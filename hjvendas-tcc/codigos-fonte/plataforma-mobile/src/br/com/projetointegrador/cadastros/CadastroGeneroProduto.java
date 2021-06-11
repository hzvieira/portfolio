package br.com.projetointegrador.cadastros;

import br.com.projetointegrador.R;
import br.com.projetointegrador.entidades.GeneroProduto;
import br.com.projetointegrador.listas.ListaGeneroProduto;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CadastroGeneroProduto extends Activity {
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
			id = extras.getLong(GeneroProduto.colunas[0]);
			if (id != null) {
				GeneroProduto c = buscarGeneroProduto(id);
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
		GeneroProduto generoProduto = new GeneroProduto();
		if (id != null) {
			generoProduto.setId(id);
		}
		generoProduto.setNome(campoNome.getText().toString());
		salvarGeneroProduto(generoProduto);
		setResult(RESULT_OK, new Intent());
		finish();
	}
	
	public void cancelar(View v) {
		onPause();
		CadastroGeneroProduto.this.finish();
	}

	private GeneroProduto buscarGeneroProduto(long id) {
		return ListaGeneroProduto.repositorio.buscarGeneroProduto(id);
	}

	private void salvarGeneroProduto(GeneroProduto generoProduto) {
		ListaGeneroProduto.repositorio.salvar(generoProduto);
	}

}
