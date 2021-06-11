package br.com.projetointegrador.controles;

import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.entidades.Devolucao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ControleDevolucao {

	private static final String NOME_BANCO = "HJVendas";
	private static final String NOME_TABELA = "Devolucao";
	
	protected SQLiteDatabase db;
	
	public ControleDevolucao(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	
	public boolean verificaDevolucao(long id, Long id2) {
		boolean retorno = false;
		Cursor c = db.rawQuery("select itenspedido.entregue " +
						"from pedido Inner Join itenspedido on (itenspedido.id = pedido.id) " +
									  "Inner Join produto on (itenspedido.produto_id = produto.id) " +
						"where itenspedido.entregue = 'true' " + 
						" and itenspedido.produto_id = " + id2 + 
						" and pedido.cliente_id = " + id, null);
						
		if (c.getCount() == 1) {
			retorno = true;
		}
		return retorno;
	}
	
	public long salvar(Devolucao dev) {
		long id = dev.getId();
		if (id != 0) {
			atualizar(dev);
		} else {
			id = inserir(dev);
		}
		return id;
	}
	
	public long inserir(Devolucao dev) {
		ContentValues valores = new ContentValues();
		valores.put(Devolucao.colunas[1], dev.getData());
		valores.put(Devolucao.colunas[2], dev.getCliente().getId());
		valores.put(Devolucao.colunas[3], dev.getProduto().getId());
		long id = inserir(valores);
		return id;
	}

	public long inserir(ContentValues v) {
		long id = db.insert(NOME_TABELA, "", v);
		return id;
	}

	public int atualizar(Devolucao dev) {
		ContentValues valores = new ContentValues();
		valores.put(Devolucao.colunas[1], dev.getData());
		valores.put(Devolucao.colunas[2], dev.getCliente().getId());
		valores.put(Devolucao.colunas[3], dev.getProduto().getId());
		String _id = String.valueOf(dev.getId());
		String where = "Devolucao.id =?";
		String[] whereArgs = new String[] { _id };
		int count = atualizar(valores, where, whereArgs);
		return count;
	}

	public int atualizar(ContentValues v, String where, String[] whereArgs) {
		int count = db.update(NOME_TABELA, v, where, whereArgs);
		return count;
	}

	public int deletar(long id) {
		String _id = String.valueOf(id);
		String where = "Devolucao.id =?";
		String[] whereArgs = new String[] { _id };
		int count = deletar(where, whereArgs);
		return count;
	}

	public int deletar(String where, String[] whereArgs) {
		int count = db.delete(NOME_TABELA, where, whereArgs);
		return count;
	}
	
	
	public List<Devolucao> listarDevolucao(Context context) {
		//Cursor c = db.rawQuery("select cliente_id, count(produto_id) from devolucao", null);
		Cursor c = getCursor();
		List<Devolucao> lista = new ArrayList<Devolucao>();
		if (c.moveToFirst()) {
			do {
				Devolucao proximo = new Devolucao();
				proximo.setId(c.getLong(0));
				proximo.setData(c.getString(1));
				proximo.setCliente(new ControleCliente(context).buscarCliente(c.getLong(2), context));
				proximo.setProduto(new ControleProduto(context).buscarProduto(c.getLong(3), context));
				lista.add(proximo);
			}while (c.moveToNext());
		}
		return lista;
	}
	
	public Cursor getCursor(){
		try {
			return db.query(NOME_TABELA, Devolucao.colunas, null, null, null, null, null);
		} catch (SQLException e){
			Log.e("Controle cidade", "Erro ao buscar todas as cidades, execeção: " + e.toString());
			return null;
		}
	}
	
	public List<Devolucao> autoCompleta(CharSequence texto, Context context){
		List<Devolucao> lista = new ArrayList<Devolucao>();
		Cursor c = db.rawQuery("select id, datadevolucao, cliente_id, produto_id " +
						"from devolucao, cliente " +
						"where devolucao.cliente_id = cliente.id AND " +
						"cliente.nome like '%" + texto + "%'", null); 
		if (c.moveToFirst()) {
			do {
				Devolucao proximo = new Devolucao();
				proximo.setId(c.getLong(0));
				proximo.setData(c.getString(1));
				proximo.setCliente(new ControleCliente(context).buscarCliente(c.getLong(2), context));
				proximo.setProduto(new ControleProduto(context).buscarProduto(c.getLong(3), context));
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
	
	public Long quantidadeProdutos(long id){
		Cursor c = db.rawQuery("select count(produto_id) from devolucao, cliente where devolucao.cliente_id = cliente.id and  cliente.id = " + id, null); 
		if (c.moveToFirst()) {
			return c.getLong(0);
		}	
		return 0L;
	}
	
	public Devolucao buscarDevolucao(long id, Context context) {
		Cursor c = db.query(true, NOME_TABELA, Devolucao.colunas, " Devolucao.id ="
				+ id, null, null, null, null, null);
		if (c.getCount() > 0) {
			c.moveToFirst();
			Devolucao busca = new Devolucao();
			busca.setId(c.getLong(0));
			busca.setData(c.getString(1));
			busca.setCliente(new ControleCliente(context).buscarCliente(c.getLong(2), context));
			busca.setProduto(new ControleProduto(context).buscarProduto(c.getLong(3), context));
			return busca;
		}
		return null;
	}

}
