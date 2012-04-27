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
 * @author Ususario
 */
@Entity
@Table(name = "ponderacioncaracteristica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ponderacioncaracteristica.findAll", query = "SELECT p FROM Ponderacioncaracteristica p"),
    @NamedQuery(name = "Ponderacioncaracteristica.findById", query = "SELECT p FROM Ponderacioncaracteristica p WHERE p.id = :id"),
    @NamedQuery(name = "Ponderacioncaracteristica.findByNivelimportancia", query = "SELECT p FROM Ponderacioncaracteristica p WHERE p.nivelimportancia = :nivelimportancia"),
    @NamedQuery(name = "Ponderacioncaracteristica.findByPonderacion", query = "SELECT p FROM Ponderacioncaracteristica p WHERE p.ponderacion = :ponderacion"),
    @NamedQuery(name = "Ponderacioncaracteristica.findByJustificacion", query = "SELECT p FROM Ponderacioncaracteristica p WHERE p.justificacion = :justificacion")})
public class Ponderacioncaracteristica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nivelimportancia")
    private int nivelimportancia;
    @Basic(optional = false)
    @Column(name = "ponderacion")
    private double ponderacion;
    @Basic(optional = false)
    @Column(name = "justificacion")
    private String justificacion;
    @JoinColumn(name = "caracteristica_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Caracteristica caracteristicaId;
    @JoinColumn(name = "proceso_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Proceso procesoId;

    public Ponderacioncaracteristica() {
    }

    public Ponderacioncaracteristica(Integer id) {
        this.id = id;
    }

    public Ponderacioncaracteristica(Integer id, int nivelimportancia, double ponderacion, String justificacion) {
        this.id = id;
        this.nivelimportancia = nivelimportancia;
        this.ponderacion = ponderacion;
        this.justificacion = justificacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNivelimportancia() {
        return nivelimportancia;
    }

    public void setNivelimportancia(int nivelimportancia) {
        this.nivelimportancia = nivelimportancia;
    }

    public double getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(double ponderacion) {
        this.ponderacion = ponderacion;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public Caracteristica getCaracteristicaId() {
        return caracteristicaId;
    }

    public void setCaracteristicaId(Caracteristica caracteristicaId) {
        this.caracteristicaId = caracteristicaId;
    }

    public Proceso getProcesoId() {
        return procesoId;
    }

    public void setProcesoId(Proceso procesoId) {
        this.procesoId = procesoId;
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
        if (!(object instanceof Ponderacioncaracteristica)) {
            return false;
        }
        Ponderacioncaracteristica other = (Ponderacioncaracteristica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Ponderacioncaracteristica[ id=" + id + " ]";
    }
    
}
