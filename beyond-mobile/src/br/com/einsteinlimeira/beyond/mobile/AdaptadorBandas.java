package br.com.einsteinlimeira.beyond.mobile;

import java.util.List;

import br.com.einsteinlimeira.beyond.model.Banda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

public class AdaptadorBandas extends BaseAdapter {

	private List<Banda> bandas;
	private Context contexo;
	
	public AdaptadorBandas(List<Banda> bandas, Context contexto) {
		this.bandas = bandas;
		this.contexo = contexto;
	}
	
	@Override
	public int getCount() {
		return bandas.size();
	}

	@Override
	public Object getItem(int position) {
		return bandas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater)contexo.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View view = inflater.inflate(R.layout.activity_linha_banda, null);
		
		CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox_bandas);
		checkbox.setText(bandas.get(position).getNome());
		
		return view;
	}

}
