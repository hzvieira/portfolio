package br.com.projetointegrador.controles;

import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.entidades.Cliente;
import br.com.projetointegrador.entidades.Relacionamento;
import br.com.projetointegrador.listas.ListaCliente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ControleRelacionamento {

	private static final String NOME_BANCO = "HJVendas";
	private static final String NOME_TABELA = "relacionamento";
	
	protected SQLiteDatabase db;
	
	public ControleRelacionamento(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	

	public long salvar(Relacionamento relacionamento) {
		long id = relacionamento.getId();
		if (id != 0) {
			atualizar(relacionamento);
		} else {
			id = inserir(relacionamento);
		}
		return id;
	}
	
	public long inserir(Relacionamento relacionamento) {
		ContentValues valores = new ContentValues();
		valores.put(Relacionamento.colunas[1], relacionamento.isAtivo());
		valores.put(Relacionamento.colunas[2], relacionamento.getCliente().getId());
		valores.put(Relacionamento.colunas[3], relacionamento.getRelacionamento().getId());
		valores.put(Relacionamento.colunas[4], relacionamento.getTipoRelacionamento().getId());

		long id = inserir(valores);
		return id;
	}

	public long inserir(ContentValues v) {
		long id = db.insert(NOME_TABELA, "", v);
		return id;
	}

	public int atualizar(Relacionamento relacionamento) {
		ContentValues valores = new ContentValues();
		valores.put(Relacionamento.colunas[1], relacionamento.isAtivo());
		valores.put(Relacionamento.colunas[2], relacionamento.getCliente().getId());
		valores.put(Relacionamento.colunas[3], relacionamento.getRelacionamento().getId());
		valores.put(Relacionamento.colunas[4], relacionamento.getTipoRelacionamento().getId());
		String _id = String.valueOf(relacionamento.getId());
		String where = "relacionamento.id =?";
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
		String where = "relacionamento.id =?";
		String[] whereArgs = new String[] { _id };
		int count = deletar(where, whereArgs);
		return count;
	}

	public int deletar(String where, String[] whereArgs) {
		int count = db.delete(NOME_TABELA, where, whereArgs);
		return count; 
	}
	
	
	public List<Relacionamento> listarRelacionamento(long idCliente, Context context) {
		Cursor c = getCursor();
		List<Relacionamento> lista = new ArrayList<Relacionamento>();
		if (c.moveToFirst()) {
			do {
				if(idCliente == c.getLong(2)) {
					Relacionamento proximo = new Relacionamento();
					proximo.setId(c.getLong(0));
					proximo.setAtivo(Boolean.valueOf(c.getString(1)));
					proximo.setCliente(ListaCliente.repositorio.buscarCliente(c.getLong(2), context));
					proximo.setRelacionamento(ListaCliente.repositorio.buscarCliente(c.getLong(3), context));
					proximo.setTipoRelacionamento(new ControleTipoRelacionamento(context).buscarTipoRelacionamento(c.getLong(4)));
					lista.add(proximo);
				}
			}while (c.moveToNext());
		}
		return lista;
	}
	
	public Cursor getCursor(){
		try {
			return db.query(NOME_TABELA, Relacionamento.colunas, null, null, null, null, null);
		} catch (SQLException e){
			Log.e("Controle Relacionamento", "Erro ao buscar todas os registros de Relacionamento, execeção: " + e.toString());
			return null;
		}
	}
	
	public void fechar() {
		if (db != null) {
			db.close();
		}
	}
	
	public Relacionamento buscarRelacionamento(long id, Context context) {
		Cursor c = db.query(NOME_TABELA, Relacionamento.colunas, " relacionamento.id = " + id, null, null, null, null, null);
		if (c.moveToFirst()) {
			Relacionamento busca = new Relacionamento();
			busca.setId(c.getLong(0));
			busca.setAtivo(Boolean.valueOf(c.getString(1)));
			busca.setCliente(new ControleCliente(context).buscarCliente(c.getLong(2), context));
			busca.setRelacionamento(new ControleCliente(context).buscarCliente(c.getLong(3), context));
			busca.setTipoRelacionamento(new ControleTipoRelacionamento(context).buscarTipoRelacionamento(c.getLong(4)));
			return busca;
		}
		return null;
	}


	public void apagarRelacionamentos(Context context) {
		Cursor c = getCursor();
		if (c.moveToFirst()) {
			do {
				Cliente cliente = new ControleCliente(context).buscarCliente(c.getLong(2), context);
				if(cliente == null) {
					deletar(c.getLong(0));
				} 
				cliente = new ControleCliente(context).buscarCliente(c.getLong(3), context);
				if(cliente == null) {
					deletar(c.getLong(0));
				} 
			}while (c.moveToNext());
		}
		
	}

}
