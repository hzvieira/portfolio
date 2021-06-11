package br.com.projetointegrador.cadastros;

import br.com.projetointegrador.R;
import br.com.projetointegrador.controles.ControleItemPedido;
import br.com.projetointegrador.controles.ControleProduto;
import br.com.projetointegrador.entidades.ItemPedido;
import br.com.projetointegrador.entidades.Produto;
import br.com.projetointegrador.listas.ListaProduto;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CadastroItemPedido extends Activity {
	
	private ItemPedido itemPedido = new ItemPedido();
	private int BUSCAR_PRODUTO = 1;
	private EditText campoNomeProduto;
	private EditText campoQuantidade;
	private EditText campoValor;
	private Spinner boxEntregue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frmitemvenda);

		popularEntregue();
		
		campoQuantidade = (EditText) findViewById(br.com.projetointegrador.R.id.campoQuantidade);
		campoNomeProduto = (EditText) findViewById(br.com.projetointegrador.R.id.campoNome);
		campoValor = (EditText) findViewById(br.com.projetointegrador.R.id.campoValor);
	}

	private void popularEntregue() {
		boxEntregue = (Spinner) findViewById(R.id.boxEntrega);
		boxEntregue.setAdapter(new ArrayAdapter<String>(getApplicationContext(),  android.R.layout.simple_spinner_item, new String[] {"Não", "Sim"}));
	}
	
	public void cancelar(View v){
		itemPedido = null;
		finish();
	}
	
	public void selecionarProduto (View v){
		startActivityForResult(new Intent(this, ListaProduto.class), BUSCAR_PRODUTO);
	}
	
	public void salvar(View v) {

		if (campoQuantidade.getText().toString().equals("0")) {
			Toast.makeText(getApplicationContext(), "A quantidade não pode ser zero", Toast.LENGTH_LONG).show();
		} else if (campoQuantidade.getText().toString().equals("")) {
			Toast.makeText(getApplicationContext(), "A quantidade deve ser preenchida", Toast.LENGTH_LONG).show();
		} else if ((itemPedido.getProduto() == null)  || (!this.itemPedido.getProduto().getNome().equals(campoNomeProduto.getText().toString()))){
			Toast.makeText(getApplicationContext(), "Selecione corretamente o produto", Toast.LENGTH_LONG).show();
		} else if (boxEntregue.getSelectedItemPosition() == 1) {
			if (!validaQuantidade(itemPedido.getProduto().getId(), Long.parseLong(campoQuantidade.getText().toString()))) {
				Toast.makeText(getApplicationContext(), "Quantidade indisponível para pronta entrega!", Toast.LENGTH_LONG).show();
			}
		} else {
			setResult(RESULT_OK, new Intent().putExtra("Item", new long[] {
					 	itemPedido.getProduto().getId(), 
					 	Long.parseLong(campoQuantidade.getText().toString()), 
					 	boxEntregue.getSelectedItemId() 
					 }));
			finish();
		}
	}
	
	private boolean validaQuantidade(Long id, long quantidade) {
		return new ControleItemPedido(getApplicationContext()).validaQuantidade(id, quantidade);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		if (itemPedido.getProduto() != null) {
			campoNomeProduto.setText(itemPedido.getProduto().getNome());
			campoValor.setText(itemPedido.getProduto().getValor().toString());
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == BUSCAR_PRODUTO) {
			if (resultCode == RESULT_OK) {
				itemPedido.setProduto(new ControleProduto(getApplicationContext()).buscarProduto(data.getLongExtra(Produto.colunas[0], 0), getApplicationContext()));
			}
		}
	}
}
