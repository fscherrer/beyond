package br.com.einsteinlimeira.beyond.web.servlets;

import br.com.einsteinlimeira.beyond.protocol.Requisicao;
import br.com.einsteinlimeira.beyond.protocol.RequisicaoEvento;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet que representa um controlador frontal para tratamento de requisições realcionadas a esse
 * contexto de aplicação.
 */
@WebServlet(name = "FrontControllerServlet", urlPatterns = {"/FrontControllerServlet"})
public class FrontControllerServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processaRequisicao(request, response);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    processaRequisicao(request, response);
  }

  /**
   * Método para processamento de requisições. Como são suportados os métodos POST e GET, tudo é 
   * direcionado para processamento aqui.
   * 
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException 
   */
  private void processaRequisicao(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String tipoRequisicao = request.getParameter(Requisicao.PARAMETRO_TIPO);

    if (tipoRequisicao == null) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Um tipo de requisição deve ser "
          + "informado como parâmetro \"" + Requisicao.PARAMETRO_TIPO + "\"");
    }
    else if (tipoRequisicao.equals(Requisicao.TIPO_EVENTO)) {
      processaRequisicaoEvento(request, response);
    }
    else {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "O tipo de requisição informado ("
          + tipoRequisicao + ") não é suportado");
    }
  }

  private void processaRequisicaoEvento(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    String eventoRequisitado = request.getParameter(RequisicaoEvento.PARAMETRO_EVENTO);

    if (eventoRequisitado == null) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "O evento deve ser requititado através"
          + " do parâmetro \"" + RequisicaoEvento.PARAMETRO_EVENTO + "\"");
    }
    else {
      if (eventoRequisitado.equals(RequisicaoEvento.EVENTO_TODOS)) {
        writeResponse(response, "todos requisitados");
      }
      else {
        try {
          int idEventoRequisitado = Integer.parseInt(eventoRequisitado);

          writeResponse(response, "requisitado evento de id " + idEventoRequisitado);
        }
        catch (NumberFormatException nfe) {
          response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Evento requisitado inválido. Deve"
              + " ser o ID de um evento ou \"todos\" para obter todos os eventos disponíveis");
        }
      }
    }
  }

  private void writeResponse(HttpServletResponse response, String responseString)
      throws IOException {
    response.setContentType("text/plain");

    PrintWriter printWriter = null;

    try {
      printWriter = response.getWriter();
      printWriter.write(responseString);
    }
    finally {
      if (printWriter != null) {
        printWriter.close();
      }
    }
  }

  /** 
   * Returns a short description of the servlet.
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Servlet de entrada/frontal.";
  }
}
