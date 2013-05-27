package br.com.einsteinlimeira.beyond.web;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 * Implementação de interceptor para log de eventos.
 */
@Interceptor
@LogInterceptor
public class LogInterceptorImpl implements Serializable {

  /**
   * Logger para realizar o logging em si.
   */
  private final static Logger LOGGER = Logger.getLogger(LogInterceptorImpl.class.getName());

  /**
   * Mensagen a ser logada quando métodos são chamados.
   */
  private static final String MENSAGEM_METODO = "%s.%s sendo invocado";

  /**
   * Loga a chamada ao método.
   * 
   * @param contexto
   *   Contexto da chamada ao método.
   * 
   * @return 
   *   Valor de retorno do próximo método da cadeia.
   * 
   * @throws Exception se ocorrer algum problema nas chamadas aos próximos métodos da cadeia.
   */
  @AroundInvoke
  public Object logar(InvocationContext contexto) throws Exception {
    Method method = contexto.getMethod();
    Object target = contexto.getTarget();

    LOGGER.log(Level.INFO, String.format(MENSAGEM_METODO, target.toString(), method.getName()));

    return contexto.proceed();
  }
}