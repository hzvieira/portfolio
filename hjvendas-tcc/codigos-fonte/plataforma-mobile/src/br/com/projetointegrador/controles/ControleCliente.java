package br.com.projetointegrador.controles;

import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.entidades.Cliente;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ControleCliente {

	private static final String NOME_BANCO = "HJVendas";
	private static final String NOME_TABELA = "cliente";
	
	protected SQLiteDatabase db;
	
	public ControleCliente(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	

	public long salvar(Cliente cliente) {
		long id = cliente.getId();
		if (id != 0) {
			atualizar(cliente);
		} else {
			id = inserir(cliente);
		}
		return id;
	}
	
	public long inserir(Cliente cliente) {
		ContentValues valores = new ContentValues();
		valores.put(Cliente.colunas[1], cliente.getNome());
		valores.put(Cliente.colunas[2], cliente.getTelefoneResidencial());
		valores.put(Cliente.colunas[3], cliente.getTelefoneCelular());
		valores.put(Cliente.colunas[4], cliente.getEmail01());
		valores.put(Cliente.colunas[5], cliente.getEmail02());
		valores.put(Cliente.colunas[6], cliente.getRua());
		valores.put(Cliente.colunas[7], cliente.getNumero());
		valores.put(Cliente.colunas[8], cliente.getComplemento());
		valores.put(Cliente.colunas[9], cliente.getBairro());
		valores.put(Cliente.colunas[10], cliente.getCep());
		valores.put(Cliente.colunas[11], cliente.getGenero().getId());
		valores.put(Cliente.colunas[12], cliente.getNascimento());
		valores.put(Cliente.colunas[13], cliente.getProfissao());
		valores.put(Cliente.colunas[14], cliente.getRenda());
		valores.put(Cliente.colunas[15], cliente.getSaldo());		
		valores.put(Cliente.colunas[16], cliente.getCidade().getId());
		valores.put(Cliente.colunas[17], cliente.getObservacoes());
		long id = inserir(valores);
		return id;
	}

	public long inserir(ContentValues v) {
		long id = db.insert(NOME_TABELA, "", v);
		return id;
	}

	public int atualizar(Cliente cliente) {
		ContentValues valores = new ContentValues();
		valores.put(Cliente.colunas[1], cliente.getNome());
		valores.put(Cliente.colunas[2], cliente.getTelefoneResidencial());
		valores.put(Cliente.colunas[3], cliente.getTelefoneCelular());
		valores.put(Cliente.colunas[4], cliente.getEmail01());
		valores.put(Cliente.colunas[5], cliente.getEmail02());
		valores.put(Cliente.colunas[6], cliente.getRua());
		valores.put(Cliente.colunas[7], cliente.getNumero());
		valores.put(Cliente.colunas[8], cliente.getComplemento());
		valores.put(Cliente.colunas[9], cliente.getBairro());
		valores.put(Cliente.colunas[10], cliente.getCep());
		valores.put(Cliente.colunas[11], cliente.getGenero().getId());
		valores.put(Cliente.colunas[12], String.valueOf(cliente.getNascimento()));
		valores.put(Cliente.colunas[13], cliente.getProfissao());
		valores.put(Cliente.colunas[14], cliente.getRenda());
		valores.put(Cliente.colunas[15], cliente.getSaldo());		
		valores.put(Cliente.colunas[16], cliente.getCidade().getId());
		valores.put(Cliente.colunas[17], cliente.getObservacoes());
		String _id = String.valueOf(cliente.getId());
		String where = "cliente.id =?";
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
		String where = "cliente.id =?";
		String[] whereArgs = new String[] { _id };
		int count = deletar(where, whereArgs);
		return count;
	}

	public int deletar(String where, String[] whereArgs) {
		int count = db.delete(NOME_TABELA, where, whereArgs);
		return count;
	}
	
	
	public List<Cliente> listarCliente() {
		Cursor c = getCursor();
		List<Cliente> lista = new ArrayList<Cliente>();
		if (c.moveToFirst()) {
			do {
				Cliente proximo = new Cliente();
				proximo.setId(c.getLong(0));
				proximo.setNome(c.getString(1));
				proximo.setTelefoneCelular(c.getString(3));
				proximo.setSaldo(c.getDouble(15));
				lista.add(proximo);
			}while (c.moveToNext());
		}
		return lista;
	}
	
	public Cursor getCursor(){
		try {
			return db.query(NOME_TABELA, Cliente.colunas, null, null, null, null, null);
		} catch (SQLException e){
			Log.e("Controle Cliente", "Erro ao buscar todas os registros de Cliente, execeção: " + e.toString());
			return null;
		}
	}
	
	public List<Cliente> autoCompleta(CharSequence texto){
		List<Cliente> lista = new ArrayList<Cliente>();
		Cursor c = db.rawQuery("select id, nome, saldo, telefonecelular from Cliente where nome like '%" + texto + "%'", null); 
		if (c.moveToFirst()) {
			do {
				Cliente proximo = new Cliente();
				proximo.setId(c.getLong(0));
				proximo.setNome(c.getString(1));
				proximo.setSaldo(c.getDouble(2));
				proximo.setTelefoneCelular(c.getString(3));
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
	
	public Cliente buscarCliente(long id, Context context) {
		Cursor c = db.query(true, NOME_TABELA, Cliente.colunas, " Cliente.id ="
				+ id, null, null, null, null, null);
		if (c.getCount() > 0) {
			c.moveToFirst();
			Cliente busca = new Cliente();
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
			busca.setGenero(new ControleGeneroProduto(context).buscarGeneroProduto(c.getLong(11)));
			busca.setNascimento(c.getString(12));
			busca.setProfissao(c.getString(13)); 
			busca.setRenda(c.getDouble(14));
			busca.setSaldo(c.getDouble(15));
			busca.setCidade(new ControleCidade(context).buscarCidade(c.getLong(16)));
			busca.setObservacoes(c.getString(17)); 
			
			return busca;
		}
		return null;
	}
	
	public void atualizarSaldo(long id, double valor, Context context) {
		Cliente c = buscarCliente(id, context);
		Log.i("Controle cliente", "Cliente: " + c.getNome() + " Saldo: " + c.getSaldo());
		c.setSaldo(c.getSaldo() + valor);
		Log.i("Controle cliente", "Cliente: " + c.getNome() + " Saldo atualizado: " + c.getSaldo());
		salvar(c);
	}

}
