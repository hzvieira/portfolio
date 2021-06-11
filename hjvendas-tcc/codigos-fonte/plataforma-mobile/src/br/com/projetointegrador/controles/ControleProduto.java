package br.com.projetointegrador.controles;

import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.entidades.Produto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ControleProduto {

	private static final String NOME_BANCO = "HJVendas";
	private static final String NOME_TABELA = "produto";
	
	protected SQLiteDatabase db;
	
	public ControleProduto(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	

	public long salvar(Produto produto) {
		long id = produto.getId();
		if (id != 0) {
			atualizar(produto);
		} else {
			id = inserir(produto);
		}
		return id;
	}
	
	public long inserir(Produto produto) {
		ContentValues valores = new ContentValues();
		valores.put(Produto.colunas[1], produto.getDescMaterial());
		valores.put(Produto.colunas[2], produto.getDuracao());
		valores.put(Produto.colunas[3], produto.getNome());
		valores.put(Produto.colunas[4], produto.getNumero());
		valores.put(Produto.colunas[5], produto.getProduto());
		valores.put(Produto.colunas[6], produto.getTamanho()); // acessorio
		valores.put(Produto.colunas[7], produto.getTamanhoSalto());
		valores.put(Produto.colunas[8], produto.getTempoDuracao());
		valores.put(Produto.colunas[9], produto.getValor());
		valores.put(Produto.colunas[10], produto.getVolume());
		valores.put(Produto.colunas[11], produto.getVolumeCosmetico());
		valores.put(Produto.colunas[12], produto.getCor().getId());
		valores.put(Produto.colunas[13], produto.getFornecedor().getId());
		valores.put(Produto.colunas[14], produto.getParteDoCorpo().getId());
		valores.put(Produto.colunas[15], produto.getGenero().getId());
		valores.put(Produto.colunas[16], produto.getTipoProduto().getId());
		long id = inserir(valores);
		return id;
	}

	public long inserir(ContentValues v) {
		long id = db.insert(NOME_TABELA, "", v);
		return id;
	}

	public int atualizar(Produto produto) {
		ContentValues valores = new ContentValues();
		valores.put(Produto.colunas[1], produto.getDescMaterial());
		valores.put(Produto.colunas[2], produto.getDuracao());
		valores.put(Produto.colunas[3], produto.getNome());
		valores.put(Produto.colunas[4], produto.getNumero());
		valores.put(Produto.colunas[5], produto.getProduto());
		valores.put(Produto.colunas[6], produto.getTamanho());
		valores.put(Produto.colunas[7], produto.getTamanhoSalto());
		valores.put(Produto.colunas[8], produto.getTempoDuracao());
		valores.put(Produto.colunas[9], produto.getValor());
		valores.put(Produto.colunas[10], produto.getVolume());
		valores.put(Produto.colunas[11], produto.getVolumeCosmetico());
		valores.put(Produto.colunas[12], produto.getCor().getId());
		valores.put(Produto.colunas[13], produto.getFornecedor().getId());
		valores.put(Produto.colunas[14], produto.getParteDoCorpo().getId());
		valores.put(Produto.colunas[15], produto.getGenero().getId());
		valores.put(Produto.colunas[16], produto.getTipoProduto().getId());
		String _id = String.valueOf(produto.getId());
		String where = "produto.id =?";
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
		String where = "produto.id =?";
		String[] whereArgs = new String[] { _id };
		int count = deletar(where, whereArgs);
		return count;
	}

	public int deletar(String where, String[] whereArgs) {
		int count = db.delete(NOME_TABELA, where, whereArgs);
		return count;
	}
	
	
	public List<Produto> listarProdutos() {
		Cursor c = getCursor();
		List<Produto> lista = new ArrayList<Produto>();
		if (c.moveToFirst()) {
			do {
				Produto proximo = new Produto();
				proximo.setId(c.getLong(0));
				proximo.setNome(c.getString(3));
				proximo.setProduto(c.getString(5));
				proximo.setValor(c.getDouble(9));
				lista.add(proximo);
			}while (c.moveToNext());
		}
		return lista;
	}
	
	public Cursor getCursor(){
		try {
			return db.query(NOME_TABELA, Produto.colunas, null, null, null, null, null);
		} catch (SQLException e){
			Log.e("Controle Produto", "Erro ao buscar todas os Produto, execeção: " + e.toString());
			return null;
		}
	}
	
	public List<Produto> autoCompleta(CharSequence texto){
		List<Produto> lista = new ArrayList<Produto>();
		Cursor c = db.rawQuery("" +
				"select id, nome, valor, produto from Produto where nome like '%" + texto + "%'", null); 
		if (c.moveToFirst()) {
			do {
				Produto proximo = new Produto();
				proximo.setId(c.getLong(0));
				proximo.setNome(c.getString(1));
				proximo.setValor(c.getDouble(2));
				proximo.setProduto(c.getString(3));
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
	
	public Produto buscarProduto(long id, Context context) {
		Cursor c = db.query(true, NOME_TABELA, Produto.colunas, " Produto.id ="
				+ id, null, null, null, null, null);
		if (c.getCount() > 0) {
			c.moveToFirst();
			Produto busca = new Produto();
			busca.setId(c.getLong(0));
			busca.setDescMaterial(c.getString(1));
			busca.setDuracao(c.getLong(2));
			busca.setNome(c.getString(3));
			busca.setNumero(c.getLong(4));
			busca.setProduto(c.getString(5));
			busca.setTamanho(c.getString(6));
			busca.setTamanhoSalto(c.getLong(7));
			busca.setTempoDuracao(c.getString(8));
			busca.setValor(c.getDouble(9));
			busca.setVolume(c.getLong(10));
			busca.setVolumeCosmetico(c.getString(11));
			busca.setCor(new ControleCorProduto(context).buscarCorProduto(c.getLong(12)));
			busca.setFornecedor(new ControleFornecedor(context).buscarFornecedor(c.getLong(13), context));
			busca.setParteDoCorpo(new ControleParteDoCorpo(context).buscarParteDoCorpo(c.getLong(14)));
			busca.setGenero(new ControleGeneroProduto(context).buscarGeneroProduto(c.getLong(15)));
			busca.setTipoProduto(new ControleTipoProduto(context).buscarTipoProduto(c.getLong(16)));
			return busca;
		}
		return null;
	}

}
