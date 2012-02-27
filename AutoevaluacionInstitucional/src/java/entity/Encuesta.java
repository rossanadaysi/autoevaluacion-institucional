/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vanesa
 */
@Entity
@Table(name = "encuesta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encuesta.findAll", query = "SELECT e FROM Encuesta e"),
    @NamedQuery(name = "Encuesta.findById", query = "SELECT e FROM Encuesta e WHERE e.id = :id"),
    @NamedQuery(name = "Encuesta.findByNombre", query = "SELECT e FROM Encuesta e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Encuesta.findByDescripcion", query = "SELECT e FROM Encuesta e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Encuesta.findByInstrucciones", query = "SELECT e FROM Encuesta e WHERE e.instrucciones = :instrucciones"),
    @NamedQuery(name = "Encuesta.findByMensaje", query = "SELECT e FROM Encuesta e WHERE e.mensaje = :mensaje"),
    @NamedQuery(name = "Encuesta.findByFecha", query = "SELECT e FROM Encuesta e WHERE e.fecha = :fecha")})
public class Encuesta implements Serializable {
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "encuestaId")
    private List<Asignacionencuesta> asignacionencuestaList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "instrucciones")
    private String instrucciones;
    @Column(name = "mensaje")
    private String mensaje;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "encuestaId")
    private List<Encuestahaspregunta> encuestahaspreguntaList;

    public Encuesta() {
    }

    public Encuesta(Integer id) {
        this.id = id;
    }

    public Encuesta(Integer id, String nombre, Date fecha) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @XmlTransient
    public List<Encuestahaspregunta> getEncuestahaspreguntaList() {
        return encuestahaspreguntaList;
    }

    public void setEncuestahaspreguntaList(List<Encuestahaspregunta> encuestahaspreguntaList) {
        this.encuestahaspreguntaList = encuestahaspreguntaList;
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
        if (!(object instanceof Encuesta)) {
            return false;
        }
        Encuesta other = (Encuesta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Encuesta[ id=" + id + " ]";
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @XmlTransient
    public List<Asignacionencuesta> getAsignacionencuestaList() {
        return asignacionencuestaList;
    }

    public void setAsignacionencuestaList(List<Asignacionencuesta> asignacionencuestaList) {
        this.asignacionencuestaList = asignacionencuestaList;
    }
    
}