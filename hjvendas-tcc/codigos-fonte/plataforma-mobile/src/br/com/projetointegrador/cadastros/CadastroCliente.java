package br.com.projetointegrador.cadastros;

import java.util.ArrayList;
import java.util.List;

import br.com.projetointegrador.R;
import br.com.projetointegrador.controles.ControleCidade;
import br.com.projetointegrador.controles.ControleCliente;
import br.com.projetointegrador.controles.ControleGeneroProduto;
import br.com.projetointegrador.controles.ControleRelacionamento;
import br.com.projetointegrador.controles.ControleTipoRelacionamento;
import br.com.projetointegrador.entidades.Cidade;
import br.com.projetointegrador.entidades.Cliente;
import br.com.projetointegrador.entidades.GeneroProduto;
import br.com.projetointegrador.entidades.Relacionamento;
import br.com.projetointegrador.listas.ListaCliente;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CadastroCliente extends Activity {
	
	private EditText campoNome;
	private EditText campoTelefoneRes;
	private EditText campoTelefoneCel;
	private EditText campoEmail01;
	private EditText campoEmail02;
	private EditText campoRua;
	private EditText campoNumero;
	private EditText campoComplemento;
	private EditText campoBairro;
	private EditText campoCep;
	private EditText campoDataNascimento;
	private EditText campoObservacao;
	private EditText campoProfissao;
	private EditText campoRenda;
	private EditText campoSaldo;
	private Spinner campoCidade;
	private Spinner campoGenero;
	private ListView lista;
	
	private List<Relacionamento> clientesRelacao = new ArrayList<Relacionamento>();
	private Long id;
	
	private AlertDialog.Builder builder;
	private AlertDialog alerta;
	private Relacionamento clickList;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.frmcadcliente); 

		campoNome = (EditText) findViewById(R.id.campoNome);
		campoTelefoneRes = (EditText) findViewById(R.id.campoTelefone1);
		campoTelefoneCel = (EditText) findViewById(R.id.campoTelefone2);
		campoEmail01 = (EditText) findViewById(R.id.campoEmail1);
		campoEmail02 = (EditText) findViewById(R.id.campoEmail2);
		campoRua = (EditText) findViewById(R.id.campoEndereco);
		campoNumero = (EditText) findViewById(R.id.campoNumero);
		campoComplemento = (EditText) findViewById(R.id.campoComplemento);
		campoBairro = (EditText) findViewById(R.id.campoBairro);
		campoCep = (EditText) findViewById(R.id.campoCep);
		campoObservacao = (EditText) findViewById(R.id.campoObservacoes);
		campoDataNascimento = (EditText) findViewById(R.id.campoNascimento);
		campoProfissao = (EditText) findViewById(R.id.campoProfissao);
		campoRenda = (EditText) findViewById(R.id.campoRenda);
		campoSaldo = (EditText) findViewById(R.id.campoSaldo);
		campoCidade = (Spinner) findViewById(R.id.boxCidade);
		campoGenero = (Spinner) findViewById(R.id.boxGenero);
		lista = (ListView) findViewById(R.id.lista);
		
		popularCidade();
		popularGenero();
		
		id = null;

		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			id = extras.getLong(Cliente.colunas[0]);
			if (id != null) {
				atualizarRelacao(id);
				Cliente c = buscarCliente(id);
				campoNome.setText(c.getNome());
				campoTelefoneRes.setText(c.getTelefoneResidencial());
				campoTelefoneCel.setText(c.getTelefoneCelular());
				campoEmail01.setText(c.getEmail01());
				campoEmail02.setText(c.getEmail02());
				campoRua.setText(c.getRua());
				campoNumero.setText(String.valueOf(c.getNumero()));
				campoComplemento.setText(c.getComplemento());
				campoBairro.setText(c.getBairro());
				campoCep.setText(c.getCep());
				campoDataNascimento.setText(c.getNascimento());
				campoCidade.setSelection((int) selecionado(c.getCidade().getId()));
				campoGenero.setSelection(selecionadoGenero(c.getGenero().getId()));
				campoObservacao.setText(c.getObservacoes());
				campoProfissao.setText(c.getProfissao());
				campoRenda.setText(c.getRenda().toString());
				campoSaldo.setText(c.getSaldo().toString());
				
								
				lista.setAdapter(new ArrayAdapter<Relacionamento>(getApplicationContext(), android.R.layout.simple_spinner_item, clientesRelacao));
			}
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		setResult(RESULT_CANCELED);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		verificaDuplicados();
		atualizarLista();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		atualizarLista();
		
		lista.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,	int arg2, long arg3) {
				clickList = (Relacionamento) arg0.getItemAtPosition(arg2);
				
				LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				View botoes = layoutInflater.inflate(R.layout.botoestelarelacionamento, null);
				
				builder = new AlertDialog.Builder(CadastroCliente.this);
				alerta = builder.setView(botoes).create();
				alerta.setTitle("Opções");
				alerta.setMessage("Escolha uma operação");
				alerta.show();
			}
		});
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		new ControleRelacionamento(getApplicationContext()).apagarRelacionamentos(getApplicationContext());
	}
	
	private void atualizarLista() {
		lista.setAdapter(new ArrayAdapter<Relacionamento>(getApplicationContext(), android.R.layout.simple_spinner_item, clientesRelacao));	
	}
	
	private void atualizarRelacao(Long id2) {
		clientesRelacao = new ControleRelacionamento(getApplicationContext()).listarRelacionamento(id2, getApplicationContext());
	}

	private void popularCidade() {
		campoCidade.setAdapter(new ArrayAdapter<Cidade>(CadastroCliente.this,  android.R.layout.simple_spinner_item, new ControleCidade(getApplicationContext()).listarCidades()));
	}
	
	private void popularGenero() {
		campoGenero.setAdapter(new ArrayAdapter<GeneroProduto>(CadastroCliente.this,  android.R.layout.simple_spinner_item, new ControleGeneroProduto(getApplicationContext()).listarGeneroProduto()));
	}
	
	private int selecionado(long id2) {
		int pos = 0;
		for (Cidade posicao : new ControleCidade(getApplicationContext()).listarCidades()) {	
			if (posicao.getId() == id2)
				return pos;
			else
				pos++;
		}
		return -1;
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
	
	public void salvar(View v) {
		Cliente cliente = new Cliente();
		if (id != null) {
			cliente.setId(id);
		}
		
		cliente.setNome(campoNome.getText().toString());
		cliente.setTelefoneResidencial(campoTelefoneRes.getText().toString());
		cliente.setTelefoneCelular(campoTelefoneCel.getText().toString());
		cliente.setEmail01(campoEmail01.getText().toString());
		cliente.setEmail02(campoEmail02.getText().toString());
		cliente.setRua(campoRua.getText().toString());
		cliente.setNumero(!campoNumero.getText().toString().trim().equals("") ? Long.parseLong(campoNumero.getText().toString()) : 0);
		cliente.setComplemento(campoComplemento.getText().toString());
		cliente.setBairro(campoBairro.getText().toString());
		cliente.setCep(campoCep.getText().toString());
		cliente.setCidade((Cidade) campoCidade.getSelectedItem());
		cliente.setObservacoes(campoObservacao.getText().toString());
		
		cliente.setNascimento(campoDataNascimento.getText().toString());
		cliente.setProfissao(campoProfissao.getText().toString());
		cliente.setRenda(campoRenda.length() == 0 ? 0d : Double.parseDouble(campoRenda.getText().toString()));
		cliente.setSaldo(campoSaldo.length() == 0 ? 0d : Double.parseDouble(campoSaldo.getText().toString()));
		cliente.setGenero((GeneroProduto) campoGenero.getSelectedItem());
		
		if (cliente.getNome().length() == 0) {
			Toast.makeText(getApplicationContext(), "Preencha o nome corretamente", Toast.LENGTH_LONG).show();
		} else if (cliente.getNascimento().length() != 10) {
			Toast.makeText(getApplicationContext(), "Preencha a data no formato dd/MM/aaaa!", Toast.LENGTH_LONG).show();	
		} else if (cliente.getTelefoneResidencial().length() < 8) {
			Toast.makeText(getApplicationContext(), "Preencha o telefone rasidencial corretamente!", Toast.LENGTH_LONG).show();
		} else if (cliente.getRua().length() < 5) {
			Toast.makeText(getApplicationContext(), "Preencha o endereço corretamente!", Toast.LENGTH_LONG).show();			
		} else {
				
			id = salvarCliente(cliente);
		
			for(Relacionamento relacionamento : clientesRelacao ) {
				relacionamento.setCliente(buscarCliente(id));
				new ControleRelacionamento(getApplicationContext()).salvar(relacionamento);
			}

			setResult(RESULT_OK, new Intent());
			finish();
		}
	}
	
	public void cancelar(View v) {
		onPause();
		CadastroCliente.this.finish();
	}
	
	public void novo(View v){
		startActivityForResult(new Intent(CadastroCliente.this, CadastroRelacionamento.class), 1);
	}

	private Cliente buscarCliente(long id) {
		return new ControleCliente(getApplicationContext()).buscarCliente(id, getApplicationContext());
	}
	
	private long salvarCliente(Cliente cliente) {
		return ListaCliente.repositorio.salvar(cliente);
	}
		
	public void alterar(View v) {
		startActivityForResult(new Intent(getApplicationContext(), CadastroRelacionamento.class).putExtra(Relacionamento.colunas[0], clickList.getId()), 1);

		alerta.cancel();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Relacionamento relacionamento = new Relacionamento();
		
		if (requestCode == 1 && resultCode == RESULT_OK) {
			String[] dados = data.getStringArrayExtra("Relacionamento");
			
			if (!dados[0].equals("0")) {
				relacionamento = new ControleRelacionamento(getApplicationContext()).buscarRelacionamento(Long.parseLong(dados[0]), getApplicationContext());
			}

			relacionamento.setRelacionamento(new ControleCliente(getApplicationContext()).buscarCliente(Long.parseLong(dados[1]),getApplicationContext()));
			relacionamento.setTipoRelacionamento(new ControleTipoRelacionamento(getApplicationContext()).buscarTipoRelacionamento(Long.parseLong(dados[2])));
			relacionamento.setAtivo(dados[3].equals("Sim")? true : false);
			relacionamento.setCliente(new Cliente());
			relacionamento.setId(new ControleRelacionamento(getApplicationContext()).salvar(relacionamento));
			clientesRelacao.add(relacionamento);
		}
	}

    private void verificaDuplicados() {
        List<Relacionamento> aExcluir = new ArrayList<Relacionamento>();
        for (int i = 0; i < clientesRelacao.size(); i++) {
            for (int j = i + 1; j < clientesRelacao.size(); j++) {
            	Relacionamento itemAtual = clientesRelacao.get(i);
            	Relacionamento itemDuplicado = clientesRelacao.get(j);
                if (itemAtual.getId() == itemDuplicado.getId()) {
                    itemDuplicado.setId(j);
                    aExcluir.add(itemAtual);
                }
            }
        }
        for (Relacionamento itemDuplicado : aExcluir) {
        	clientesRelacao.remove(itemDuplicado);
        }
    }

}
