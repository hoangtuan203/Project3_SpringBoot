/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project3.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@Builder
@Entity
@Table(name = "thietbi")
public class Thietbi implements Serializable {

    @Id
    @Column(name = "MaTB")
    private int maTB;
    @Column(name = "TenTB")
    private String tenTB;
    @Column(name = "MoTaTB")
    private String moTaTB;

    public Thietbi() {
    }

    public Thietbi(int maTB, String tenTB, String moTaTB) {
        this.maTB = maTB;
        this.tenTB = tenTB;
        this.moTaTB = moTaTB;
    }

    
 // Getter cho maTB
    public int getMaTB() {
        return maTB;
    }

    // Setter cho maTB
    public void setMaTB(int maTB) {
        this.maTB = maTB;
    }

    // Getter cho tenTB
    public String getTenTB() {
        return tenTB;
    }

    // Setter cho tenTB
    public void setTenTB(String tenTB) {
        this.tenTB = tenTB;
    }

    // Getter cho moTaTB
    public String getMoTaTB() {
        return moTaTB;
    }

    // Setter cho moTaTB
    public void setMoTaTB(String moTaTB) {
        this.moTaTB = moTaTB;
    }
}
