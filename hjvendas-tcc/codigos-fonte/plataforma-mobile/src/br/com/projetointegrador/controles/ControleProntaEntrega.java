package br.com.projetointegrador.controles;

import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.entidades.ItemPedido;
import br.com.projetointegrador.entidades.Produto;
import br.com.projetointegrador.entidades.ProntaEntrega;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ControleProntaEntrega {

	private static final String NOME_BANCO = "HJVendas";
	private static final String NOME_TABELA = "prontaentrega";
	
	protected SQLiteDatabase db;
	
	public ControleProntaEntrega(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
		
	public List<ProntaEntrega> listarProntaEntrega() {
		Cursor c = getCursor();
		List<ProntaEntrega> lista = new ArrayList<ProntaEntrega>();
		if (c.moveToFirst()) {
			do {
				ProntaEntrega proximo = new ProntaEntrega();
				proximo.setId(c.getLong(0));
				proximo.setQuantidade(c.getLong(1));
				Produto produto = new Produto();
				produto.setId(c.getLong(2));
				proximo.setProduto(produto);
				lista.add(proximo);
			}while (c.moveToNext());
		}
		return lista;
	}
	
	public Cursor getCursor(){
		try {
			return db.query(NOME_TABELA, ProntaEntrega.colunas, null, null, null, null, null);
		} catch (SQLException e){
			Log.e("Controle cidade", "Erro ao buscar todas os produtos da pronta entrega, execeção: " + e.toString());
			return null;
		}
	}
	
	public List<ProntaEntrega> autoCompleta(CharSequence texto){
		List<ProntaEntrega> lista = new ArrayList<ProntaEntrega>();
		Cursor c = db.rawQuery(
				"select id, quantidade, produto_id " +
				"from ProntaEntrega " +
				"where produto_id = (" +
					"select id " +
					"from produto " +
					"where nome like '%" + texto + "a%')"
				, null);
		
		if (c.moveToFirst()) {
			do {
				ProntaEntrega proximo = new ProntaEntrega();
				proximo.setId(c.getLong(0));
				proximo.setQuantidade(c.getLong(1));
				Produto produto = new Produto();
				produto.setId(c.getLong(2));
				proximo.setProduto(produto);
				lista.add(proximo);
			}while (c.moveToNext());
		}
		return lista;
	}
	
	public void fechar() {
		if (db != null) {
			db.close();
		}
	}
	
	public List<ItemPedido> listarEntregas(Context context){
		List<ItemPedido> lista = new ArrayList<ItemPedido>();
		Cursor c = db.rawQuery(
				"select itenspedido.id from itenspedido, produto, fornecedor, periodo " +
				"WHERE itenspedido.produto_id = produto.id " +
				"AND produto.fornecedor_id = fornecedor.id " + 
				"AND fornecedor.id = periodo.fornecedor_id " +
				"AND periodo.recebeuencomenda = 1 " +
				"AND itenspedido.entregue = 0"	, null);
		
		if (c.moveToFirst()) {
			do {
				ItemPedido proximo = new ControleItemPedido(context).buscarItemPedido(c.getLong(0), context);
				lista.add(proximo);
			}while (c.moveToNext());
		}
		return lista;
	}
	
	public List<ItemPedido> autoCompletaEntregas(CharSequence texto, Context context){
		List<ItemPedido> lista = new ArrayList<ItemPedido>();
		Cursor c = db.rawQuery(
				"select id, produto_id " +
				"from ItensPedido " +
				"where produto_id = (" +
					"select id " +
					"from produto " +
					"where nome like '%" + texto + "a%')"
				, null);
		
		if (c.moveToFirst()) {
			do {
				ItemPedido proximo = new ControleItemPedido(context).buscarItemPedido(c.getLong(0), context);
				lista.add(proximo);
			}while (c.moveToNext());
		}
		return lista;
	}
	
}
