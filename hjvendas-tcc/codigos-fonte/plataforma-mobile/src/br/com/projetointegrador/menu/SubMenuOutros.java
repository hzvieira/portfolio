package br.com.projetointegrador.menu;

import br.com.projetointegrador.listas.ListaCidade;
import br.com.projetointegrador.listas.ListaCorProduto;
import br.com.projetointegrador.listas.ListaDevolucao;
import br.com.projetointegrador.listas.ListaFornecedor;
import br.com.projetointegrador.listas.ListaGeneroProduto;
import br.com.projetointegrador.listas.ListaParteDoCorpo;
import br.com.projetointegrador.listas.ListaTipoContatoAgenda;
import br.com.projetointegrador.listas.ListaTipoProduto;
import br.com.projetointegrador.listas.ListaTipoRelacionamento;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SubMenuOutros extends ListActivity {
	private static final String[] nomes = new String[] { 
		"Cidade",
		"Evento",
		"Fornecedor",
		"Produto/Devolução",
		"Produto/Área corporal",
		"Produto/Tipo",
		"Produto/Cor",
		"Produto/Genero",
		"Tipo de contato da agenda",
		"Tipo de relacionamento",
		"Voltar"};		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, nomes));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		switch(position) {
		case 0:
			startActivity(new Intent(SubMenuOutros.this, ListaCidade.class));
			break;
		case 2:
			startActivity(new Intent(SubMenuOutros.this, ListaFornecedor.class));
			break;
		case 3:
			startActivity(new Intent(SubMenuOutros.this, ListaDevolucao.class));
			break;
		case 4:
			startActivity(new Intent(SubMenuOutros.this, ListaParteDoCorpo.class));
			break;
		case 5:
			startActivity(new Intent(SubMenuOutros.this, ListaTipoProduto.class));
			break;
		case 6:
			startActivity(new Intent(SubMenuOutros.this, ListaCorProduto.class));
			break;
		case 7:
			startActivity(new Intent(SubMenuOutros.this, ListaGeneroProduto.class));
			break;
		case 8:
			startActivity(new Intent(SubMenuOutros.this, ListaTipoContatoAgenda.class));
			break;
		case 9:
			startActivity(new Intent(SubMenuOutros.this, ListaTipoRelacionamento.class));
			break;
		default:
			finish();
			break;
		}
	}
}
