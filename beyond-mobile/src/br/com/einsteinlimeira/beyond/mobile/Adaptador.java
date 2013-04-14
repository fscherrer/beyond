package br.com.einsteinlimeira.beyond.mobile;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.einsteinlimeira.beyond.mobile.util.DateUtils;
import br.com.einsteinlimeira.beyond.mobile.util.EntidadeUtils;
import br.com.einsteinlimeira.beyond.model.Banda;
import br.com.einsteinlimeira.beyond.model.Evento;

public class Adaptador extends BaseAdapter {

	private List<Evento> eventos;
	private Context contexto;

	public Adaptador(List<Evento> eventos, Context contexto) {
		this.eventos = eventos;
		this.contexto = contexto;
	}

	@Override
	public int getCount() {
		return eventos.size();
	}

	@Override
	public Object getItem(int position) {
		return eventos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int positon, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) contexto
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.activity_linha_eventos, null);

		((TextView) view.findViewById(R.id.dia_evento)).setText(DateUtils.diaFormat.format(eventos.get(
        positon).getDatahora()));
		
		TextView textViewNomeEvento = (TextView) view
		    .findViewById(R.id.nome_banda);
		List<Banda> bandas = eventos.get(positon).getBandas();
		
		textViewNomeEvento.setText(EntidadeUtils.bandasToString(bandas));

		TextView textViewDataEvento = (TextView) view
				.findViewById(R.id.data_evento);
		textViewDataEvento.setText(DateUtils.dateFormat.format(eventos.get(
				positon).getDatahora()));

		return view;
	}

	public void setEventos(List<Evento> eventos) {
    this.eventos = eventos;
  }
}
