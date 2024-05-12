package com.example.project3.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
@Table(name = "thongtinsd")
public class ThongtinSD implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTT")
    private int maTT;

    @ManyToOne
    @JoinColumn(name = "MaTV")
    private Member thanhVien;

    @ManyToOne
    @JoinColumn(name = "MaTB")
    private ThietBi thietBi;

    @Column(name = "TGVao")
    private Timestamp tgVao;

    @Column(name = "TGMuon")
    private Timestamp tgMuon;

    @Column(name = "TGTra")
    private Timestamp tgTra;

    @Column(name = "TGDatCho")
    private Timestamp tgDatCho;

    public ThongtinSD() {

    }

    public ThongtinSD(int maTT, Member thanhVien, ThietBi thietBi, Timestamp tgVao, Timestamp tgMuon, Timestamp tgTra,
                      Timestamp tgDatCho) {
        this.maTT = maTT;
        this.thanhVien = thanhVien;
        this.thietBi = thietBi;
        this.tgVao = tgVao;
        this.tgMuon = tgMuon;
        this.tgTra = tgTra;
        this.tgDatCho = tgDatCho;
    }

    public ThongtinSD(int maTT, Member thanhVien, Timestamp tgVao, Timestamp tgMuon, Timestamp tgTra,
                      Timestamp tgDatCho) {
        this.maTT = maTT;
        this.thanhVien = thanhVien;
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

    // Getter cho thanhVien
    public Member getThanhVien() {
        return thanhVien;
    }

    // Setter cho thanhVien
    public void setThanhVien(Member thanhVien) {
        this.thanhVien = thanhVien;
    }

    // Getter cho thietBi
    public ThietBi getThietBi() {
        return thietBi;
    }

    // Setter cho thietBi
    public void setThietBi(ThietBi thietBi) {
        this.thietBi = thietBi;
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