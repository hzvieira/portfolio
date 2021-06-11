package br.com.projetointegrador.adapters;

import java.util.List;

import br.com.projetointegrador.R;
import br.com.projetointegrador.entidades.PeriodoVendas;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class PeriodoListAdapter extends BaseAdapter implements ListAdapter {

	private Context context;
	private List<PeriodoVendas> lista;
	
	public PeriodoListAdapter(Context ctx, List<PeriodoVendas> lista) {
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
		PeriodoVendas c = lista.get(position);
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(R.layout.listadaptercinco, null);

		TextView campo1 = (TextView) view.findViewById(R.id.campo1);
		campo1.setText(c.getFornecedor().getNome());
		
		TextView campo2 = (TextView) view.findViewById(R.id.campo2);
		campo2.setText(c.getDataInicial());
		
		TextView campo3 = (TextView) view.findViewById(R.id.campo3);
		campo3.setText(c.getDataFinal());
		
		TextView campo4 = (TextView) view.findViewById(R.id.campo4);
		campo4.setText(c.isPedidoFeito() == true ? "Sim" : "Não");
		
		TextView campo5 = (TextView) view.findViewById(R.id.campo5);
		campo5.setText(c.isRecebeuEncomenda() == true ? "Sim" : "Não");
		
		return view;
	}

}
