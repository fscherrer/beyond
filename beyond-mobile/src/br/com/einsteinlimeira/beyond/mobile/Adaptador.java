package br.com.einsteinlimeira.beyond.mobile;

import java.util.List;

import br.com.einsteinlimeira.beyond.mobile.util.DateUtils;
import br.com.einsteinlimeira.beyond.model.Banda;
import br.com.einsteinlimeira.beyond.model.Evento;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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

		TextView textViewNomeEvento = (TextView) view
				.findViewById(R.id.nome_banda);
		Banda banda = eventos.get(positon).getBanda();
		textViewNomeEvento.setText(banda == null ? "" : banda.getNome());

		TextView textViewDataEvento = (TextView) view
				.findViewById(R.id.data_evento);
		textViewDataEvento.setText(DateUtils.dateFormat.format(eventos.get(
				positon).getDatahora()));

		return view;
	}

}
