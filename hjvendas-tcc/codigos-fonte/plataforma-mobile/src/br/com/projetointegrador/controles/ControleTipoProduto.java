package br.com.projetointegrador.controles;

import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.entidades.TipoProduto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ControleTipoProduto {

	private static final String NOME_BANCO = "HJVendas";
	private static final String NOME_TABELA = "tipoproduto";
	
	protected SQLiteDatabase db;
	
	public ControleTipoProduto(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	

	public long salvar(TipoProduto tipoProduto) {
		long id = tipoProduto.getId();
		if (id != 0) {
			atualizar(tipoProduto);
		} else {
			id = inserir(tipoProduto);
		}
		return id;
	}
	
	public long inserir(TipoProduto tipoProduto) {
		ContentValues valores = new ContentValues();
		valores.put(TipoProduto.colunas[1], tipoProduto.getNome());
		long id = inserir(valores);
		return id;
	}

	public long inserir(ContentValues v) {
		long id = db.insert(NOME_TABELA, "", v);
		return id;
	}

	public int atualizar(TipoProduto tipoProduto) {
		ContentValues valores = new ContentValues();
		valores.put(TipoProduto.colunas[1], tipoProduto.getNome());
		String _id = String.valueOf(tipoProduto.getId());
		String where = "tipoProduto.id =?";
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
		String where = "tipoProduto.id =?";
		String[] whereArgs = new String[] { _id };
		int count = deletar(where, whereArgs);
		return count;
	}

	public int deletar(String where, String[] whereArgs) {
		int count = db.delete(NOME_TABELA, where, whereArgs);
		return count;
	}
	
	
	public List<TipoProduto> listarTipoProduto() {
		Cursor c = getCursor();
		List<TipoProduto> lista = new ArrayList<TipoProduto>();
		if (c.moveToFirst()) {
			do {
				TipoProduto proximo = new TipoProduto();
				proximo.setId(c.getLong(0));
				proximo.setNome(c.getString(1));
				lista.add(proximo);
			}while (c.moveToNext());
		}
		return lista;
	}
	
	public Cursor getCursor(){
		try {
			return db.query(NOME_TABELA, TipoProduto.colunas, null, null, null, null, null);
		} catch (SQLException e){
			Log.e("Controle ParteDoCorpo", "Erro ao buscar todas os registros de TipoProduto, execeção: " + e.toString());
			return null;
		}
	}
	
	public List<TipoProduto> autoCompleta(CharSequence texto){
		List<TipoProduto> lista = new ArrayList<TipoProduto>();
		Cursor c = db.rawQuery("select id, nome from TipoProduto where nome like '%" + texto + "%'", null); 
		if (c.moveToFirst()) {
			do {
				TipoProduto proximo = new TipoProduto();
				proximo.setId(c.getLong(0));
				proximo.setNome(c.getString(1));
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
	
	public TipoProduto buscarTipoProduto(long id) {
		Cursor c = db.query(true, NOME_TABELA, TipoProduto.colunas, " TipoProduto.id ="
				+ id, null, null, null, null, null);
		if (c.getCount() == 1) {
			c.moveToFirst();
			TipoProduto busca = new TipoProduto();
			busca.setId(c.getLong(0));
			busca.setNome(c.getString(1));
			return busca;
		}
		return null;
	}

}
