package br.com.projetointegrador.controles;

import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.entidades.Fornecedor;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ControleFornecedor {

	private static final String NOME_BANCO = "HJVendas";
	private static final String NOME_TABELA = "fornecedor";
	
	protected SQLiteDatabase db;
	
	public ControleFornecedor(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	

	public long salvar(Fornecedor fornecedor) {
		long id = fornecedor.getId();
		if (id != 0) {
			atualizar(fornecedor);
		} else {
			id = inserir(fornecedor);
		}
		return id;
	}
	
	public long inserir(Fornecedor fornecedor) {
		ContentValues valores = new ContentValues();
		valores.put(Fornecedor.colunas[1], fornecedor.getNome());
		valores.put(Fornecedor.colunas[2], fornecedor.getTelefoneResidencial());
		valores.put(Fornecedor.colunas[3], fornecedor.getTelefoneCelular());
		valores.put(Fornecedor.colunas[4], fornecedor.getEmail01());
		valores.put(Fornecedor.colunas[5], fornecedor.getEmail02());
		valores.put(Fornecedor.colunas[6], fornecedor.getRua());
		valores.put(Fornecedor.colunas[7], fornecedor.getNumero());
		valores.put(Fornecedor.colunas[8], fornecedor.getComplemento());
		valores.put(Fornecedor.colunas[9], fornecedor.getBairro());
		valores.put(Fornecedor.colunas[10], fornecedor.getCep());
		valores.put(Fornecedor.colunas[11], fornecedor.getCnpj());
		valores.put(Fornecedor.colunas[12], fornecedor.getCidade().getId());
		valores.put(Fornecedor.colunas[13], fornecedor.getObservacoes());
		long id = inserir(valores);
		return id;
	}

	public long inserir(ContentValues v) {
		long id = db.insert(NOME_TABELA, "", v);
		return id;
	}

	public int atualizar(Fornecedor fornecedor) {
		ContentValues valores = new ContentValues();
		valores.put(Fornecedor.colunas[1], fornecedor.getNome());
		valores.put(Fornecedor.colunas[2], fornecedor.getTelefoneResidencial());
		valores.put(Fornecedor.colunas[3], fornecedor.getTelefoneCelular());
		valores.put(Fornecedor.colunas[4], fornecedor.getEmail01());
		valores.put(Fornecedor.colunas[5], fornecedor.getEmail02());
		valores.put(Fornecedor.colunas[6], fornecedor.getRua());
		valores.put(Fornecedor.colunas[7], fornecedor.getNumero());
		valores.put(Fornecedor.colunas[8], fornecedor.getComplemento());
		valores.put(Fornecedor.colunas[9], fornecedor.getBairro());
		valores.put(Fornecedor.colunas[10], fornecedor.getCep());
		valores.put(Fornecedor.colunas[11], fornecedor.getCnpj());
		valores.put(Fornecedor.colunas[12], fornecedor.getCidade().getId());
		valores.put(Fornecedor.colunas[13], fornecedor.getObservacoes());
		String _id = String.valueOf(fornecedor.getId());
		String where = "fornecedor.id =?";
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
		String where = "fornecedor.id =?";
		String[] whereArgs = new String[] { _id };
		int count = deletar(where, whereArgs);
		return count;
	}

	public int deletar(String where, String[] whereArgs) {
		int count = db.delete(NOME_TABELA, where, whereArgs);
		return count;
	}
	
	
	public List<Fornecedor> listarFornecedor() {
		Cursor c = getCursor();
		List<Fornecedor> lista = new ArrayList<Fornecedor>();
		if (c.moveToFirst()) {
			do {
				Fornecedor proximo = new Fornecedor();
				proximo.setId(c.getLong(0));
				proximo.setNome(c.getString(1));
				lista.add(proximo);
			}while (c.moveToNext());
		}
		return lista;
	}
	
	public Cursor getCursor(){
		try {
			return db.query(NOME_TABELA, Fornecedor.colunas, null, null, null, null, null);
		} catch (SQLException e){
			Log.e("Controle Fornecedor", "Erro ao buscar todas os registros de Fornecedor, execeção: " + e.toString());
			return null;
		}
	}
	
	public List<Fornecedor> autoCompleta(CharSequence texto){
		List<Fornecedor> lista = new ArrayList<Fornecedor>();
		Cursor c = db.rawQuery("select id, nome from Fornecedor where nome like '%" + texto + "%'", null); 
		if (c.moveToFirst()) {
			do {
				Fornecedor proximo = new Fornecedor();
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
	
	public Fornecedor buscarFornecedor(long id, Context context) {
		Cursor c = db.query(true, NOME_TABELA, Fornecedor.colunas, " Fornecedor.id ="
				+ id, null, null, null, null, null);
		if (c.getCount() > 0) {
			c.moveToFirst();
			Fornecedor busca = new Fornecedor();
			busca.setId(c.getLong(0));
			busca.setNome(c.getString(1));
			busca.setTelefoneResidencial(c.getString(2));
			busca.setTelefoneCelular(c.getString(3));
			busca.setEmail01(c.getString(4));
			busca.setEmail02(c.getString(5));
			busca.setRua(c.getString(6));
			busca.setNumero(c.getLong(7));
			busca.setComplemento(c.getString(8));
			busca.setBairro(c.getString(9));
			busca.setCep(c.getString(10));
			busca.setCnpj(c.getString(11));
			busca.setCidade(new ControleCidade(context).buscarCidade(c.getLong(12)));
			busca.setObservacoes(c.getString(13));
			
			return busca;
		}
		return null;
	}

}
