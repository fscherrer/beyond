package br.com.einsteinlimeira.beyond.mobile;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.einsteinlimeira.beyond.mobile.model.EventoSimplificadoDTO;
import br.com.einsteinlimeira.beyond.mobile.util.DateUtils;

public class AdaptadorEvento extends BaseAdapter {

	private List<EventoSimplificadoDTO> eventos;
	private Context contexto;

	public AdaptadorEvento(List<EventoSimplificadoDTO> eventos, Context contexto) {
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
        positon).getDataHora()));
		
		TextView textViewNomeEvento = (TextView) view
		    .findViewById(R.id.nome_evento);
		
		textViewNomeEvento.setText(eventos.get(positon).getNome());

		TextView textViewDataEvento = (TextView) view
				.findViewById(R.id.data_evento);
		textViewDataEvento.setText(DateUtils.dateFormat.format(eventos.get(
				positon).getDataHora()));

		return view;
	}

	public void setEventos(List<EventoSimplificadoDTO> eventos) {
    this.eventos = eventos;
  }
}
