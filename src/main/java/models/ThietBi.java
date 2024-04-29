/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.io.Serializable;
import jakarta.persistence.*;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "thietbi")
public class ThietBi implements Serializable {

    @Id
    @Column(name = "MaTB")
    private int maTB;
    @Column(name = "TenTB")
    private String tenTB;
    @Column(name = "MoTaTB")
    private String moTaTB;

    public ThietBi() {
    }

    public ThietBi(int maTB, String tenTB, String moTaTB) {
        this.maTB = maTB;
        this.tenTB = tenTB;
        this.moTaTB = moTaTB;
    }

    public int getMaTB() {
        return maTB;
    }

    public void setMaTB(int maTB) {
        this.maTB = maTB;
    }

    public String getTenTB() {
        return tenTB;
    }

    public void setTenTB(String tenTB) {
        this.tenTB = tenTB;
    }

    public String getMoTaTB() {
        return moTaTB;
    }

    public void setMoTaTB(String moTaTB) {
        this.moTaTB = moTaTB;
    }
}