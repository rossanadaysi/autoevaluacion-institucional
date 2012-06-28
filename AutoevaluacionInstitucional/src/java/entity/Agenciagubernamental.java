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
@Table(name = "agenciagubernamental")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Agenciagubernamental.findAll", query = "SELECT a FROM Agenciagubernamental a"),
    @NamedQuery(name = "Agenciagubernamental.findById", query = "SELECT a FROM Agenciagubernamental a WHERE a.id = :id"),
    @NamedQuery(name = "Agenciagubernamental.findByDescripcion", query = "SELECT a FROM Agenciagubernamental a WHERE a.descripcion = :descripcion")})
public class Agenciagubernamental implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "agenciagubernamentalId")
    private List<Muestraagencia> muestraagenciaList;
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Persona personaId;
    @JoinColumn(name = "fuente_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Fuente fuenteId;

    public Agenciagubernamental() {
    }

    public Agenciagubernamental(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<Muestraagencia> getMuestraagenciaList() {
        return muestraagenciaList;
    }

    public void setMuestraagenciaList(List<Muestraagencia> muestraagenciaList) {
        this.muestraagenciaList = muestraagenciaList;
    }

    public Persona getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Persona personaId) {
        this.personaId = personaId;
    }

    public Fuente getFuenteId() {
        return fuenteId;
    }

    public void setFuenteId(Fuente fuenteId) {
        this.fuenteId = fuenteId;
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
        if (!(object instanceof Agenciagubernamental)) {
            return false;
        }
        Agenciagubernamental other = (Agenciagubernamental) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Agenciagubernamental[ id=" + id + " ]";
    }
    
}
