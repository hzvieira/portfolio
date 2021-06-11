package br.com.projetointegrador.cadastros;

import br.com.projetointegrador.R;
import br.com.projetointegrador.entidades.CorProduto;
import br.com.projetointegrador.listas.ListaCorProduto;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CadastroCorProduto extends Activity {
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
			id = extras.getLong(CorProduto.colunas[0]);
			if (id != null) {
				CorProduto c = buscarCorProduto(id);
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
		CorProduto corProduto = new CorProduto();
		if (id != null) {
			corProduto.setId(id);
		}
		corProduto.setNome(campoNome.getText().toString());
		salvarCorProduto(corProduto);
		setResult(RESULT_OK, new Intent());
		finish();
	}
	
	public void cancelar(View v) {
		onPause();
		CadastroCorProduto.this.finish();
	}

	private CorProduto buscarCorProduto(long id) {
		return ListaCorProduto.repositorio.buscarCorProduto(id);
	}

	private void salvarCorProduto(CorProduto corProduto) {
		ListaCorProduto.repositorio.salvar(corProduto);
	}

}
