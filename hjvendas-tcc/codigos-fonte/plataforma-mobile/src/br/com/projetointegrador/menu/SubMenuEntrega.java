package br.com.projetointegrador.menu;

import br.com.projetointegrador.listas.ListaEntregas;
import br.com.projetointegrador.listas.ListaProntaEntrega;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SubMenuEntrega extends ListActivity {
	private static final String[] nomes = new String[] { 
		"Pronta Entrega",
		"Entregas",
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
			startActivity(new Intent(SubMenuEntrega.this, ListaProntaEntrega.class));
			break;
		case 1:
			startActivity(new Intent(SubMenuEntrega.this, ListaEntregas.class));
			break;
		default:
			finish();
			break;
		}
	}
}
