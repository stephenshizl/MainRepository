package com.example.a61555.okhttpdemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Translation {

    @SerializedName("word_name")
    @Expose
    public String wordName;
    @SerializedName("is_CRI")
    @Expose
    public int isCRI;
    @SerializedName("exchange")
    @Expose
    public Exchange exchange;
    @SerializedName("symbols")
    @Expose
    public List<Symbol> symbols = null;
    @SerializedName("items")
    @Expose
    public List<String> items = null;

    @Override
    public String toString() {
        return wordName+"\n"
                +exchange.toString()+"\n"
                +symbols.toString();
    }
}

class Exchange {

    @SerializedName("word_pl")
    @Expose
    public List<String> wordPl;
    @SerializedName("word_past")
    @Expose
    public List<String> wordPast;
    @SerializedName("word_done")
    @Expose
    public List<String> wordDone;
    @SerializedName("word_ing")
    @Expose
    public List<String> wordIng;
    @SerializedName("word_third")
    @Expose
    public List<String> wordThird;
    @SerializedName("word_er")
    @Expose
    public List<String> wordEr;
    @SerializedName("word_est")
    @Expose
    public List<String> wordEst;

    @Override
    public String toString() {
        return  "【变形】："+"\n"+
                "复数："+(wordPl.size()==0?"无":wordPl.get(0))+"\n"
                +"过去式："+(wordPast.size()==0?"无":wordPast.get(0))+"\n"
                +"过去分词："+(wordDone.size()==0?"无":wordDone.get(0))+"\n"
                +"进行式："+(wordIng.size()==0?"无":wordIng.get(0))+"\n"
                +"第三人称单数："+(wordThird.size()==0?"无":wordThird.get(0))+"\n"
                +"比较级："+(wordEr.size()==0?"无":wordEr.get(0))+"\n"
                +"最高级："+(wordEst.size()==0?"无":wordEst.get(0));
    }
}

class Part {

    @SerializedName("part")
    @Expose
    public String part;
    @SerializedName("means")
    @Expose
    public List<String> means = null;

    @Override
    public String toString() {
        return "【类型】："+part+"\n"
                +"【释义】："+means;
    }
}

class Symbol {

    @SerializedName("ph_en")
    @Expose
    public String phEn;
    @SerializedName("ph_am")
    @Expose
    public String phAm;
    @SerializedName("ph_other")
    @Expose
    public String phOther;
    @SerializedName("ph_en_mp3")
    @Expose
    public String phEnMp3;
    @SerializedName("ph_am_mp3")
    @Expose
    public String phAmMp3;
    @SerializedName("ph_tts_mp3")
    @Expose
    public String phTtsMp3;
    @SerializedName("parts")
    @Expose
    public List<Part> parts = null;

    @Override
    public String toString() {
        String result = "";
        for (Part part : parts)
            result += "\n"+part.toString();
        return result+"\n";
    }
}
