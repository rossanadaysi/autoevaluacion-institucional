/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "encabezado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Encabezado.findAll", query = "SELECT e FROM Encabezado e"),
    @NamedQuery(name = "Encabezado.findById", query = "SELECT e FROM Encabezado e WHERE e.id = :id"),
    @NamedQuery(name = "Encabezado.findByFecha", query = "SELECT e FROM Encabezado e WHERE e.fecha = :fecha"),
    @NamedQuery(name = "Encabezado.findByEstado", query = "SELECT e FROM Encabezado e WHERE e.estado = :estado")})
public class Encabezado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "fuente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Fuente fuenteId;
    @JoinColumn(name = "encuesta_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Encuesta encuestaId;
    @JoinColumn(name = "proceso_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Proceso procesoId;
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Persona personaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "encabezadoId")
    private List<Resultadoevaluacion> resultadoevaluacionList;

    public Encabezado() {
    }

    public Encabezado(Integer id) {
        this.id = id;
    }

    public Encabezado(Integer id, Date fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Fuente getFuenteId() {
        return fuenteId;
    }

    public void setFuenteId(Fuente fuenteId) {
        this.fuenteId = fuenteId;
    }

    public Encuesta getEncuestaId() {
        return encuestaId;
    }

    public void setEncuestaId(Encuesta encuestaId) {
        this.encuestaId = encuestaId;
    }

    public Proceso getProcesoId() {
        return procesoId;
    }

    public void setProcesoId(Proceso procesoId) {
        this.procesoId = procesoId;
    }

    public Persona getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Persona personaId) {
        this.personaId = personaId;
    }

    @XmlTransient
    public List<Resultadoevaluacion> getResultadoevaluacionList() {
        return resultadoevaluacionList;
    }

    public void setResultadoevaluacionList(List<Resultadoevaluacion> resultadoevaluacionList) {
        this.resultadoevaluacionList = resultadoevaluacionList;
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
        if (!(object instanceof Encabezado)) {
            return false;
        }
        Encabezado other = (Encabezado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Encabezado[ id=" + id + " ]";
    }
    
}
