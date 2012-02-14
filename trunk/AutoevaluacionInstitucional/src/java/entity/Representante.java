/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author vanesa
 */
@Entity
@Table(name = "representante")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Representante.findAll", query = "SELECT r FROM Representante r"),
    @NamedQuery(name = "Representante.findById", query = "SELECT r FROM Representante r WHERE r.id = :id"),
    @NamedQuery(name = "Representante.findByRol", query = "SELECT r FROM Representante r WHERE r.rol = :rol")})
public class Representante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "rol")
    private String rol;
    @JoinColumn(name = "programa_id", referencedColumnName = "id")
    @ManyToOne
    private Programa programaId;
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Persona personaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "representanteId")
    private List<Representantehasprivilegio> representantehasprivilegioList;

    public Representante() {
    }

    public Representante(Integer id) {
        this.id = id;
    }

    public Representante(Integer id, String rol) {
        this.id = id;
        this.rol = rol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Programa getProgramaId() {
        return programaId;
    }

    public void setProgramaId(Programa programaId) {
        this.programaId = programaId;
    }

    public Persona getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Persona personaId) {
        this.personaId = personaId;
    }

    @XmlTransient
    public List<Representantehasprivilegio> getRepresentantehasprivilegioList() {
        return representantehasprivilegioList;
    }

    public void setRepresentantehasprivilegioList(List<Representantehasprivilegio> representantehasprivilegioList) {
        this.representantehasprivilegioList = representantehasprivilegioList;
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
        if (!(object instanceof Representante)) {
            return false;
        }
        Representante other = (Representante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Representante[ id=" + id + " ]";
    }
    
}
