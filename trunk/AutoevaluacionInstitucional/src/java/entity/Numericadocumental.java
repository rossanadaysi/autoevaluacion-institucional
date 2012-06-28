/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oscar
 */
@Entity
@Table(name = "numericadocumental")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Numericadocumental.findAll", query = "SELECT n FROM Numericadocumental n"),
    @NamedQuery(name = "Numericadocumental.findById", query = "SELECT n FROM Numericadocumental n WHERE n.id = :id"),
    @NamedQuery(name = "Numericadocumental.findByDocumento", query = "SELECT n FROM Numericadocumental n WHERE n.documento = :documento"),
    @NamedQuery(name = "Numericadocumental.findByResponsable", query = "SELECT n FROM Numericadocumental n WHERE n.responsable = :responsable"),
    @NamedQuery(name = "Numericadocumental.findByMedio", query = "SELECT n FROM Numericadocumental n WHERE n.medio = :medio"),
    @NamedQuery(name = "Numericadocumental.findByLugar", query = "SELECT n FROM Numericadocumental n WHERE n.lugar = :lugar"),
    @NamedQuery(name = "Numericadocumental.findByEvaluacion", query = "SELECT n FROM Numericadocumental n WHERE n.evaluacion = :evaluacion"),
    @NamedQuery(name = "Numericadocumental.findByAccion", query = "SELECT n FROM Numericadocumental n WHERE n.accion = :accion")})
public class Numericadocumental implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "documento")
    private String documento;
    @Basic(optional = false)
    @Column(name = "responsable")
    private String responsable;
    @Basic(optional = false)
    @Column(name = "medio")
    private String medio;
    @Basic(optional = false)
    @Column(name = "lugar")
    private String lugar;
    @Basic(optional = false)
    @Column(name = "evaluacion")
    private int evaluacion;
    @Basic(optional = false)
    @Column(name = "accion")
    private String accion;
    @JoinColumn(name = "proceso_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Proceso procesoId;
    @JoinColumn(name = "indicador_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Indicador indicadorId;

    public Numericadocumental() {
    }

    public Numericadocumental(Integer id) {
        this.id = id;
    }

    public Numericadocumental(Integer id, String documento, String responsable, String medio, String lugar, int evaluacion, String accion) {
        this.id = id;
        this.documento = documento;
        this.responsable = responsable;
        this.medio = medio;
        this.lugar = lugar;
        this.evaluacion = evaluacion;
        this.accion = accion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getMedio() {
        return medio;
    }

    public void setMedio(String medio) {
        this.medio = medio;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public int getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(int evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Proceso getProcesoId() {
        return procesoId;
    }

    public void setProcesoId(Proceso procesoId) {
        this.procesoId = procesoId;
    }

    public Indicador getIndicadorId() {
        return indicadorId;
    }

    public void setIndicadorId(Indicador indicadorId) {
        this.indicadorId = indicadorId;
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
        if (!(object instanceof Numericadocumental)) {
            return false;
        }
        Numericadocumental other = (Numericadocumental) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Numericadocumental[ id=" + id + " ]";
    }
    
}
