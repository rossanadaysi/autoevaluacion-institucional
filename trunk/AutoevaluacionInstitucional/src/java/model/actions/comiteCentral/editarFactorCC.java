/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.comiteCentral;

import entity.Factor;
import entity.controller.CaracteristicaJpaController;
import entity.controller.FactorJpaController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import model.Action;

/**
 *
 * @author Ususario
 */
public class editarFactorCC implements Action{
    @Override
    public String procesar(HttpServletRequest request) throws IOException, ServletException {
        
        FactorJpaController conFactor = new FactorJpaController(); 
        CaracteristicaJpaController conCaract = new CaracteristicaJpaController(); 
        HttpSession session = request.getSession();
        String idfactor = request.getParameter("idF");
        
        
        Factor f =  conFactor.findFactor(Integer.parseInt(idfactor));
        session.setAttribute("factor",f);
        session.setAttribute("listcaracteristicas", conCaract.findCaracteristicaEntities());
        
        
        String url = "/WEB-INF/vista/comiteCentral/factor/editar.jsp";
        return url;
    }
    
}
