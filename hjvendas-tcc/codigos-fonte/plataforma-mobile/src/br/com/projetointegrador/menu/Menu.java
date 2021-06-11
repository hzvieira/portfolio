package br.com.projetointegrador.menu;

import br.com.projetointegrador.listas.ListaCliente;
import br.com.projetointegrador.listas.ListaParcelasPagar;
import br.com.projetointegrador.listas.ListaPedido;
import br.com.projetointegrador.listas.ListaPeriodoVenda;
import br.com.projetointegrador.listas.ListaProduto;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {
	
	private static final String[] nomes = new String[] { 
		"Agenda" , 
		"Contas a pagar", 
		"Manutenção de cliente", 
		"Manutenção de produto", 
		"Entrega",
		"Pedido final fornecedor",
		"Venda",
		"Outros", 
		"Sair" 
	};		
		
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
			ComponentName cn = new ComponentName("com.android.calendar", "com.android.calendar.LaunchActivity");
			startActivity(new Intent().setComponent(cn));
			break; 
		case 1:
			startActivity(new Intent(Menu.this, ListaParcelasPagar.class));
			break;
		case 2:
			startActivity(new Intent(Menu.this, ListaCliente.class));
			break;
		case 3:
			startActivity(new Intent(Menu.this, ListaProduto.class));
			break;
		case 4:
			startActivity(new Intent(Menu.this, SubMenuEntrega.class));
			break;
		case 5:
			startActivity(new Intent(Menu.this, ListaPeriodoVenda.class));
			break;			
		case 6:
			startActivity(new Intent(Menu.this, ListaPedido.class));
			break;
		case 7:
			startActivity(new Intent(Menu.this, SubMenuOutros.class));
			break;
		default:
			finish();
			break;
		}
	}
	
}
