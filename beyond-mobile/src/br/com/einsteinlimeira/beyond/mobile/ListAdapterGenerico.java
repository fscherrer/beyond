package br.com.einsteinlimeira.beyond.mobile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * Adapter genérico.
 *
 * @param <T>
 *   Tipo dos elementos.
 * @param <I>
 *   Tipo do identificador dos elementos.
 */
public abstract class ListAdapterGenerico<T, I> extends BaseAdapter {
  /**
   * Lista de elementos. Modelo.
   */
  private List<T> elementos;

  /**
   * Contexto.
   */
  private Context contexto;

  /**
   * Listener para perceber linhas selecionadas/desselecionadas.
   */
  private OnCheckedChangeListener onCheckedChangeListener;

  /**
   * Para armazenar os identificadores dos elementos selecionados.
   */
  private Set<I> elementosSelecionados;

  /**
   * Cria um Adapter para os <code>elementos</code> informados.
   * 
   * @param elementos
   *   Elementos. Modelo.
   * @param contexto
   *   Contexto.
   */
  public ListAdapterGenerico(List<T> elementos, Context contexto) {
    this.elementos = elementos;
    this.contexto = contexto;

    elementosSelecionados = new HashSet<I>();

    onCheckedChangeListener = new OnCheckedChangeListener() {

      @SuppressWarnings("unchecked")
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
          elementosSelecionados.add((I) buttonView.getTag());
        }
        else {
          elementosSelecionados.remove((I) buttonView.getTag());
        }
      }
    };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return elementos.size();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getItem(int position) {
    return elementos.get(position);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public long getItemId(int position) {
    return position;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    LayoutInflater inflater = (LayoutInflater) contexto
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    View view = inflater.inflate(R.layout.linha_listview, null);

    CheckBox checkbox = (CheckBox) view.findViewById(R.id.linha_listview_checkbox);
    
    T elemento = elementos.get(position);
    
    checkbox.setText(getText(elemento));
    
    // a partir dessa tag será possível identificar para qual elemento é esse check
    checkbox.setTag(getIdentificador(elemento));
    
    // se esse elemento já está selecionado, precisa retornar a view com o check
    // marcado (lembre-se: a view é gerada todas as vezes que entra na área visível)
    checkbox.setChecked(elementosSelecionados.contains(getIdentificador(elemento)));
    
    checkbox.setOnCheckedChangeListener(onCheckedChangeListener);

    return view;
  }

  /**
   * Retorna o texto a ser exibido na linha para esse <code>elemento</code>.
   * 
   * @param elemento
   *   Elemento do qual deseja-se obter o texto a ser exibido.
   *   
   * @return
   *   Representação desse <code>elemento</code>.
   */
  public abstract String getText(T elemento);
  
  /**
   * Retorna o Identificador para esse <code>elemento</code> (deve ser único para cada elemento).
   * 
   * @param elemento
   *   Elemento do qual deseja-se obter o identificador.
   *   
   * @return
   *   Identificador desse <code>elemento</code>.
   */
  public abstract I getIdentificador(T elemento);

  /**
   * Define os <code>elementos</code> que compõem o modelo.
   * 
   * @param elementos
   *   Elementos.
   */
  public void setElementos(List<T> elementos) {
    this.elementos = elementos;
    elementosSelecionados.clear();
  }

  /**
   * Retorna os Identificadores dos Elementos selecionadao.
   * 
   * @return
   *   Identificadores dos Elementos selecionados.
   */
  public List<I> getIdentificadoresElementosSelecionados() {
    return new ArrayList<I>(elementosSelecionados);
  }
  
  /**
   * Define os Identificadores dos Elementos já selecionados.
   * 
   * @param identificadoresElementosSelecionados
   *   Identificadores dos Elementos já selecionados.
   */
  public void setIdentificadoresElementosSelecionados(Collection<I> identificadoresElementosSelecionados){
    this.elementosSelecionados.addAll(identificadoresElementosSelecionados);
  }
}
