package br.com.projetointegrador.controles;

import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.entidades.TipoContatoAgenda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ControleTipoContatoAgenda {

	private static final String NOME_BANCO = "HJVendas";
	private static final String NOME_TABELA = "tipocontatoagenda";
	
	protected SQLiteDatabase db;
	
	public ControleTipoContatoAgenda(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	

	public long salvar(TipoContatoAgenda tipoContatoAgenda) {
		long id = tipoContatoAgenda.getId();
		if (id != 0) {
			atualizar(tipoContatoAgenda);
		} else {
			id = inserir(tipoContatoAgenda);
		}
		return id;
	}
	
	public long inserir(TipoContatoAgenda tipoContatoAgenda) {
		ContentValues valores = new ContentValues();
		valores.put(TipoContatoAgenda.colunas[1], tipoContatoAgenda.getNome());
		long id = inserir(valores);
		return id;
	}

	public long inserir(ContentValues v) {
		long id = db.insert(NOME_TABELA, "", v);
		return id;
	}

	public int atualizar(TipoContatoAgenda tipoContatoAgenda) {
		ContentValues valores = new ContentValues();
		valores.put(TipoContatoAgenda.colunas[1], tipoContatoAgenda.getNome());
		String _id = String.valueOf(tipoContatoAgenda.getId());
		String where = "tipoContatoAgenda.id =?";
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
		String where = "tipoContatoAgenda.id =?";
		String[] whereArgs = new String[] { _id };
		int count = deletar(where, whereArgs);
		return count;
	}

	public int deletar(String where, String[] whereArgs) {
		int count = db.delete(NOME_TABELA, where, whereArgs);
		return count;
	}
	
	
	public List<TipoContatoAgenda> listarTipoContatoAgenda() {
		Cursor c = getCursor();
		List<TipoContatoAgenda> lista = new ArrayList<TipoContatoAgenda>();
		if (c.moveToFirst()) {
			do {
				TipoContatoAgenda proximo = new TipoContatoAgenda();
				proximo.setId(c.getLong(0));
				proximo.setNome(c.getString(1));
				lista.add(proximo);
			}while (c.moveToNext());
		}
		return lista;
	}
	
	public Cursor getCursor(){
		try {
			return db.query(NOME_TABELA, TipoContatoAgenda.colunas, null, null, null, null, null);
		} catch (SQLException e){
			Log.e("Controle TipoContatoAgenda", "Erro ao buscar todas os registros de TipoContatoAgenda, execeção: " + e.toString());
			return null;
		}
	}
	
	public List<TipoContatoAgenda> autoCompleta(CharSequence texto){
		List<TipoContatoAgenda> lista = new ArrayList<TipoContatoAgenda>();
		Cursor c = db.rawQuery("select id, nome from TipoContatoAgenda where nome like '%" + texto + "%'", null); 
		if (c.moveToFirst()) {
			do {
				TipoContatoAgenda proximo = new TipoContatoAgenda();
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
	
	public TipoContatoAgenda buscarTipoContatoAgenda(long id) {
		Cursor c = db.query(true, NOME_TABELA, TipoContatoAgenda.colunas, " TipoContatoAgenda.id ="
				+ id, null, null, null, null, null);
		if (c.getCount() > 0) {
			c.moveToFirst();
			TipoContatoAgenda busca = new TipoContatoAgenda();
			busca.setId(c.getLong(0));
			busca.setNome(c.getString(1));
			return busca;
		}
		return null;
	}

}
