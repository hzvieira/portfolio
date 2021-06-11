package br.com.projetointegrador.controles;

import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.entidades.ParteDoCorpo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ControleParteDoCorpo {

	private static final String NOME_BANCO = "HJVendas";
	private static final String NOME_TABELA = "partedocorpo";
	
	protected SQLiteDatabase db;
	
	public ControleParteDoCorpo(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	

	public long salvar(ParteDoCorpo parteDoCorpo) {
		long id = parteDoCorpo.getId();
		if (id != 0) {
			atualizar(parteDoCorpo);
		} else {
			id = inserir(parteDoCorpo);
		}
		return id;
	}
	
	public long inserir(ParteDoCorpo parteDoCorpo) {
		ContentValues valores = new ContentValues();
		valores.put(ParteDoCorpo.colunas[1], parteDoCorpo.getNome());
		long id = inserir(valores);
		return id;
	}

	public long inserir(ContentValues v) {
		long id = db.insert(NOME_TABELA, "", v);
		return id;
	}

	public int atualizar(ParteDoCorpo parteDoCorpo) {
		ContentValues valores = new ContentValues();
		valores.put(ParteDoCorpo.colunas[1], parteDoCorpo.getNome());
		String _id = String.valueOf(parteDoCorpo.getId());
		String where = "parteDoCorpo.id =?";
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
		String where = "parteDoCorpo.id =?";
		String[] whereArgs = new String[] { _id };
		int count = deletar(where, whereArgs);
		return count;
	}

	public int deletar(String where, String[] whereArgs) {
		int count = db.delete(NOME_TABELA, where, whereArgs);
		return count;
	}
	
	
	public List<ParteDoCorpo> listarParteDoCorpo() {
		Cursor c = getCursor();
		List<ParteDoCorpo> lista = new ArrayList<ParteDoCorpo>();
		if (c.moveToFirst()) {
			do {
				ParteDoCorpo proximo = new ParteDoCorpo();
				proximo.setId(c.getLong(0));
				proximo.setNome(c.getString(1));
				lista.add(proximo);
			}while (c.moveToNext());
		}
		return lista;
	}
	
	public Cursor getCursor(){
		try {
			return db.query(NOME_TABELA, ParteDoCorpo.colunas, null, null, null, null, null);
		} catch (SQLException e){
			Log.e("Controle ParteDoCorpo", "Erro ao buscar todas os registros de ParteDoCorpo, execeção: " + e.toString());
			return null;
		}
	}
	
	public List<ParteDoCorpo> autoCompleta(CharSequence texto){
		List<ParteDoCorpo> lista = new ArrayList<ParteDoCorpo>();
		Cursor c = db.rawQuery("select id, nome from ParteDoCorpo where nome like '%" + texto + "%'", null); 
		if (c.moveToFirst()) {
			do {
				ParteDoCorpo proximo = new ParteDoCorpo();
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
	
	public ParteDoCorpo buscarParteDoCorpo(long id) {
		Cursor c = db.query(true, NOME_TABELA, ParteDoCorpo.colunas, " ParteDoCorpo.id ="
				+ id, null, null, null, null, null);
		if (c.getCount() > 0) {
			c.moveToFirst();
			ParteDoCorpo busca = new ParteDoCorpo();
			busca.setId(c.getLong(0));
			busca.setNome(c.getString(1));
			return busca;
		}
		return null;
	}

}
