package br.com.projetointegrador.controles;

import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.entidades.Cidade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ControleCidade {

	private static final String NOME_BANCO = "HJVendas";
	private static final String NOME_TABELA = "Cidade";
	
	protected SQLiteDatabase db;
	
	public ControleCidade(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	

	public long salvar(Cidade cidade) {
		long id = cidade.getId();
		if (id != 0) {
			atualizar(cidade);
		} else {
			id = inserir(cidade);
		}
		return id;
	}
	
	public long inserir(Cidade cidade) {
		ContentValues valores = new ContentValues();
		valores.put(Cidade.colunas[1], cidade.getNome());
		long id = inserir(valores);
		return id;
	}

	public long inserir(ContentValues v) {
		long id = db.insert(NOME_TABELA, "", v);
		return id;
	}

	public int atualizar(Cidade cidade) {
		ContentValues valores = new ContentValues();
		valores.put(Cidade.colunas[1], cidade.getNome());
		String _id = String.valueOf(cidade.getId());
		String where = "cidade.id =?";
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
		String where = "cidade.id =?";
		String[] whereArgs = new String[] { _id };
		int count = deletar(where, whereArgs);
		return count;
	}

	public int deletar(String where, String[] whereArgs) {
		int count = db.delete(NOME_TABELA, where, whereArgs);
		return count;
	}
	
	
	public List<Cidade> listarCidades() {
		Cursor c = getCursor();
		List<Cidade> lista = new ArrayList<Cidade>();
		if (c.moveToFirst()) {
			do {
				Cidade proximo = new Cidade();
				proximo.setId(c.getLong(0));
				proximo.setNome(c.getString(1));
				lista.add(proximo);
			}while (c.moveToNext());
		}
		return lista;
	}
	
	public Cursor getCursor(){
		try {
			return db.query(NOME_TABELA, Cidade.colunas, null, null, null, null, null);
		} catch (SQLException e){
			Log.e("Controle cidade", "Erro ao buscar todas as cidades, execeção: " + e.toString());
			return null;
		}
	}
	
	public List<Cidade> autoCompleta(CharSequence texto){
		List<Cidade> lista = new ArrayList<Cidade>();
		Cursor c = db.rawQuery("select id, nome from cidade where nome like '%" + texto + "%'", null); 
		if (c.moveToFirst()) {
			do {
				Cidade proximo = new Cidade();
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
	
	public Cidade buscarCidade(long id) {
		Cursor c = db.query(true, NOME_TABELA, Cidade.colunas, " cidade.id ="
				+ id, null, null, null, null, null);
		if (c.getCount() > 0) {
			c.moveToFirst();
			Cidade busca = new Cidade();
			busca.setId(c.getLong(0));
			busca.setNome(c.getString(1));
			return busca;
		}
		return null;
	}

}
