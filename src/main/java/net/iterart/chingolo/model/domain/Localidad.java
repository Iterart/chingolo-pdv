package net.iterart.chingolo.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import net.iterart.chingolo.util.TextoUtil;

@Entity
@Table(name = "localidades")
public class Localidad implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_loc")
    private int numero;

    @Column(name = "cpa", nullable = false, length = 5)
    private String cpa;

    @Column(name = "nombre", nullable = false, unique = false, length = 150)
    private String nombre;

    @NotNull(message = "La Provincia es requerida")
    @ManyToOne
    @JoinColumn(name = "fk_id_prv")
    private Provincia provincia;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCpa() {
        return cpa;
    }

    public void setCpa(String cpa) {
        this.cpa = cpa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return cpa + "-" + TextoUtil.primerLetraMayuscula(nombre) + " / " + TextoUtil.primerLetraMayuscula(provincia.getNombre());
    }

}
