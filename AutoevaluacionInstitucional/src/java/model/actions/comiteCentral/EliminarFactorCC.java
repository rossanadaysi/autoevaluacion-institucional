/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.comiteCentral;

import entity.Factor;
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

/**
 *
 * @author Ususario
 */
public class EliminarFactorCC implements Action {

    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String idF = request.getParameter("idF");
        System.out.println("+++++++++++++++++++++++++++++++++"+idF+"++++++++++++++++++++++++++++++++++++");
        FactorJpaController conFactor = new FactorJpaController();
        int idFactor = Integer.parseInt(idF);
        try {
            conFactor.destroy(idFactor);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(EliminarFactorCC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(EliminarFactorCC.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute("listfactores", conFactor.findFactorEntities());
        String url = "/WEB-INF/vista/comiteCentral/factor/listar.jsp";
        return url;
    }
}
