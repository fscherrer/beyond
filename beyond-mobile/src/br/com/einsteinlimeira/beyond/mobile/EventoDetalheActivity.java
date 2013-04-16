package br.com.einsteinlimeira.beyond.mobile;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;
import br.com.einsteinlimeira.beyond.mobile.util.DateUtils;
import br.com.einsteinlimeira.beyond.mobile.util.EntidadeUtils;
import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.model.Endereco;
import br.com.einsteinlimeira.beyond.model.Evento;

public class EventoDetalheActivity extends GlobalActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evento_detalhe);

		Evento evento = (Evento) getIntent().getExtras().getSerializable(
				"evento");

		Resources resources = getResources();

		((TextView) findViewById(R.id.evento_texto_titulo)).setText(evento
				.getNome());
		
		Casa casa = evento.getCasa();
		Endereco endereco = casa.getEndereco();

		((TextView) findViewById(R.id.evento_texto_casa)).setText(casa.getNome());
		
		((TextView) findViewById(R.id.evento_texto_local_logradouro)).setText(resources
				.getString(R.string.evento_local_logradouro, endereco.getLogradouro(),
						endereco.getNumero(), endereco.getBairro()));
		
		((TextView) findViewById(R.id.evento_texto_local_cidade)).setText(resources
				.getString(R.string.evento_local_cidade, endereco.getCidade().getNome(),
						endereco.getCidade().getUf().getSigla(), endereco.getCep()));

		((TextView) findViewById(R.id.evento_texto_banda)).setText(resources
				.getString(R.string.evento_banda,
						EntidadeUtils.bandasToString(evento.getBandas())));

		((TextView) findViewById(R.id.evento_texto_data)).setText(resources
				.getString(R.string.evento_data,
						DateUtils.dateHourFormat.format(evento.getDatahora())));

		((TextView) findViewById(R.id.evento_texto_valor)).setText(resources
				.getString(R.string.evento_valor, evento.getValor()));

	}
}
