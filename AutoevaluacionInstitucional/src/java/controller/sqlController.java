/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connection.sqlConnection;
import entity.Proceso;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

/**
 *
 * @author Arturo González
 */
public class sqlController {

    private sqlConnection linkDB = null;
    private Connection con = null;

    //////////////////////////////////////////////////////////////////////
    public void setConexion(sqlConnection conex) {
        linkDB = conex;
        con = linkDB.getConnection();
        System.out.println("[CONEXION RECIBIDA]");
    }

    ///////////////////////////////////////////////////////////////////////
    public boolean isConected() {
        boolean result = false;
        if ((linkDB != null) && (linkDB.isConnect())) {
            result = true;
        }
        return result;
    }

    /*
     * public ResultSet CargarSql(String sql) {
     *
     * ResultSet rs = null; Connection con = null; sqlConnection sqlCon = new
     * sqlConnection();
     *
     * try {
     *
     * con = (Connection) sqlCon.getConecction();
     *
     * Statement st = (Statement) con.createStatement(); System.out.println("Se
     * ha realizado con exito la conexiÃƒÂ³n a MySQL"); //el resultSet es el
     * encargado de traer los datos de la consulta rs = st.executeQuery(sql);
     *
     *
     * } catch (SQLException ex) { System.out.println(ex); } finally { try {
     * con.close(); System.out.println("sqlConnection Cerrada con Exito..."); }
     * catch (SQLException ex) { System.out.println(ex); } } return rs;
    }
     */
    public void newDb(Proceso proceso, String str, String str2) throws IOException, SQLException, FileNotFoundException {

        String nombre = proceso.getProgramaId().getNombre();
        int id = proceso.getId();
        System.out.println("el id : " + id);

        sqlConnection conSql = new sqlConnection();
        conSql.conectarMySQL("autoevaluacion");

        this.setConexion(conSql);

        String name1 = "`" + nombre + id + "`;";
        Statement stmt = con.createStatement();
        stmt.executeUpdate("create database " + name1);

        String script = str;
        String nameBd = nombre + id;

        this.cargarScript(nameBd, script);


        script = str2;
        this.cargarScript(nameBd, script);
        //  this.resetDB_gtbio(stmt);
        //stmt.close();
    }

    public void cargarScript(String nameBd, String url) throws FileNotFoundException, IOException, SQLException {
        try {

            String s = new String();

            Statement stmt = con.createStatement();

            StringBuffer sb = new StringBuffer();
            FileReader fr = new FileReader(new File(url));
            // be sure to not have line starting with "--" or "/*" or any 
            // other non aplhabetical character
            BufferedReader br = new BufferedReader(fr);
            String name = "USE `" + nameBd + "`;";
            sb.append(name);
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            br.close();
            // here is our splitter ! We use ";" as a delimiter for each request
            // then we are sure to have well formed statements
            String[] inst = sb.toString().split(";");
            for (int i = 0; i < inst.length; i++) {
                // we ensure that there is no spaces before or after the request 
                // string in order to not execute empty statements
                if (!inst[i].trim().equals("")) {
                    stmt.executeUpdate(inst[i] + ";");
                }
            }

            stmt.close();
        } catch (java.sql.SQLException er) {
            System.out.println("No se pudo realizar la operaciÃ³n");
            System.out.println("SQLException: " + er.getMessage());
            System.out.println("SQLState: " + er.getSQLState());
            System.out.println("VendorError: " + er.getErrorCode());
        }

    }

    public void cerrarConexion() {

        if (this.isConected()) {
            linkDB.cerrarConexion();
        }
    }

    public ResultSet CargarSql(String sql, String bd) {


        ResultSet rs = null;
        Connection con = null;
        sqlConnection sqlCon = new sqlConnection();

        try {

            sqlCon.conectarMySQL(bd);
            con = (Connection) sqlCon.getConnection();

            Statement st = (Statement) con.createStatement();
            // System.out.println("Se ha realizado con exito la conexion a MySQL");
            //el resultSet es el encargado de traer los datos de la consulta
            rs = st.executeQuery(sql);


        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (Error ex) {
            System.out.println(ex);
        }
        return rs;
    }

    public Result CargarSql2(String sql, String bd) {


        ResultSet rs = null;
        Result result = null;
        Connection con = null;
        sqlConnection sqlCon = new sqlConnection();

        try {

            sqlCon.conectarMySQL(bd);
            //   System.out.println("**" + bd);

            con = (Connection) sqlCon.getConnection();

            Statement st = (Statement) con.createStatement();
            //  System.out.println("Se ha realizado con exito la conexion a MySQL");
            //el resultSet es el encargado de traer los datos de la consulta
            //System.out.println(sql);
            rs = st.executeQuery(sql);
            result = ResultSupport.toResult(rs);


        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (Error ex) {
            System.out.println(ex);
        }
        return result;
    }

    public void UpdateSql(String sql, String bd) {



        Connection con = null;
        sqlConnection sqlCon = new sqlConnection();

        try {


            sqlCon.conectarMySQL(bd);
            con = (Connection) sqlCon.getConnection();

            Statement st = (Statement) con.createStatement();
            // System.out.println("Se ha realizado con exito la conexion a MySQL");

            st.executeUpdate(sql);


        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (Error ex) {
            System.out.println(ex);
        } finally {
            try {
                con.close();
                //System.out.println("sqlConnection Cerrada con Exito...");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

    }
}
