package br.com.projetointegrador.controles;

import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.entidades.GeneroProduto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ControleGeneroProduto {

	private static final String NOME_BANCO = "HJVendas";
	private static final String NOME_TABELA = "genero";
	
	protected SQLiteDatabase db;
	
	public ControleGeneroProduto(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	

	public long salvar(GeneroProduto generoProduto) {
		long id = generoProduto.getId();
		if (id != 0) {
			atualizar(generoProduto);
		} else {
			id = inserir(generoProduto);
		}
		return id;
	}
	
	public long inserir(GeneroProduto generoProduto) {
		ContentValues valores = new ContentValues();
		valores.put(GeneroProduto.colunas[1], generoProduto.getNome());
		long id = inserir(valores);
		return id;
	}

	public long inserir(ContentValues v) {
		long id = db.insert(NOME_TABELA, "", v);
		return id;
	}

	public int atualizar(GeneroProduto generoProduto) {
		ContentValues valores = new ContentValues();
		valores.put(GeneroProduto.colunas[1], generoProduto.getNome());
		String _id = String.valueOf(generoProduto.getId());
		String where = "genero.id =?";
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
		String where = "genero.id =?";
		String[] whereArgs = new String[] { _id };
		int count = deletar(where, whereArgs);
		return count;
	}

	public int deletar(String where, String[] whereArgs) {
		int count = db.delete(NOME_TABELA, where, whereArgs);
		return count;
	}
	
	
	public List<GeneroProduto> listarGeneroProduto() {
		Cursor c = getCursor();
		List<GeneroProduto> lista = new ArrayList<GeneroProduto>();
		if (c.moveToFirst()) {
			do {
				GeneroProduto proximo = new GeneroProduto();
				proximo.setId(c.getLong(0));
				proximo.setNome(c.getString(1));
				lista.add(proximo);
			}while (c.moveToNext());
		}
		return lista;
	}
	
	public Cursor getCursor(){
		try {
			return db.query(NOME_TABELA, GeneroProduto.colunas, null, null, null, null, null);
		} catch (SQLException e){
			Log.e("Controle CorProduto", "Erro ao buscar todas os registros de GeneroProduto, execeção: " + e.toString());
			return null;
		}
	}
	
	public List<GeneroProduto> autoCompleta(CharSequence texto){
		List<GeneroProduto> lista = new ArrayList<GeneroProduto>();
		Cursor c = db.rawQuery("select id, nome from Genero where nome like '%" + texto + "%'", null); 
		if (c.moveToFirst()) {
			do {
				GeneroProduto proximo = new GeneroProduto();
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
	
	public GeneroProduto buscarGeneroProduto(long id) {
		Cursor c = db.query(true, NOME_TABELA, GeneroProduto.colunas, " Genero.id ="
				+ id, null, null, null, null, null);
		if (c.getCount() > 0) {
			c.moveToFirst();
			GeneroProduto busca = new GeneroProduto();
			busca.setId(c.getLong(0));
			busca.setNome(c.getString(1));
			return busca;
		}
		return null;
	}

}
