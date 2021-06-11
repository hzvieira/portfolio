package br.com.projetointegrador.controles;

import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.entidades.CorProduto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ControleCorProduto {

	private static final String NOME_BANCO = "HJVendas";
	private static final String NOME_TABELA = "cor";
	
	protected SQLiteDatabase db;
	
	public ControleCorProduto(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	

	public long salvar(CorProduto corProduto) {
		long id = corProduto.getId();
		if (id != 0) {
			atualizar(corProduto);
		} else {
			id = inserir(corProduto);
		}
		return id;
	}
	
	public long inserir(CorProduto corProduto) {
		ContentValues valores = new ContentValues();
		valores.put(CorProduto.colunas[1], corProduto.getNome());
		long id = inserir(valores);
		return id;
	}

	public long inserir(ContentValues v) {
		long id = db.insert(NOME_TABELA, "", v);
		return id;
	}

	public int atualizar(CorProduto corProduto) {
		ContentValues valores = new ContentValues();
		valores.put(CorProduto.colunas[1], corProduto.getNome());
		String _id = String.valueOf(corProduto.getId());
		String where = "cor.id =?";
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
		String where = "cor.id =?";
		String[] whereArgs = new String[] { _id };
		int count = deletar(where, whereArgs);
		return count;
	}

	public int deletar(String where, String[] whereArgs) {
		int count = db.delete(NOME_TABELA, where, whereArgs);
		return count;
	}
	
	
	public List<CorProduto> listarCorProduto() {
		Cursor c = getCursor();
		List<CorProduto> lista = new ArrayList<CorProduto>();
		if (c.moveToFirst()) {
			do {
				CorProduto proximo = new CorProduto();
				proximo.setId(c.getLong(0));
				proximo.setNome(c.getString(1));
				lista.add(proximo);
			}while (c.moveToNext());
		}
		return lista;
	}
	
	public Cursor getCursor(){
		try {
			return db.query(NOME_TABELA, CorProduto.colunas, null, null, null, null, null);
		} catch (SQLException e){
			Log.e("Controle CorProduto", "Erro ao buscar todas os registros de CorProduto, execeção: " + e.toString());
			return null;
		}
	}
	
	public List<CorProduto> autoCompleta(CharSequence texto){
		List<CorProduto> lista = new ArrayList<CorProduto>();
		Cursor c = db.rawQuery("select id, nome from Cor where nome like '%" + texto + "%'", null); 
		if (c.moveToFirst()) {
			do {
				CorProduto proximo = new CorProduto();
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
	
	public CorProduto buscarCorProduto(long id) {
		Cursor c = db.query(true, NOME_TABELA, CorProduto.colunas, " Cor.id ="
				+ id, null, null, null, null, null);
		if (c.getCount() > 0) {
			c.moveToFirst();
			CorProduto busca = new CorProduto();
			busca.setId(c.getLong(0));
			busca.setNome(c.getString(1));
			return busca;
		}
		return null;
	}

}
