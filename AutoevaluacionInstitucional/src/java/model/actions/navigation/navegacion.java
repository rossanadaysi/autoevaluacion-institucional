/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.actions.navigation;

import entity.Proceso;
import entity.controller.PrivilegioJpaController;
import entity.controller.ProcesoJpaController;
import entity.controller.ProgramaJpaController;
import entity.controller.RepresentanteJpaController;
import entity.controller.exceptions.NonexistentEntityException;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Action;

/**
 *
 * @author Arturo González
 */
public class navegacion implements Action {

    public String procesar(HttpServletRequest request)
            throws IOException, ServletException {

        HttpSession session = request.getSession();
        ProgramaJpaController conPrograma = new ProgramaJpaController();
        PrivilegioJpaController conPivilegio = new PrivilegioJpaController();
        RepresentanteJpaController conRepresentante = new RepresentanteJpaController();

        String path = request.getParameter("action");

        if (path.equals("CrearProcesoAI")) {
            path = "autoevaluacionInstitucional/proceso/crear";
        } else if (path.equals("detalleProcesoAI")) {
            path = "autoevaluacionInstitucional/proceso/detalle";
        } else if (path.equals("indexCC")) {
            path = "comiteCentral/index";
        } else if (path.equals("listarRepresentante")) {
            path = "comiteCentral/representante/listar";
            session.setAttribute("listrepresentantes", conRepresentante.findRepresentanteEntities());
        } else if (path.equals("crearRepresentante")) {
            session.setAttribute("programas", conPrograma.findProgramaEntities());
            session.setAttribute("privilegios", conPivilegio.findPrivilegioEntities());
            path = "comiteCentral/representante/crear";
        } else if (path.equals("indexAI")) {
            path = "autoevaluacionInstitucional/index2";
        } else if (path.equals("CerrarProcesoAI")) {
            Proceso p = (Proceso) session.getAttribute("proceso");
            ProcesoJpaController pc = new ProcesoJpaController();
            Date d = new Date();
            String date = String.valueOf(d);
            p.setFechacierre(date);
            try {
                pc.edit(p);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(navegacion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(navegacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("aux_index2", 0);
            path = "autoevaluacionInstitucional/index2";
        } else if (path.equals("IniciarProcesoAI")) {
            Proceso p = (Proceso) session.getAttribute("proceso");
            ProcesoJpaController pc = new ProcesoJpaController();
            Date d = new Date();
            String date = String.valueOf(d);
            p.setFechainicio(date);
            try {
                pc.edit(p);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(navegacion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(navegacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("aux2_index2", 0);
            path = "autoevaluacionInstitucional/index2";
        }
        String url;
        if (path.equals("IniciarProcesoAI") || (path.equals("CerrarProcesoAI"))) {
            url = "/";
        } else {
            url = "/WEB-INF/vista/" + path + ".jsp";
        }


        return url;
    }
}
