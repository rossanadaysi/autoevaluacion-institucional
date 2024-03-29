/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findById", query = "SELECT p FROM Persona p WHERE p.id = :id"),
    @NamedQuery(name = "Persona.findByNombre", query = "SELECT p FROM Persona p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Persona.findByApellido", query = "SELECT p FROM Persona p WHERE p.apellido = :apellido"),
    @NamedQuery(name = "Persona.findByPassword", query = "SELECT p FROM Persona p WHERE p.password = :password"),
    @NamedQuery(name = "Persona.findByMail", query = "SELECT p FROM Persona p WHERE p.mail = :mail")})
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "mail")
    private String mail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaId")
    private List<Administrativo> administrativoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaId")
    private List<Estudiante> estudianteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaId")
    private List<Directorprograma> directorprogramaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaId")
    private List<Encabezado> encabezadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaId")
    private List<Egresado> egresadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaId")
    private List<Agenciagubernamental> agenciagubernamentalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaId")
    private List<Representante> representanteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaId")
    private List<Empleador> empleadorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaId")
    private List<Muestracriterio> muestracriterioList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaId")
    private List<Docente> docenteList;

    public Persona() {
    }

    public Persona(String id) {
        this.id = id;
    }

    public Persona(String id, String nombre, String apellido, String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @XmlTransient
    public List<Administrativo> getAdministrativoList() {
        return administrativoList;
    }

    public void setAdministrativoList(List<Administrativo> administrativoList) {
        this.administrativoList = administrativoList;
    }

    @XmlTransient
    public List<Estudiante> getEstudianteList() {
        return estudianteList;
    }

    public void setEstudianteList(List<Estudiante> estudianteList) {
        this.estudianteList = estudianteList;
    }

    @XmlTransient
    public List<Directorprograma> getDirectorprogramaList() {
        return directorprogramaList;
    }

    public void setDirectorprogramaList(List<Directorprograma> directorprogramaList) {
        this.directorprogramaList = directorprogramaList;
    }

    @XmlTransient
    public List<Encabezado> getEncabezadoList() {
        return encabezadoList;
    }

    public void setEncabezadoList(List<Encabezado> encabezadoList) {
        this.encabezadoList = encabezadoList;
    }

    @XmlTransient
    public List<Egresado> getEgresadoList() {
        return egresadoList;
    }

    public void setEgresadoList(List<Egresado> egresadoList) {
        this.egresadoList = egresadoList;
    }

    @XmlTransient
    public List<Agenciagubernamental> getAgenciagubernamentalList() {
        return agenciagubernamentalList;
    }

    public void setAgenciagubernamentalList(List<Agenciagubernamental> agenciagubernamentalList) {
        this.agenciagubernamentalList = agenciagubernamentalList;
    }

    @XmlTransient
    public List<Representante> getRepresentanteList() {
        return representanteList;
    }

    public void setRepresentanteList(List<Representante> representanteList) {
        this.representanteList = representanteList;
    }

    @XmlTransient
    public List<Empleador> getEmpleadorList() {
        return empleadorList;
    }

    public void setEmpleadorList(List<Empleador> empleadorList) {
        this.empleadorList = empleadorList;
    }

    @XmlTransient
    public List<Muestracriterio> getMuestracriterioList() {
        return muestracriterioList;
    }

    public void setMuestracriterioList(List<Muestracriterio> muestracriterioList) {
        this.muestracriterioList = muestracriterioList;
    }

    @XmlTransient
    public List<Docente> getDocenteList() {
        return docenteList;
    }

    public void setDocenteList(List<Docente> docenteList) {
        this.docenteList = docenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Persona[ id=" + id + " ]";
    }
    
}
