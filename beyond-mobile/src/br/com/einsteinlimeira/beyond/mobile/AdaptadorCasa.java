package br.com.einsteinlimeira.beyond.mobile;

import java.util.List;

import br.com.einsteinlimeira.beyond.model.Casa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

public class AdaptadorCasa extends BaseAdapter {

	private List<Casa> casas;
	private Context contexto;
	
	public AdaptadorCasa(List<Casa> casas, Context contexto){
		this.casas = casas;
		this.contexto = contexto;
	}
	
	@Override
	public int getCount() {
		return casas.size();
	}

	@Override
	public Object getItem(int position) {
		return casas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) contexto.
				getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View view = inflater.inflate(R.layout.activity_linha_casas, null);
		
		CheckBox checkBoxCasa = (CheckBox) view.findViewById(R.id.checkbox_casas);
		checkBoxCasa.setText(casas.get(position).getNome());
		
		return view;
	}
	
	public void setCasas(List<Casa> casas){
		this.casas = casas;
	}
}
