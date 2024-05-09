package com.example.project3.models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;
@Entity
@Table(name = "thongtinsd")
public class ThongTinSD {
    @Id
    @Column(name="MaTT")
    private int maTT;
    @Column(name="MaTV")
    private int maTV;
    @Column(name="MaTB")
    private int maTB;
    @Column(name = "TGVao")
    private Timestamp tgVao;
    @Column(name = "TGMuon")
    private Timestamp tgMuon;
    @Column(name = "TGTra")
    private Timestamp tgTra;
    @Column(name = "TGDatCho")
    private Timestamp tgDatCho;


    public ThongTinSD(){

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

    public Date getTgDatCho() {
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
}