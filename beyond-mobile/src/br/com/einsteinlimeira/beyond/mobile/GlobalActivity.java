package br.com.einsteinlimeira.beyond.mobile;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class GlobalActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_configuracoes:
			exibirActivityConfiguracoes();
			break;
		}

		return true;
	}

	/**
	 * Exibe a Activity para realização de configurações.
	 */
	private void exibirActivityConfiguracoes() {
		Intent intent = new Intent(this, ConfiguracaoActivity.class);
		startActivity(intent);
	}
}
