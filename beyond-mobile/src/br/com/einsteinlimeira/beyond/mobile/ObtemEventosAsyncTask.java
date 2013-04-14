package br.com.einsteinlimeira.beyond.mobile;

import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import br.com.einsteinlimeira.beyond.mobile.services.EventoServices;
import br.com.einsteinlimeira.beyond.model.Evento;

public class ObtemEventosAsyncTask extends AsyncTask<Void, Void, ArrayList<Evento>> {
  
  protected boolean problema;
  private Context contexto;
  

  public ObtemEventosAsyncTask(Context contexto) {
    this.contexto = contexto;
  }

  @Override
  protected ArrayList<Evento> doInBackground(Void... params) {
    try {
      return new ArrayList<Evento>(
          new EventoServices().getEventos(contexto));
    }
    catch (Exception e) {
      Log.e(Constantes.TAG,
          contexto.getResources().getString(
              R.string.global_erro_requisicao), e);
      problema = true;
    }

    return null;
  }
}