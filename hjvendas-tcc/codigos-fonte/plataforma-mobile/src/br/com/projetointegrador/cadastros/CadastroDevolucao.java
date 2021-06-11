package br.com.projetointegrador.cadastros;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.projetointegrador.R;
import br.com.projetointegrador.controles.ControleCliente;
import br.com.projetointegrador.controles.ControleDevolucao;
import br.com.projetointegrador.controles.ControleProduto;
import br.com.projetointegrador.entidades.Cliente;
import br.com.projetointegrador.entidades.Devolucao;
import br.com.projetointegrador.entidades.Produto;
import br.com.projetointegrador.listas.ListaCliente;
import br.com.projetointegrador.listas.ListaProduto;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroDevolucao extends Activity {
	private EditText campoCliente;
	private EditText campoProduto;
	private EditText campoData;
	private Devolucao dev;
	
	public int BUSCAR_PRODUTO = 1;
	public int BUSCAR_CLIENTE = 2;
	//private Long id;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.frmcaddevolucao);

		campoCliente = (EditText) findViewById(R.id.campoNome);
		campoProduto = (EditText) findViewById(R.id.campoProduto);
		campoData = (EditText) findViewById(R.id.campoData);
		
		campoData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		
		dev = new Devolucao();
	}

	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		if (dev.getProduto() != null) {
			campoProduto.setText(dev.getProduto().getNome());
		} 
		
		if (dev.getCliente() != null) {
			campoCliente.setText(dev.getCliente().getNome());
		}
	}

	public void salvar(View v) {

		if ((dev.getProduto() == null) || (!dev.getProduto().getNome().equals(campoProduto.getText().toString())) ) {
			Toast.makeText(getApplicationContext(), "Selecione corretamente o produto", Toast.LENGTH_LONG).show();
		} else if ((dev.getCliente() == null) || (!dev.getCliente().getNome().equals(campoCliente.getText().toString()))) {
			Toast.makeText(getApplicationContext(), "Selecione corretamente o cliente", Toast.LENGTH_LONG).show();
		} else if (campoData.getText().length() != 10) {
			Toast.makeText(this, "Preencha a data no formato dd/MM/aaaa!", Toast.LENGTH_LONG).show();
		} else if (!verificaDevolucao(dev.getCliente().getId(), dev.getProduto().getId())) {
			Toast.makeText(this, "Cliente não comprou/recebeu esse produto para ser devolvido!", Toast.LENGTH_LONG).show();
		} else {
		
			salvarDevolucao(dev);
			setResult(RESULT_OK, new Intent());
			finish();	
		}
	}
	
	private boolean verificaDevolucao(long id, Long id2) {
		return new ControleDevolucao(getApplicationContext()).verificaDevolucao(id, id2);
	}

	public void cancelar(View v) {
		onPause();
		CadastroDevolucao.this.finish();
	}
	
	private void salvarDevolucao(Devolucao dev) {
		new ControleDevolucao(getApplicationContext()).salvar(dev);
	}
	
	public void buscarCliente(View v){
		startActivityForResult(new Intent(this, ListaCliente.class), BUSCAR_CLIENTE);
	}
	
	public void buscarProduto(View v){
		startActivityForResult(new Intent(this, ListaProduto.class), BUSCAR_PRODUTO);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == BUSCAR_CLIENTE) {
			if (resultCode == RESULT_OK) {
				dev.setCliente(new ControleCliente(getApplicationContext()).buscarCliente(data.getLongExtra(Cliente.colunas[0], 0), getApplicationContext()));
			}
		} else if (requestCode == BUSCAR_PRODUTO) {
			if (resultCode == RESULT_OK) {
				dev.setProduto(new ControleProduto(getApplicationContext()).buscarProduto(data.getLongExtra(Produto.colunas[0], 0), getApplicationContext()));
			}
		}
	}
}

