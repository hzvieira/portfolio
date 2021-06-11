package br.com.projetointegrador.cadastros;

import br.com.projetointegrador.R;
import br.com.projetointegrador.controles.ControleCidade;
import br.com.projetointegrador.controles.ControleCliente;
import br.com.projetointegrador.controles.ControleGeneroProduto;
import br.com.projetointegrador.controles.ControleRelacionamento;
import br.com.projetointegrador.controles.ControleTipoRelacionamento;
import br.com.projetointegrador.entidades.Cliente;
import br.com.projetointegrador.entidades.GeneroProduto;
import br.com.projetointegrador.entidades.Relacionamento;
import br.com.projetointegrador.entidades.TipoRelacionamento;
import br.com.projetointegrador.listas.ListaCliente;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CadastroRelacionamento extends Activity {
	
	private static final int BUSCAR_CLIENTE = 1;
	
	public static ControleGeneroProduto repositorioGenero;
	public static ControleTipoRelacionamento repositorioTipoRelacionamento;
	private EditText campoNome;
	private Spinner campoGenero;
	private EditText campoTelefone1;
	private EditText campoTelefone2;
	private Spinner campoRelacionamento;
	private Spinner campoAtivo;
		
	private Long id;
	private Relacionamento c = new Relacionamento();
	//private Cliente cliente = new Cliente();
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.frmcadrelacionamento);

		campoNome = (EditText) findViewById(R.id.campoNome);
		campoGenero = (Spinner) findViewById(R.id.boxGenero);
		campoTelefone1 = (EditText) findViewById(R.id.campoTelefone1);
		campoTelefone2 = (EditText) findViewById(R.id.campoTelefone2);
		campoRelacionamento = (Spinner) findViewById(R.id.boxRelacionamento);
		campoAtivo = (Spinner) findViewById(R.id.boxAtivo);
		
		popularSpinner();
		
		id = null;

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			id = extras.getLong(Relacionamento.colunas[0]);
			if (id != null) {
				c = buscarRelacionamento(id);
				campoAtivo.setSelection(selecionarAtivo(c.isAtivo()));
				campoGenero.setSelection(selecionarGenero(c.getRelacionamento().getGenero()));
				campoRelacionamento.setSelection(selecionarRelacionamento(c.getTipoRelacionamento()));
				
				campoNome.setText(c.getRelacionamento().getNome());
				campoTelefone1.setText(c.getRelacionamento().getTelefoneResidencial());
				campoTelefone2.setText(c.getRelacionamento().getTelefoneCelular());
			}
		}
	}

	private int selecionarRelacionamento(TipoRelacionamento tipoRelacionamento) {
		int pos = 0;
		for (TipoRelacionamento posicao : new ControleTipoRelacionamento(getApplicationContext()).listarTipoRelacionamento()) {	
			if (posicao.equals(tipoRelacionamento))
				return pos;
			else
				pos++;
		}
		return -1;
	}

	private int selecionarAtivo(boolean ativo) {
		if (ativo)
			return 0;
		else 
			return 1;
	}

	private int selecionarGenero(GeneroProduto string) {
		int pos = 0;
		for (GeneroProduto posicao : new ControleGeneroProduto(getApplicationContext()).listarGeneroProduto()) {	
			if (posicao.equals(string))
				return pos;
			else
				pos++;
		}
		return -1;
	}
	
	private void popularSpinner() {
		repositorioGenero = new ControleGeneroProduto(this);
		repositorioTipoRelacionamento = new ControleTipoRelacionamento(this);
		campoGenero.setAdapter(new ArrayAdapter<GeneroProduto>(CadastroRelacionamento.this,  android.R.layout.simple_spinner_item, repositorioGenero.listarGeneroProduto()));
		campoRelacionamento.setAdapter(new ArrayAdapter<TipoRelacionamento>(CadastroRelacionamento.this, android.R.layout.simple_spinner_item, repositorioTipoRelacionamento.listarTipoRelacionamento()));
		
		campoAtivo.setAdapter(new ArrayAdapter<String>(CadastroRelacionamento.this, android.R.layout.simple_spinner_item, new String[] {"Sim", "Não"}));
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		if (!(c.getRelacionamento().getNome() == null)) {
			campoNome.setText(c.getRelacionamento().getNome());
			campoTelefone1.setText(c.getRelacionamento().getTelefoneResidencial());
			campoTelefone2.setText(c.getRelacionamento().getTelefoneCelular());
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
	}

	public void salvar(View v) {
		GeneroProduto genero = (GeneroProduto) campoGenero.getSelectedItem();
		TipoRelacionamento tipoRelacionamento = (TipoRelacionamento) campoRelacionamento.getSelectedItem();
		String ativo = (String) campoAtivo.getSelectedItem();
		Cliente relacionado = new Cliente();
		
		if (c.getId() != 0) {
			relacionado = c.getRelacionamento();
		} else {
			relacionado.setSaldo(0d);
		}
		long idCad = 0;
		
		relacionado.setNome(campoNome.getText().toString());
		relacionado.setGenero(new ControleGeneroProduto(getApplicationContext()).buscarGeneroProduto(genero.getId()));
		relacionado.setTelefoneResidencial(campoTelefone1.getText().toString());
		relacionado.setTelefoneCelular(campoTelefone2.getText().toString());
		relacionado.setCidade(relacionado.getCidade() == null ? new ControleCidade(getApplicationContext()).buscarCidade(1) : relacionado.getCidade());
		relacionado.setSaldo(0d);
		
		if (relacionado.getNome().length() == 0) {
			Toast.makeText(getApplicationContext(), "Preencha o nome corretamente", Toast.LENGTH_LONG).show();
		} else if (relacionado.getTelefoneResidencial().length() < 8) {
			Toast.makeText(getApplicationContext(), "Preencha o telefone rasidencial corretamente!", Toast.LENGTH_LONG).show();
		} else {
			idCad = new ControleCliente(getApplicationContext()).salvar(relacionado);
			Toast.makeText(getApplicationContext(), "Cadastro do relacionado realizado com sucesso!", Toast.LENGTH_LONG).show();
			
			setResult(RESULT_OK, new Intent().putExtra("Relacionamento", new String[] {
					id == null ? "0" : String.valueOf(id),
					String.valueOf(idCad),
					String.valueOf(tipoRelacionamento.getId()),
					ativo,
					}
			));
			finish();
		}
	}
	
	public void cancelar(View v) {
		onPause();
		CadastroRelacionamento.this.finish();
	}
	
	public void buscar(View v) {
		startActivityForResult(new Intent(getApplicationContext(), ListaCliente.class), BUSCAR_CLIENTE);
	}

	private Relacionamento buscarRelacionamento(long id) {
		return new ControleRelacionamento(getApplicationContext()).buscarRelacionamento(id, getApplicationContext());
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
       	if (requestCode == BUSCAR_CLIENTE) {
       		if (resultCode == RESULT_OK) {
       			c.setRelacionamento(new ControleCliente(getApplicationContext()).buscarCliente(data.getLongExtra(Cliente.colunas[0], 0), getApplicationContext()));
       		}
       	}
		
	}
}
