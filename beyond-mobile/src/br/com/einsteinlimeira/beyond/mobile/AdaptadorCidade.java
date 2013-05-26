package br.com.einsteinlimeira.beyond.mobile;

import java.util.List;

import br.com.einsteinlimeira.beyond.model.Cidade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

public class AdaptadorCidade extends BaseAdapter {
	
	private List<Cidade> cidades;
	private Context contexto;
	
	public AdaptadorCidade(List<Cidade> cidades, Context contexto) {
		this.cidades = cidades;
		this.contexto = contexto;
	}

	@Override
	public int getCount() {
		return cidades.size();
	}

	@Override
	public Object getItem(int position) {
		return cidades.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View view = inflater.inflate(R.layout.activity_linha_cidades, null);
		
		CheckBox checkbox = (CheckBox) view.findViewById(R.id.checkbox_cidades);
		checkbox.setText(cidades.get(position).getNome());
		
		return view;
	}
	
	public void setCidades(List<Cidade> cidades){
		this.cidades = cidades;
	}

}
