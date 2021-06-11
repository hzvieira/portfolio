package br.com.projetointegrador.controles;

import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.entidades.ParcelasPagar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ControleParcelasPagar {

	private static final String NOME_BANCO = "HJVendas";
	private static final String NOME_TABELA = "parcelaspagar";
	
	protected SQLiteDatabase db;
	
	public ControleParcelasPagar(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	

	public long salvar(ParcelasPagar parcelaspagar) {
		long id = parcelaspagar.getId();
		if (id != 0) {
			atualizar(parcelaspagar);
		} else {
			id = inserir(parcelaspagar);
		}
		return id;
	}
	
	public long inserir(ParcelasPagar parcelaspagar) {
		ContentValues valores = new ContentValues();
		valores.put(ParcelasPagar.colunas[1], parcelaspagar.getDataCompra());
		valores.put(ParcelasPagar.colunas[2], parcelaspagar.getDataPagamento());
		valores.put(ParcelasPagar.colunas[3], parcelaspagar.getDataVencimento());
		valores.put(ParcelasPagar.colunas[4], parcelaspagar.getValor());
		valores.put(ParcelasPagar.colunas[5], parcelaspagar.getFornecedor().getId());
		long id = inserir(valores);
		return id;
	}

	public long inserir(ContentValues v) {
		long id = db.insert(NOME_TABELA, "", v);
		return id;
	}

	public int atualizar(ParcelasPagar parcelaspagar) {
		ContentValues valores = new ContentValues();
		valores.put(ParcelasPagar.colunas[1], parcelaspagar.getDataCompra());
		valores.put(ParcelasPagar.colunas[2], parcelaspagar.getDataPagamento());
		valores.put(ParcelasPagar.colunas[3], parcelaspagar.getDataVencimento());
		valores.put(ParcelasPagar.colunas[4], parcelaspagar.getValor());
		valores.put(ParcelasPagar.colunas[5], parcelaspagar.getFornecedor().getId());
		String _id = String.valueOf(parcelaspagar.getId());
		String where = "parcelaspagar.id =?";
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
		String where = "parcelaspagar.id =?";
		String[] whereArgs = new String[] { _id };
		int count = deletar(where, whereArgs);
		return count;
	}

	public int deletar(String where, String[] whereArgs) {
		int count = db.delete(NOME_TABELA, where, whereArgs);
		return count;
	}
	
	
	public List<ParcelasPagar> listarParcelasPagar() {
		Cursor c = getCursor();
		List<ParcelasPagar> lista = new ArrayList<ParcelasPagar>();
		if (c.moveToFirst()) {
			do {
				ParcelasPagar proximo = new ParcelasPagar();
				proximo.setId(c.getLong(0));
				proximo.setDataCompra(c.getString(1));
				proximo.setDataPagamento(c.getString(2));
				proximo.setDataVencimento(c.getString(3));
				proximo.setValor(c.getDouble(4));
				lista.add(proximo);
			}while (c.moveToNext());
		}
		return lista;
	}
	
	public Cursor getCursor(){
		try {
			return db.query(NOME_TABELA, ParcelasPagar.colunas, null, null, null, null, null);
		} catch (SQLException e){
			Log.e("Controle ParcelasPagar", "Erro ao buscar todas as ParcelasPagar, execeção: " + e.toString());
			return null;
		}
	}
	
	public List<ParcelasPagar> autoCompleta(CharSequence texto, Context context){
		List<ParcelasPagar> lista = new ArrayList<ParcelasPagar>();
		Cursor c = db.rawQuery("" +
				"select pp.id, pp.datacompra, pp.datapagamento, pp.datavencimento, pp.valor, f.id, f.nome " +
				"from ParcelasPagar pp, Fornecedor f " +
				"where f.nome like '%" + texto + "%'", null); 
		if (c.moveToFirst()) {
			do {
				ParcelasPagar proximo = new ParcelasPagar();
				proximo.setId(c.getLong(0));
				proximo.setDataCompra(c.getString(1));
				proximo.setDataPagamento(c.getString(2));
				proximo.setDataVencimento(c.getString(3));
				proximo.setValor(c.getDouble(4));
				proximo.setFornecedor(new ControleFornecedor(context).buscarFornecedor(c.getLong(5), context));
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
	
	public ParcelasPagar buscarParcelasPagar(long id, Context context) {
		Cursor c = db.query(true, NOME_TABELA, ParcelasPagar.colunas, " ParcelasPagar.id ="
				+ id, null, null, null, null, null);
		if (c.getCount() > 0) {
			c.moveToFirst();
			ParcelasPagar busca = new ParcelasPagar();
			busca.setId(c.getLong(0));
			busca.setDataCompra(c.getString(1));
			busca.setDataPagamento(c.getString(2));
			busca.setDataVencimento(c.getString(3));
			busca.setValor(c.getDouble(4));
			busca.setFornecedor(new ControleFornecedor(context).buscarFornecedor(c.getLong(5), context));
			return busca;
		}
		return null;
	}

}
