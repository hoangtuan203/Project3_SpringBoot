/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project3.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Builder
@Entity
@Table(name = "thongtinsd")
public class ThongtinSD implements Serializable {

    @Id
    @Column(name = "MaTT")
    private int maTT;
    @ManyToOne
    @JoinColumn(name = "MaTV")
    private Member thanhVien;
    @ManyToOne
    @JoinColumn(name = "MaTB")
    public Thietbi thietBi;
    @CreationTimestamp
    @Column(name = "TGVao")
    private LocalDateTime tgVao;
    @CreationTimestamp
    @Column(name = "TGMuon")
    private LocalDateTime tgMuon;
    @CreationTimestamp
    @Column(name = "TGTra")
    private LocalDateTime tgTra;
    @CreationTimestamp
    @Column(name = "TGDatCho")
    private LocalDateTime tgDatCho;

    public ThongtinSD() {
    }

    public ThongtinSD(int maTT, Member thanhVien, Thietbi thietBi, LocalDateTime tgVao, LocalDateTime tgMuon,
            LocalDateTime tgTra, LocalDateTime tgDatCho) {
        this.maTT = maTT;
        this.thanhVien = thanhVien;
        this.thietBi = thietBi;
        this.tgVao = tgVao;
        this.tgMuon = tgMuon;
        this.tgTra = tgTra;
        this.tgDatCho = tgDatCho;
    }

    // Getter cho maTT
    public int getMaTT() {
        return maTT;
    }

    // Setter cho maTT
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
    public Thietbi getThietBi() {
        return thietBi;
    }

    // Setter cho thietBi
    public void setThietBi(Thietbi thietBi) {
        this.thietBi = thietBi;
    }

    // Getter cho tgVao
    public LocalDateTime getTgVao() {
        return tgVao;
    }

    // Setter cho tgVao
    public void setTgVao(LocalDateTime tgVao) {
        this.tgVao = tgVao;
    }

    // Getter cho tgMuon
    public LocalDateTime getTgMuon() {
        return tgMuon;
    }

    // Setter cho tgMuon
    public void setTgMuon(LocalDateTime tgMuon) {
        this.tgMuon = tgMuon;
    }

    // Getter cho tgTra
    public LocalDateTime getTgTra() {
        return tgTra;
    }

    // Setter cho tgTra
    public void setTgTra(LocalDateTime tgTra) {
        this.tgTra = tgTra;
    }

    // Getter cho tgDatCho
    public LocalDateTime getTgDatCho() {
        return tgDatCho;
    }

    // Setter cho tgDatCho
    public void setTgDatCho(LocalDateTime tgDatCho) {
        this.tgDatCho = tgDatCho;
    }
}
