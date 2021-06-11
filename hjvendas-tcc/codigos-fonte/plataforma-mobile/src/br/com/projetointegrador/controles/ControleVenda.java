package br.com.projetointegrador.controles;

import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.entidades.ItemPedido;
import br.com.projetointegrador.entidades.Pedido;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ControleVenda {

	private static final String NOME_BANCO = "HJVendas";
	private static final String NOME_TABELA = "Pedido";
	private static final String NOME_TABELA_ITEM = "ItensPedido";
	
	protected SQLiteDatabase db;
	
	public ControleVenda(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	
	public long salvar(Pedido pedido, List<ItemPedido> itens) {
		long id = inserir(pedido, itens);
		return id;
	}
	
	public long inserir(Pedido pedido, List<ItemPedido> itens) {
		ContentValues valoresVenda = new ContentValues();
		valoresVenda.put(Pedido.colunas[1], pedido.getData());
		valoresVenda.put(Pedido.colunas[2], pedido.getCliente().getId());
		long id = inserirVenda(valoresVenda);
		
		for (ItemPedido item : itens) {
			ContentValues valoresItem = new ContentValues();
			valoresItem.put(ItemPedido.colunas[1], item.isEntregue());
			valoresItem.put(ItemPedido.colunas[2], item.getQuantidade());
			valoresItem.put(ItemPedido.colunas[3], item.getValor());
			valoresItem.put(ItemPedido.colunas[4], id);
			valoresItem.put(ItemPedido.colunas[5], item.getProduto().getId());

			inserirItem(valoresItem);
		}
		return id;
	}

	public long inserirVenda(ContentValues v) {
		long id = db.insert(NOME_TABELA, "", v);
		return id;
	}
	
	public long inserirItem(ContentValues v) {
		long id = db.insert(NOME_TABELA_ITEM, "", v);
		return id;
	}
	

	public List<Pedido> listarVendas(Context context) {
		Cursor c = getCursor();
		List<Pedido> vendas = new ArrayList<Pedido>();
		if (c.moveToFirst()) {
			do {
				Pedido proximo = new Pedido();
				proximo.setId(c.getLong(0));
				proximo.setData(c.getString(1));
				proximo.setCliente(new ControleCliente(context).buscarCliente(c.getLong(2), context));
				vendas.add(proximo);
			}while (c.moveToNext());
		}
		return vendas;
	}
	
	public Cursor getCursor(){
		try {
			return db.query(NOME_TABELA, Pedido.colunas, null, null, null, null, null);
		} catch (SQLException e){
			Log.e("Controle Pedido", "Erro ao buscar todos as vendas, execeção: " + e.toString());
			return null;
		}
	}
	
	public Long quantidadeProdutos(long id){
		Cursor c = db.rawQuery("select sum(quantidade) from itenspedido where pedido_id = " + id, null); 
		if (c.moveToFirst()) {
			return c.getLong(0);
		}	
		return 0L;
	}

	
	public Pedido buscarPedido(long id, Context context) {
		Cursor c = db.query(true, NOME_TABELA, Pedido.colunas, " Pedido.id ="
				+ id, null, null, null, null, null);
		if (c.getCount() > 0) {
			c.moveToFirst();
			Pedido venda = new Pedido();
			venda.setId(c.getLong(0));
			venda.setData(c.getString(1));
			venda.setCliente(new ControleCliente(context).buscarCliente(c.getLong(2), context));
			return venda;
		}
		return null;
	}
	
	public void fechar() {
		if (db != null) {
			db.close();
		}
	}

}
