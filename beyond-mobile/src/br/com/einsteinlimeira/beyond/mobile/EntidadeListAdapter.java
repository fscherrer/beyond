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
import br.com.einsteinlimeira.beyond.model.Entidade;

/**
 * Adapter padrão para {@link Entidade}s.
 *
 * @param <E>
 *   Tipo da Entidade.
 */
public abstract class EntidadeListAdapter<E extends Entidade> extends BaseAdapter {
  /**
   * Lista de entidades. Modelo.
   */
  private List<E> entidades;

  /**
   * Contexto.
   */
  private Context contexto;

  /**
   * Listener para perceber linhas selecionadas/desselecionadas.
   */
  private OnCheckedChangeListener onCheckedChangeListener;

  /**
   * Para armazenar os IDs das Entidades selecionadas.
   */
  private Set<Integer> idsEntidadesSelecionadas;

  /**
   * Cria um Adapter para as <code>entidades</code> informadas.
   * 
   * @param entidades
   *   Entidades. Modelo.
   * @param contexto
   *   Contexto.
   */
  public EntidadeListAdapter(List<E> entidades, Context contexto) {
    this.entidades = entidades;
    this.contexto = contexto;

    idsEntidadesSelecionadas = new HashSet<Integer>();

    onCheckedChangeListener = new OnCheckedChangeListener() {

      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
          idsEntidadesSelecionadas.add((Integer) buttonView.getTag());
        }
        else {
          idsEntidadesSelecionadas.remove((Integer) buttonView.getTag());
        }
      }
    };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return entidades.size();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getItem(int position) {
    return entidades.get(position);
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
    
    E entidade = entidades.get(position);
    
    checkbox.setText(getText(entidade));
    
    // a partir dessa tag será possível identificar para qual entidade é esse check
    checkbox.setTag(entidade.getId());
    
    // se essa entidade já está selecionada, precisa retornar a view com o check
    // marcado (lembre-se: a view é gerada todas vez que entra na área visível)
    checkbox.setChecked(idsEntidadesSelecionadas.contains(entidade.getId()));
    
    checkbox.setOnCheckedChangeListener(onCheckedChangeListener);

    return view;
  }

  /**
   * Retorna o texto a ser exibido na linha para essa <code>entidade</code>.
   * 
   * @param entidade
   *   Entidade da qual deseja-se obter o texto a ser exibido.
   *   
   * @return
   *   Representação dessa <code>entidade</code>.
   */
  public abstract String getText(E entidade);

  /**
   * Define as <code>entidades</code> que compõem o modelo.
   * 
   * @param entidades
   *   Entidades.
   */
  public void setEntidades(List<E> entidades) {
    this.entidades = entidades;
    idsEntidadesSelecionadas.clear();
  }

  /**
   * Retorna os IDs das Entidades selecionadas.
   * 
   * @return
   *   IDs das Entidades selecionadas.
   */
  public List<Integer> getIdsEntidadesSelecionadas() {
    return new ArrayList<Integer>(idsEntidadesSelecionadas);
  }
  
  /**
   * Define os IDs das Entidades já selecionadas.
   * 
   * @param idsEntidadesSelecionadas
   *   IDs das Entidades já selecionadas.
   */
  public void setIdsEntidadesSelecionadas(Collection<Integer> idsEntidadesSelecionadas){
    this.idsEntidadesSelecionadas.addAll(idsEntidadesSelecionadas);
  }
}
