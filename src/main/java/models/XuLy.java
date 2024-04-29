/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "xuly")
public class XuLy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maXL;

    @ManyToOne
    @JoinColumn(name = "maTV")
    private int maTV;

    private String hinhThucXL;

    private Integer soTien;

    private Date ngayXL;
//    @Column(name = "TrangThaiXL")
    private int trangThaiXL;

    public XuLy() {
    }

    public XuLy(int maXL, int maTV, String hinhThucXL, Integer soTien, Date ngayXL, int trangThaiXL) {
        this.maXL =  maXL;
        this.maTV = maTV;
        this.hinhThucXL = hinhThucXL;
        this.soTien = soTien;
        this.ngayXL = ngayXL;
        this.trangThaiXL = trangThaiXL;
    }

    public int getMaXL() {
        return maXL;
    }

    public void setMaXL(int maXL) {
        this.maXL = maXL;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getHinhThucXL() {
        return hinhThucXL;
    }

    public void setHinhThucXL(String hinhThucXL) {
        this.hinhThucXL = hinhThucXL;
    }

    public Integer getSoTien() {
        return soTien;
    }

    public void setSoTien(Integer soTien) {
        this.soTien = soTien;
    }

    public Date getNgayXL() {
        return ngayXL;
    }

    public void setNgayXL(Date ngayXL) {
        this.ngayXL = ngayXL;
    }

    public int getTrangThaiXL() {
        return trangThaiXL;
    }

    public void setTrangThaiXL(int trangThaiXL) {
        this.trangThaiXL = trangThaiXL;
    }

}
