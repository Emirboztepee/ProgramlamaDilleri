package com.hr190012.emirdursunboztepefinal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


//jsondaki verilerin başlıkları burada tutuluyor ve json dosyası verileri buradan çekiliyor.
public class ProgramlamaModel {
    @SerializedName("Programlama Dili")
    @Expose
    private String programlamaDili;
    @SerializedName("\u00c7\u0131k\u0131\u015f Tarihi")
    @Expose
    private String kTarihi;
    @SerializedName("KapakFotoUrl")
    @Expose
    private String kapakFotoUrl;
    @SerializedName("LogoUrl")
    @Expose
    private String logoUrl;
    @SerializedName("Aciklama")
    @Expose
    private String aciklama;

    public String getProgramlamaDili() {
        return programlamaDili;
    }

    public void setProgramlamaDili(String programlamaDili) {
        this.programlamaDili = programlamaDili;
    }

    public String getKTarihi() {
        return kTarihi;
    }

    public void setKTarihi(String kTarihi) {
        this.kTarihi = kTarihi;
    }

    public String getKapakFotoUrl() {
        return kapakFotoUrl;
    }

    public void setKapakFotoUrl(String kapakFotoUrl) {
        this.kapakFotoUrl = kapakFotoUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }
}
