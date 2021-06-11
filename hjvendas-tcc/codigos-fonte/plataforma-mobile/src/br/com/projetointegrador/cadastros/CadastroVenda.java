package br.com.projetointegrador.cadastros;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.projetointegrador.R;
import br.com.projetointegrador.adapters.VendaProdutoListAdapter;
import br.com.projetointegrador.controles.ControleCliente;
import br.com.projetointegrador.controles.ControleProduto;
import br.com.projetointegrador.controles.ControleVenda;
import br.com.projetointegrador.entidades.Cliente;
import br.com.projetointegrador.entidades.ItemPedido;
import br.com.projetointegrador.entidades.Pedido;
import br.com.projetointegrador.listas.ListaCliente;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CadastroVenda extends Activity {
	
	private Cliente pessoa = new Cliente();
	private List<ItemPedido> itens = new ArrayList<ItemPedido>();
	private int BUSCAR_CLIENTE = 1;
	private int BUSCAR_ITEM = 2;
	
	private EditText campoNome;
	private EditText campoData;
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frmvenda);
		
		campoNome = (EditText) findViewById(br.com.projetointegrador.R.id.campoNome);
		campoData = (EditText) findViewById(br.com.projetointegrador.R.id.campoData);
		campoData.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		
	}
	
	public void buscarPessoa(View v){
		startActivityForResult(new Intent(getApplicationContext(), ListaCliente.class), BUSCAR_CLIENTE);
	}
	
	public void maisProduto(View v) {
		startActivityForResult(new Intent(this, CadastroItemPedido.class), BUSCAR_ITEM);		
	}

	public void cancelar(View v) {
		pessoa = null;
		itens = null;
		finish();
	}
	
	public void salvar(View v){
		if ((this.pessoa.getNome() == null) || (!this.pessoa.getNome().toString().equals(campoNome.getText().toString()))) {
			Toast.makeText(this, "Selecione corretamente o cliente!", Toast.LENGTH_LONG).show();
		} else if (campoData.getText().toString().length() != 10) {
			Toast.makeText(this, "Preencha a data no formato dd/MM/aaaa!", Toast.LENGTH_LONG).show();
		} else if (this.itens.size() == 0) {
			Toast.makeText(this, "Selecione um produto para venda", Toast.LENGTH_LONG).show();		
		} else {
			Pedido venda = new Pedido();
			venda.setCliente(pessoa);
			venda.setData(campoData.getText().toString());
			salvarVenda(venda, itens);
			setResult(RESULT_OK, new Intent());
			finish();	
		}
	}
	
	private void salvarVenda(Pedido venda, List<ItemPedido> itens) {
		new ControleVenda(getApplicationContext()).salvar(venda, itens);
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		if (!(pessoa.getNome() == null)) {
			campoNome.setText(pessoa.getNome());
		}
		if (!this.itens.equals(null)) {
			verificaDuplicados();
			listView = (ListView) findViewById(br.com.projetointegrador.R.id.lista);
			listView.setAdapter(new VendaProdutoListAdapter(getApplicationContext(), itens));
		}
	}
	
    private void verificaDuplicados() {
        List<ItemPedido> aExcluir = new ArrayList<ItemPedido>();
        for (int i = 0; i < itens.size(); i++) {
            for (int j = i + 1; j < itens.size(); j++) {
            	ItemPedido itemAtual = itens.get(i);
            	ItemPedido itemDuplicado = itens.get(j);
                if (itemAtual.getProduto().getId() == itemDuplicado.getProduto().getId()) {
                    itemDuplicado.setId(j);
                    aExcluir.add(itemDuplicado);
                    itemAtual.adicionaQuantidade(itemDuplicado);
                }
            }
        }
        for (ItemPedido itemDuplicado : aExcluir) {
            itens.remove(itemDuplicado);
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       	super.onActivityResult(requestCode, resultCode, data);
       	if (requestCode == BUSCAR_CLIENTE) {
       		if (resultCode == RESULT_OK) {
       			pessoa = new ControleCliente(getApplicationContext()).buscarCliente(data.getLongExtra(Cliente.colunas[0], 0), getApplicationContext());
       		}
      	} else if (requestCode == BUSCAR_ITEM) {
       		if (resultCode == RESULT_OK) {
       			long[] dados = data.getLongArrayExtra("Item");
       			ItemPedido item = new ItemPedido();

       			item.setProduto(new ControleProduto(getApplicationContext()).buscarProduto(dados[0], getApplicationContext()));
       			item.setValor(new ControleProduto(getApplicationContext()).buscarProduto(dados[0], getApplicationContext()).getValor());
       			item.setQuantidade(dados[1]);
       			item.setEntregue(dados[2] == 0 ? true : false);
       			itens.add(item);
       		}
      	}
    }
}
