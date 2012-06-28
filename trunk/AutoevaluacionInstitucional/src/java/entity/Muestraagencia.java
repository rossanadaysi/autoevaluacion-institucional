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
@Table(name = "muestraagencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Muestraagencia.findAll", query = "SELECT m FROM Muestraagencia m"),
    @NamedQuery(name = "Muestraagencia.findById", query = "SELECT m FROM Muestraagencia m WHERE m.id = :id"),
    @NamedQuery(name = "Muestraagencia.findByConglomerado", query = "SELECT m FROM Muestraagencia m WHERE m.conglomerado = :conglomerado"),
    @NamedQuery(name = "Muestraagencia.findByMetodo", query = "SELECT m FROM Muestraagencia m WHERE m.metodo = :metodo")})
public class Muestraagencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "conglomerado")
    private String conglomerado;
    @Column(name = "metodo")
    private String metodo;
    @JoinColumn(name = "muestra_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Muestra muestraId;
    @JoinColumn(name = "agenciagubernamental_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Agenciagubernamental agenciagubernamentalId;

    public Muestraagencia() {
    }

    public Muestraagencia(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConglomerado() {
        return conglomerado;
    }

    public void setConglomerado(String conglomerado) {
        this.conglomerado = conglomerado;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public Muestra getMuestraId() {
        return muestraId;
    }

    public void setMuestraId(Muestra muestraId) {
        this.muestraId = muestraId;
    }

    public Agenciagubernamental getAgenciagubernamentalId() {
        return agenciagubernamentalId;
    }

    public void setAgenciagubernamentalId(Agenciagubernamental agenciagubernamentalId) {
        this.agenciagubernamentalId = agenciagubernamentalId;
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
        if (!(object instanceof Muestraagencia)) {
            return false;
        }
        Muestraagencia other = (Muestraagencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Muestraagencia[ id=" + id + " ]";
    }
    
}
