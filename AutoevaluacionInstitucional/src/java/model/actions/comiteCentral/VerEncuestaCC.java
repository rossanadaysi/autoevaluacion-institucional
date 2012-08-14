/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model.actions.comiteCentral;

import entity.Encuesta;
import entity.controller.EncuestaJpaController;
import entity.controller.FactorJpaController;
import entity.controller.exceptions.IllegalOrphanException;
import entity.controller.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Action;


public class VerEncuestaCC implements Action{
    
    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        EncuestaJpaController conEncuesta = new EncuestaJpaController();
        int idEncuesta = Integer.parseInt(id);
        Encuesta e = conEncuesta.findEncuesta(idEncuesta);
        session.setAttribute("encuesta", e);
        String url = "/WEB-INF/vista/comiteCentral/encuesta/vistaPrevia.jsp";
        return url;
    }

}
