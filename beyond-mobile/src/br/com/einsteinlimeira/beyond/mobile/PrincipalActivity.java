package br.com.einsteinlimeira.beyond.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PrincipalActivity extends Activity {
	
	private Button botaoCasa, botaoCidade, botaoBanda, botaoEstilo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		
		botaoCasa = (Button) findViewById(R.id.btn_casa);
		botaoCasa.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PrincipalActivity.this, CasasActivity.class);
				startActivity(intent);
			}
		});
	}

}
