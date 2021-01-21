package com.hr190012.emirdursunboztepefinal.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.hr190012.emirdursunboztepefinal.R;

public class E1_SplashActivity extends AppCompatActivity {
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread splash = new Thread(){
            @Override
            public void run(){
                try{
                    ImageView logo = findViewById(R.id.İmageView_Logo);
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splash_animation);
                    logo.startAnimation(animation);
                    sleep(3000);
                    //logoyu belirliyoruz ve ekranda 3 saniye kalmasını sağlıyoruz.
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                   nextActivity();
                   //aşağıdaki methodu çağırarak E2 ekranına geçişi sağıyoruz.
                }

            }
        };

        internetKontrol();  //aşağıdaki methodu buraya çağırarak çalıştırdık ve internet kontrolünü sağladık

        if (internetKontrol()){ //internet var ise splash ekranını başlattık.
            splash.start();
        }else{  //internet yok ise alertDialogu çağırdık ve seçenekler sunduk.
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle(getString(R.string.connection_alert_title))
                    .setMessage(getString(R.string.connection_alert_message))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.connection_alert_settings), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                            //Ayarları aç butonuna tıklandığında wifi ayarlarına gidiyoruz.
                        }
                    })
                    .setNegativeButton(getString(R.string.connection_alert_exit), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            finish();
                            // Çıkış Yap butonuyla programı sonlandırıyoruz.
                        }

                    });
            alertDialog.show();
        }
    }

    public boolean internetKontrol(){ //internet kontrolünü yaptığımız ve yukarıda if içerisinde kullandığımız method
        ConnectivityManager manager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager.getActiveNetworkInfo() != null
                && manager.getActiveNetworkInfo().isAvailable()
                && manager.getActiveNetworkInfo().isConnected()) {
            return true;
        } else{
            return false;
        }
    }

    public void nextActivity(){             //E2 ekranına geçmek için kullandığımız method
        Intent intent = new Intent(context, E2_ListActivity.class);
        startActivity(intent);
        finish();
    }
}

