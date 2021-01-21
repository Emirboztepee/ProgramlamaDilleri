package com.hr190012.emirdursunboztepefinal.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hr190012.emirdursunboztepefinal.R;
import com.hr190012.emirdursunboztepefinal.model.ProgramlamaModel;
import com.hr190012.emirdursunboztepefinal.util.Constants;
import com.hr190012.emirdursunboztepefinal.util.GlideUtil;
import com.hr190012.emirdursunboztepefinal.util.ObjectUtil;

public class E3_DetayActivity extends AppCompatActivity {

    ImageView imgKapak;
    TextView txtBaslik;
    TextView txtDetay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        init(); //aşağıdaki init methodunu çağırıp burada çalıştırarak temiz kod yapıyoruz.
    }

    private void init(){
        String tasinanDilString = getIntent().getStringExtra(Constants.TIKLANAN_DIL_TASINANIN_BASLIGI);
        ProgramlamaModel programlamaModel = ObjectUtil.jsonStringToDil(tasinanDilString);
        //tıklandığında modelden getirilecek dil çağırılıyor.

        //detay layoutundaki text ve viewleri classa bağlıyoruz.
        imgKapak =findViewById(R.id.imgKapak);
        txtBaslik =findViewById(R.id.txtBaslik);
        txtDetay =findViewById(R.id.txtDetay);

        //jsondaki verileri model classından çekerek detay 3 ekranına veriyor.
        txtBaslik.setText(programlamaModel.getProgramlamaDili());
        txtDetay.setText(programlamaModel.getAciklama());

        GlideUtil.resmiIndiripGoster(getApplicationContext(),programlamaModel.getKapakFotoUrl(),imgKapak);
        //dillerin kapak fotolarını jsondan çekerek imgKapak viewinde gösteriyor.
    }
}