package br.com.projetointegrador.adapters;

import java.util.List;

import br.com.projetointegrador.R;
import br.com.projetointegrador.controles.ControleVenda;
import br.com.projetointegrador.entidades.Cliente;
import br.com.projetointegrador.entidades.ItemPedido;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class EntregaListAdapter extends BaseAdapter implements ListAdapter {

	private Context context;
	private List<ItemPedido> lista;
	
	public EntregaListAdapter(Context ctx, List<ItemPedido> lista) {
		this.context = ctx;
		this.lista = lista;
	}
	
	public int getCount() {
		return lista.size();
	}

	public Object getItem(int position) {
		return lista.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ItemPedido c = lista.get(position);
		Cliente cli = new ControleVenda(context).buscarPedido(c.getPedido().getId(), context).getCliente();
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(R.layout.listadaptercinco, null);

		TextView campo1 = (TextView) view.findViewById(R.id.campo1);
		campo1.setText(cli.getNome());
		TextView campo2 = (TextView) view.findViewById(R.id.campo2);
		campo2.setText(cli.getTelefoneCelular());
		TextView campo3 = (TextView) view.findViewById(R.id.campo3);
		campo3.setText(c.getProduto().getNome());
		TextView campo4 = (TextView) view.findViewById(R.id.campo4);
		campo4.setText(String.valueOf(c.getQuantidade()));
		TextView campo5 = (TextView) view.findViewById(R.id.campo5);
		campo5.setText(c.isEntregue() ? "Sim" : "Não");
		
		
		return view;
	}

}
