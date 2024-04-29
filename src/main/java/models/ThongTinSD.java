/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import jakarta.persistence.*;
/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "thongtinsd")
public class ThongTinSD implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTT")
    private int maTT;
    @ManyToOne
    @JoinColumn(name = "maTV")
    private int maTV;
    @ManyToOne
    @JoinColumn(name = "maTB")
    private int maTB;
    @Column(name = "TGVao")
    private Timestamp tgVao;
    @Column(name = "TGMuon")
    private Timestamp tgMuon;
    @Column(name = "TGTra")
    private Timestamp tgTra;
    @Column(name = "TGDatCho")
    private Timestamp tgDatCho;
    public ThongTinSD() {
    }

    
    public ThongTinSD(int maTT, int maTV, int maTB, Timestamp tgVao, Timestamp tgMuon, Timestamp tgTra, Timestamp tgDatCho) {
        this.maTT = maTT;
        this.maTV = maTV;
        this.maTB = maTB;
        this.tgVao = tgVao;
        this.tgMuon = tgMuon;
        this.tgTra = tgTra;
        this.tgDatCho = tgDatCho;
    }
    public ThongTinSD(int maTT, int maTV, Timestamp tgVao, Timestamp tgMuon, Timestamp tgTra, Timestamp tgDatCho) {
        this.maTT = maTT;
        this.maTV = maTV;
        this.tgVao = tgVao;
        this.tgMuon = tgMuon;
        this.tgTra = tgTra;
        this.tgDatCho = tgDatCho;
    }

    public Timestamp getTgDatCho() {
        return tgDatCho;
    }

    public void setTgDatCho(Timestamp tgDatCho) {
        this.tgDatCho = tgDatCho;
    }

    public int getMaTT() {
        return maTT;
    }

    public void setMaTT(int maTT) {
        this.maTT = maTT;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaTB() {
        return maTB;
    }

    public void setMaTB(int maTB) {
        this.maTB = maTB;
    }

    public Date getTgVao() {
        return tgVao;
    }

    public void setTgVao(Timestamp tgVao) {
        this.tgVao = tgVao;
    }

    public Date getTgMuon() {
        return tgMuon;
    }

    public void setTgMuon(Timestamp tgMuon) {
        this.tgMuon = tgMuon;
    }

    public Date getTgTra() {
        return tgTra;
    }

    public void setTgTra(Timestamp tgTra) {
        this.tgTra = tgTra;
    }

    @Override
    public String toString() {
        return "thongtinsd{" + "maTT=" + maTT + ", maTV=" + maTV + ", maTB=" + maTB + ", tgVao=" + tgVao + ", tgMuon=" + tgMuon + ", tgTra=" + tgTra + '}';
    }

}
