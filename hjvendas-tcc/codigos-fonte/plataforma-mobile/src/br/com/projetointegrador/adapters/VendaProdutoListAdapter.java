package br.com.projetointegrador.adapters;

import java.util.List;

import br.com.projetointegrador.R;
import br.com.projetointegrador.entidades.ItemPedido;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class VendaProdutoListAdapter extends BaseAdapter implements ListAdapter {

	private Context context;
	private List<ItemPedido> lista;
	
	public VendaProdutoListAdapter(Context ctx, List<ItemPedido> lista) {
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
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(R.layout.listadapter, null);

		TextView campo1 = (TextView) view.findViewById(R.id.campo1);
		campo1.setText(c.getProduto().getNome());
		TextView campo2 = (TextView) view.findViewById(R.id.campo2);
		campo2.setText(String.valueOf(c.getQuantidade()));
		TextView campo3 = (TextView) view.findViewById(R.id.campo3);
		campo3.setText(c.isEntregue() ? "Sim" : "Não");
		return view;
	}

}
