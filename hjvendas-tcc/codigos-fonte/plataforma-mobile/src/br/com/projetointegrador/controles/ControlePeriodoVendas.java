package br.com.projetointegrador.controles;

import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.entidades.Fornecedor;
import br.com.projetointegrador.entidades.PeriodoVendas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ControlePeriodoVendas {

	private static final String NOME_BANCO = "HJVendas";
	private static final String NOME_TABELA = "periodo";
	
	protected SQLiteDatabase db;
	
	public ControlePeriodoVendas(Context ctx) {
		db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
	}
	

	public long salvar(PeriodoVendas periodoVendas) {
		long id = periodoVendas.getId();
		if (id != 0) {
			atualizar(periodoVendas);
		} else {
			id = inserir(periodoVendas);
		}
		return id;
	}
	
	public long inserir(PeriodoVendas periodoVendas) {
		ContentValues valores = new ContentValues();
		valores.put(PeriodoVendas.colunas[1], periodoVendas.getDataFinal());
		valores.put(PeriodoVendas.colunas[2], periodoVendas.getDataInicial());
		valores.put(PeriodoVendas.colunas[3], periodoVendas.isPedidoFeito());
		valores.put(PeriodoVendas.colunas[4], periodoVendas.isRecebeuEncomenda());
		valores.put(PeriodoVendas.colunas[5], periodoVendas.getFornecedor().getId());
		long id = inserir(valores);
		return id;
	}

	public long inserir(ContentValues v) {
		long id = db.insert(NOME_TABELA, "", v);
		return id;
	}

	public int atualizar(PeriodoVendas periodoVendas) {
		ContentValues valores = new ContentValues();
		valores.put(PeriodoVendas.colunas[1], periodoVendas.getDataFinal());
		valores.put(PeriodoVendas.colunas[2], periodoVendas.getDataInicial());
		valores.put(PeriodoVendas.colunas[3], periodoVendas.isPedidoFeito());
		valores.put(PeriodoVendas.colunas[4], periodoVendas.isRecebeuEncomenda());
		valores.put(PeriodoVendas.colunas[5], periodoVendas.getFornecedor().getId());
		String _id = String.valueOf(periodoVendas.getId());
		String where = "periodo.id =?";
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
		String where = "periodo.id =?";
		String[] whereArgs = new String[] { _id };
		int count = deletar(where, whereArgs);
		return count;
	}

	public int deletar(String where, String[] whereArgs) {
		int count = db.delete(NOME_TABELA, where, whereArgs);
		return count;
	}
	
	
	public List<PeriodoVendas> listarPeriodoVendas(Context context) {
		Cursor c = getCursor();
		List<PeriodoVendas> lista = new ArrayList<PeriodoVendas>();
		if (c.moveToFirst()) {
			do {
				PeriodoVendas proximo = new PeriodoVendas();
				proximo.setId(c.getLong(0));
				proximo.setDataFinal(c.getString(1));
				proximo.setDataInicial(c.getString(2));
				proximo.setPedidoFeito(c.getLong(3) == 0 ? false : true);
				proximo.setRecebeuEncomenda(c.getLong(4) == 0 ? false : true);
				proximo.setFornecedor(new ControleFornecedor(context).buscarFornecedor(c.getLong(5), context));
				lista.add(proximo);
			}while (c.moveToNext());
		}
		return lista;
	}
	
	public Cursor getCursor(){
		try {
			return db.query(NOME_TABELA, PeriodoVendas.colunas, null, null, null, null, null);
		} catch (SQLException e){
			Log.e("Controle PeriodoVendas", "Erro ao buscar todas os PeriodoVendas, execeção: " + e.toString());
			return null;
		}
	}
	
	public List<PeriodoVendas> autoCompleta(CharSequence texto, Context context){
		List<PeriodoVendas> lista = new ArrayList<PeriodoVendas>();
		Cursor c = db.rawQuery("" +
				"select pp.id, pp.datafinal, pp.datainicial, pp.pedidofeito, pp.recebeuencomenda, f.nome " +
				"from Periodo pp, Fornecedor f " +
				"where f.nome like '%" + texto + "%'", null); 
		if (c.moveToFirst()) {
			do {
				PeriodoVendas proximo = new PeriodoVendas();
				proximo.setId(c.getLong(0));
				proximo.setDataFinal(c.getString(1));
				proximo.setDataInicial(c.getString(2));
				proximo.setPedidoFeito(c.getLong(3) == 0 ? false : true);
				proximo.setRecebeuEncomenda(c.getLong(4) == 0 ? false : true);
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
	
	public PeriodoVendas buscarPeriodoVendas(long id, Context context) {
		Cursor c = db.query(true, NOME_TABELA, PeriodoVendas.colunas, " Periodo.id ="
				+ id, null, null, null, null, null);
		if (c.getCount() > 0) {
			c.moveToFirst();
			PeriodoVendas busca = new PeriodoVendas();
			busca.setId(c.getLong(0));
			busca.setDataFinal(c.getString(1));
			busca.setDataInicial(c.getString(2));
			busca.setPedidoFeito(c.getLong(3) == 0 ? false : true);
			busca.setRecebeuEncomenda(c.getLong(4) == 0 ? false : true);
			busca.setFornecedor(new ControleFornecedor(context).buscarFornecedor(c.getLong(5), context));
			return busca;
		}
		return null;
	}

	public boolean verificaPeriodo(String datainicial, Fornecedor fornecedor) {
		Cursor c = db.rawQuery("select id from periodo " +
							"WHERE periodo.datafinal > " + datainicial +
							" AND periodo.fornecedor_id = " + fornecedor.getId(),null); 
		if (c.getCount() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Long atualizarFazerPedido(PeriodoVendas periodo, Context context) {
		Long cont = 0L;
		Cursor c = db.rawQuery("select cliente.id, itenspedido.valor * itenspedido.quantidade  from cliente, pedido, itenspedido, produto, fornecedor, periodo " +
				"WHERE cliente.id = pedido.cliente_id " + 
				"AND pedido.id = itenspedido.pedido_id " +
				"AND itenspedido.produto_id = produto.id " +
				"AND produto.fornecedor_id = fornecedor.id " +
				"AND fornecedor.id = periodo.fornecedor_id " +
				"AND itenspedido.entregue = 0 " + 
				"AND produto.fornecedor_id = " + periodo.getFornecedor().getId() + 
				"AND pedido.datapedido between " + periodo.getDataInicial()  + " AND " + periodo.getDataFinal() +
				" ORDER BY 1", null);
		if (c.moveToFirst()) {
			do {
				new ControleCliente(context).atualizarSaldo(c.getLong(0), c.getDouble(1), context);
				cont++;
			}while (c.moveToNext());
		}
		periodo.setPedidoFeito(true);
		salvar(periodo);
		return cont;
	}

}
