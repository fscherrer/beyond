package br.com.einsteinlimeira.beyond.mobile;

import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.ListView;

public class CasasActivity extends Activity {

	private ListView listViewListaCasas;
	private Button botaoVoltar, botaoAvancar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_casas);
	}

}
