package br.com.einsteinlimeira.beyond.mobile.util;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Parcelable genérico para {@link List}.
 *
 * @param <T>
 *   Tipo de elementos carregados na lista.
 */
public class ParcelableList<T> implements Parcelable {
  private List<T> list;

  /**
   * Cria um Parcelable contendo a <code>list</code>.
   * 
   * @param list
   *   Lista de elementos.
   */
  public ParcelableList(List<T> list) {
    this.list = list;
  }

  /**
   * Retorna a lista de elementos.
   * 
   * @return
   *   A lista de elementos.
   */
  public List<T> getList() {
    return list;
  }

  private ParcelableList(Parcel in) {
    list = new ArrayList<T>();
    in.readList(list, null);
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeList(list);
  }

  @SuppressWarnings("unused")
  private void readFromParcel(Parcel in) {
    list = new ArrayList<T>();
    in.readList(list, null);
  }

  @SuppressWarnings("rawtypes")
  public static final Creator<ParcelableList> CREATOR =
      new Creator<ParcelableList>() {

        @Override
        public ParcelableList[] newArray(int size) {
          return new ParcelableList[size];
        }

        @Override
        public ParcelableList createFromParcel(Parcel source) {
          return new ParcelableList(source);
        }
      };
}
