package br.com.projetointegrador.controles;

import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.entidades.TipoRelacionamento;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ControleTipoRelacionamento {

	private static final String NOME_BANCO = "HJVendas";
	private static final String NOME_TABELA = "tiporelacionamento";
	
	protected SQLiteDatabase db;
	
	public ControleTipoRelacionamento(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	

	public long salvar(TipoRelacionamento tipoRelacionamento) {
		long id = tipoRelacionamento.getId();
		if (id != 0) {
			atualizar(tipoRelacionamento);
		} else {
			id = inserir(tipoRelacionamento);
		}
		return id;
	}
	
	public long inserir(TipoRelacionamento tipoRelacionamento) {
		ContentValues valores = new ContentValues();
		valores.put(TipoRelacionamento.colunas[1], tipoRelacionamento.getNome());
		long id = inserir(valores);
		return id;
	}

	public long inserir(ContentValues v) {
		long id = db.insert(NOME_TABELA, "", v);
		return id;
	}

	public int atualizar(TipoRelacionamento tipoRelacionamento) {
		ContentValues valores = new ContentValues();
		valores.put(TipoRelacionamento.colunas[1], tipoRelacionamento.getNome());
		String _id = String.valueOf(tipoRelacionamento.getId());
		String where = "tipoRelacionamento.id =?";
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
		String where = "tipoRelacionamento.id =?";
		String[] whereArgs = new String[] { _id };
		int count = deletar(where, whereArgs);
		return count;
	}

	public int deletar(String where, String[] whereArgs) {
		int count = db.delete(NOME_TABELA, where, whereArgs);
		return count;
	}
	
	
	public List<TipoRelacionamento> listarTipoRelacionamento() {
		Cursor c = getCursor();
		List<TipoRelacionamento> lista = new ArrayList<TipoRelacionamento>();
		if (c.moveToFirst()) {
			do {
				TipoRelacionamento proximo = new TipoRelacionamento();
				proximo.setId(c.getLong(0));
				proximo.setNome(c.getString(1));
				lista.add(proximo);
			}while (c.moveToNext());
		}
		return lista;
	}
	
	public Cursor getCursor(){
		try {
			return db.query(NOME_TABELA, TipoRelacionamento.colunas, null, null, null, null, null);
		} catch (SQLException e){
			Log.e("Controle TipoRelacionamento", "Erro ao buscar todas os registros de TipoRelacionamento, execeção: " + e.toString());
			return null;
		}
	}
	
	public List<TipoRelacionamento> autoCompleta(CharSequence texto){
		List<TipoRelacionamento> lista = new ArrayList<TipoRelacionamento>();
		Cursor c = db.rawQuery("select id, nome from TipoRelacionamento where nome like '%" + texto + "%'", null); 
		if (c.moveToFirst()) {
			do {
				TipoRelacionamento proximo = new TipoRelacionamento();
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
	
	public TipoRelacionamento buscarTipoRelacionamento(long id) {
		Cursor c = db.query(true, NOME_TABELA, TipoRelacionamento.colunas, " TipoRelacionamento.id ="
				+ id, null, null, null, null, null);
		if (c.getCount() > 0) {
			c.moveToFirst();
			TipoRelacionamento busca = new TipoRelacionamento();
			busca.setId(c.getLong(0));
			busca.setNome(c.getString(1));
			return busca;
		}
		return null;
	}

}
