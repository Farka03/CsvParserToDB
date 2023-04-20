package com.csv.parser.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "posting")
public class Posting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "mat_doc")
    private Long matDoc;
    @Column(name = "item")
    private String item;
    @Column(name = "doc_date")
    private LocalDate docDate;
    @Column(name = "pstng_date")
    private LocalDate pstngDate;
    @Column(name = "material_description")
    private String materialDescription;
    @Column(name = "quantity")
    private String quantity;
    @Column(name = "bUn")
    private String bUn;
    @Column(name = "amount_lc")
    private String amountLC;
    @Column(name = "crcy")
    private String crcy;
    @Column(name = "username")
    private String userName;
    @Column(name = "is_authorized")
    private boolean isAuthorized;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getMatDoc() {
        return matDoc;
    }

    public void setMatDoc(Long matDoc) {
        this.matDoc = matDoc;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public LocalDate getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.docDate = LocalDate.parse(docDate, formatter);
    }

    public LocalDate getPstngDate() {
        return pstngDate;
    }

    public void setPstngDate(String pstngDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.pstngDate = LocalDate.parse(pstngDate, formatter);
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getbUn() {
        return bUn;
    }

    public void setbUn(String bUn) {
        this.bUn = bUn;
    }

    public String getAmountLC() {
        return amountLC;
    }

    public void setAmountLC(String amountLC) {
        this.amountLC = amountLC;
    }

    public String getCrcy() {
        return crcy;
    }

    public void setCrcy(String crcy) {
        this.crcy = crcy;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }

    @Override
    public String toString() {
        return "Posting{" +
                "id=" + id +
                ", matDoc='" + matDoc + '\'' +
                ", item='" + item + '\'' +
                ", docDate='" + docDate + '\'' +
                ", pstngDate='" + pstngDate + '\'' +
                ", materialDescription='" + materialDescription + '\'' +
                ", quantity='" + quantity + '\'' +
                ", bUn='" + bUn + '\'' +
                ", amountLC='" + amountLC + '\'' +
                ", crcy='" + crcy + '\'' +
                ", userName='" + userName + '\'' +
                ", isAuthorized='" + isAuthorized + '\'' +
                '}';
    }
}
