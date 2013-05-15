package br.com.einsteinlimeira.beyond.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class PrincipalActivity extends Activity {
	
	private ImageButton botaoCasa, botaoCidade, botaoBanda, botaoEstilo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		
		botaoCasa = (ImageButton) findViewById(R.id.btn_casa);
		botaoCasa.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PrincipalActivity.this, CasasActivity.class);
				startActivity(intent);
			}
		});
		
		botaoCidade = (ImageButton) findViewById(R.id.btn_cidade);
		botaoCidade.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO intent for open activity cidade.
				
			}
		});
		
		botaoBanda = (ImageButton) findViewById(R.id.btn_banda);
		botaoBanda.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO intent for open activity banda.
				
			}
		});
		
		botaoEstilo = (ImageButton) findViewById(R.id.btn_estilo);
		botaoEstilo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO intent for open activity estilo.
				
			}
		});
	}

}
