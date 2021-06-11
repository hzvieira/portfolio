package br.com.projetointegrador.controles;

import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.entidades.ItemPedido;
import br.com.projetointegrador.entidades.PeriodoVendas;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ControleItemPedido {

	private static final String NOME_BANCO = "HJVendas";
	private static final String NOME_TABELA = "ItensPedido";	
	
	protected SQLiteDatabase db;
	
	public ControleItemPedido(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	
	public Cursor getCursor(long id){
		try {
			return db.query(NOME_TABELA, ItemPedido.colunas, " pedido_id = " + id, null, null, null, null);
		} catch (SQLException e){
			Log.e("Controle Pedido", "Erro ao buscar todos as vendas, execeção: " + e.toString());
			return null;
		}
	}
	
	public boolean validaQuantidade(Long id, long quantidade) {
		boolean retorno = false;
		Cursor c = db.rawQuery("select quantidade " +
								"from prontaentrega, produto " +
								"where prontaentrega.produto_id = produto.id " +
								" and produto.id = " + id + 
								" and prontaentrega.quantidade >= " + quantidade, null);
		if (c.getCount() == 1) {
			retorno = true;
		} else {
			retorno = false;	
		}
		return retorno;
	}
	
	public List<ItemPedido> listarPedidoFinal(Context context, PeriodoVendas periodo) {
		List<ItemPedido> itens = new ArrayList<ItemPedido>();
		Cursor c = db.rawQuery("SELECT ip.id " +
					"FROM itenspedido ip, pedido p, produto pro, periodo pr " + 
					"WHERE ip.pedido_id = p.id " +
					"AND ip.produto_id = pro.id " +
					"AND pr.fornecedor_id = pro.fornecedor_id " +
					"AND ip.entregue = 'false' " +
					"AND p.datapedido between '" + periodo.getDataInicial() + "' AND '" + periodo.getDataFinal() + "' " +
					"AND pr.fornecedor_id = " + periodo.getFornecedor().getId(), null);
		if (c.moveToFirst()) {
			do {
				itens.add(buscarItemPedido(c.getLong(0), context));	
			}while (c.moveToNext());
		}
		return itens;
	}
	                                                                                
	
	public List<ItemPedido> listarItens(Context context, long id) {
		Cursor c = getCursor(id);
		List<ItemPedido> itens = new ArrayList<ItemPedido>();
		if (c.moveToFirst()) {
			do {
				ItemPedido proximo = new ItemPedido();
				proximo.setId(c.getLong(0));
				proximo.setEntregue(c.getLong(1) == 0 ? false : true);
				proximo.setQuantidade(c.getLong(2));
				proximo.setValor(c.getDouble(3));
				proximo.setPedido(new ControleVenda(context).buscarPedido(c.getLong(4), context));
				proximo.setProduto(new ControleProduto(context).buscarProduto(c.getLong(5), context));
				itens.add(proximo);
			}while (c.moveToNext());
		}
		return itens;
	}
	
	public ItemPedido buscarItemPedido(long id, Context context) {
		Cursor c = db.query(true, NOME_TABELA, ItemPedido.colunas, " ItensPedido.id ="
				+ id, null, null, null, null, null);
		if (c.getCount() > 0) {
			c.moveToFirst();
			ItemPedido busca = new ItemPedido();
			busca.setId(c.getLong(0));
			busca.setEntregue(Boolean.valueOf(c.getString(1)));
			busca.setQuantidade(c.getLong(2));
			busca.setValor(c.getDouble(3));
			busca.setPedido(new ControleVenda(context).buscarPedido(c.getLong(4), context));
			busca.setProduto(new ControleProduto(context).buscarProduto(c.getLong(5), context));
			
			return busca;
		}
		return null;
	}
}
