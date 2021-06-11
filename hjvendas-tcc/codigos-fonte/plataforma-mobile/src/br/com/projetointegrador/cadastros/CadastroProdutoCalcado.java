package br.com.projetointegrador.cadastros;

import br.com.projetointegrador.R;
import br.com.projetointegrador.controles.ControleCorProduto;
import br.com.projetointegrador.controles.ControleFornecedor;
import br.com.projetointegrador.controles.ControleGeneroProduto;
import br.com.projetointegrador.controles.ControleParteDoCorpo;
import br.com.projetointegrador.controles.ControleProduto;
import br.com.projetointegrador.entidades.CorProduto;
import br.com.projetointegrador.entidades.Fornecedor;
import br.com.projetointegrador.entidades.GeneroProduto;
import br.com.projetointegrador.entidades.ParteDoCorpo;
import br.com.projetointegrador.entidades.Produto;
import br.com.projetointegrador.entidades.TipoProduto;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class CadastroProdutoCalcado extends Activity {
	
	private EditText campoDescricao;
	private EditText campoNome;
	private EditText campoValor;
	private EditText campoDuracao;
	private EditText campoTamanhoSalto;
	private EditText campoNumero;
	private Spinner campoGenero;
	private Spinner campoForenecedor;
	private Spinner campoPartedoCorpo;
	private Spinner campoCor;
	
	private Long id;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.frmcadcalcado);

		campoNome = (EditText) findViewById(R.id.campoNome);
		campoDescricao = (EditText) findViewById(R.id.campoDescricao);
		campoValor = (EditText) findViewById(R.id.campoValor);
		campoDuracao = (EditText) findViewById(R.id.campoDuracao);
		campoTamanhoSalto = (EditText) findViewById(R.id.campoSalto);
		campoNumero =  (EditText) findViewById(R.id.campoNumero);
		
		campoGenero = (Spinner) findViewById(R.id.boxGenero);
		campoForenecedor = (Spinner) findViewById(R.id.boxFornecedor);
		campoPartedoCorpo = (Spinner) findViewById(R.id.boxParteDoCorpo);
		campoCor = (Spinner) findViewById(R.id.boxCor);
		
		popularGenero();
		popularFornecedor();
		popularParteDoCorpo();
		popularCor();
		
		id = null;

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			id = extras.getLong(Produto.colunas[0]);
			if (id != null) {
				Produto c = buscarProduto(id);
				campoNome.setText(c.getNome());
				campoDescricao.setText(c.getDescMaterial());
				campoValor.setText(String.valueOf(c.getValor()));
				campoDuracao.setText(String.valueOf(c.getDuracao()));
				campoGenero.setSelection((int) selecionadoGenero(c.getGenero().getId()));
				campoForenecedor.setSelection((int) selecionadoFornecedor(c.getFornecedor().getId()));
				campoPartedoCorpo.setSelection(selecionadoParteDoCorpo(c.getParteDoCorpo().getId()));
				campoCor.setSelection(selecionadoCor(c.getCor().getId()));
				
				campoTamanhoSalto.setText(String.valueOf(c.getTamanhoSalto()));
				campoNumero.setText(String.valueOf(c.getNumero()));
			}
		}
	}



	private int selecionadoGenero(long id2) {
		int pos = 0;
		for (GeneroProduto posicao : new ControleGeneroProduto(getApplicationContext()).listarGeneroProduto()) {	
			if (posicao.getId() == id2)
				return pos;
			else
				pos++;
		}
		return -1;
	}
	
	private int selecionadoFornecedor(long id2) {
		int pos = 0;
		for (Fornecedor posicao : new ControleFornecedor(getApplicationContext()).listarFornecedor()) {	
			if (posicao.getId() == id2)
				return pos;
			else
				pos++;
		}
		return -1;
	}
	
	private int selecionadoParteDoCorpo(long id2) {
		int pos = 0;
		for (ParteDoCorpo posicao : new ControleParteDoCorpo(getApplicationContext()).listarParteDoCorpo()) {	
			if (posicao.getId() == id2)
				return pos;
			else
				pos++;
		}
		return -1;
	}
	
	private int selecionadoCor(long id2) {
		int pos = 0;
		for (CorProduto posicao : new ControleCorProduto(getApplicationContext()).listarCorProduto()) {	
			if (posicao.getId() == id2)
				return pos;
			else
				pos++;
		}
		return -1;
	}

	private void popularFornecedor() {
		campoForenecedor.setAdapter(new ArrayAdapter<Fornecedor>(getApplicationContext(), android.R.layout.simple_spinner_item, new ControleFornecedor(getApplicationContext()).listarFornecedor()));
	}
	
	private void popularGenero() {
		campoGenero.setAdapter(new ArrayAdapter<GeneroProduto>(CadastroProdutoCalcado.this,  android.R.layout.simple_spinner_item, new ControleGeneroProduto(getApplicationContext()).listarGeneroProduto()));
	}
	
	private void popularParteDoCorpo() {
		campoPartedoCorpo.setAdapter(new ArrayAdapter<ParteDoCorpo>(getApplicationContext(),  android.R.layout.simple_spinner_item, new ControleParteDoCorpo(getApplicationContext()).listarParteDoCorpo()));
	}
	
	private void popularCor() {
		campoCor.setAdapter(new ArrayAdapter<CorProduto>(getApplicationContext(),  android.R.layout.simple_spinner_item, new ControleCorProduto(getApplicationContext()).listarCorProduto()));
	}

	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
	}

	public void salvar(View v) {
		Produto produto = new Produto();
		if (id != null) {
			produto.setId(id);
		}
		
		produto.setProduto("CALCADO");
		produto.setNome(campoNome.getText().toString());
		produto.setDescMaterial(campoDescricao.getText().toString());
		produto.setValor(Double.parseDouble(campoValor.getText().toString()));
		produto.setDuracao(Long.parseLong(campoDuracao.getText().toString()));
		
		produto.setFornecedor((Fornecedor) campoForenecedor.getSelectedItem());
		produto.setGenero((GeneroProduto) campoGenero.getSelectedItem());
		produto.setCor((CorProduto) campoCor.getSelectedItem());
		produto.setParteDoCorpo((ParteDoCorpo) campoPartedoCorpo.getSelectedItem());
		
		produto.setNumero(Long.parseLong(campoNumero.getText().toString()));
		produto.setTamanhoSalto(Long.parseLong(campoTamanhoSalto.getText().toString()));
		
		produto.setVolume(-1L);
		produto.setTipoProduto(new TipoProduto());

		produto.setVolumeCosmetico("");
		produto.setTamanho("");  
		produto.setTempoDuracao("");
		
		salvarProduto(produto);
		setResult(RESULT_OK, new Intent());
		finish();
	}
	
	public void cancelar(View v) {
		onPause();
		CadastroProdutoCalcado.this.finish();
	}

	private Produto buscarProduto(long id) {
		return new ControleProduto(getApplicationContext()).buscarProduto(id, getApplicationContext());
	}

	private void salvarProduto(Produto produto) {
		new ControleProduto(getApplicationContext()).salvar(produto);
		
	}

}
