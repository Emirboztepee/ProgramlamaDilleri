package com.hr190012.emirdursunboztepefinal.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.hr190012.emirdursunboztepefinal.R;
import com.hr190012.emirdursunboztepefinal.adaptor.ProgramlamaAdaptor;
import com.hr190012.emirdursunboztepefinal.model.ProgramlamaModel;
import com.hr190012.emirdursunboztepefinal.network.Service;
import com.hr190012.emirdursunboztepefinal.util.Constants;
import com.hr190012.emirdursunboztepefinal.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class E2_ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        datayiGetir();
        progressCagir();
    }

    public void progressCagir() {

        //E2 ekranı açılana kadar "Lütfen Bekleyiniz.." yazısını progress ile kullanıcıya gösterir.
        CountDownTimer countDownTimer = new CountDownTimer(1000, 1000) {
            public void onTick(long millisUntilFinished) {
                pd = new ProgressDialog(E2_ListActivity.this);
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.setMessage(getString(R.string.progress_loading));
                pd.setIndeterminate(true);
                pd.setCancelable(true);
                pd.show();
            }

            public void onFinish() {
                pd.dismiss();
            }
            ;
        }.start();
    }

    void  datayiGetir()
    {
        //modelde yer alan dilleri çeker ve liste haline alır.
        new Service().getServiceApi().datayiGetir().
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ProgramlamaModel>>() {

                    List<ProgramlamaModel> diller=new ArrayList<>();

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("RETROFIT","onSubscribe : ");
                    }

                    @Override
                    public void onNext(List<ProgramlamaModel> dilList) {
                        Log.e(getString(R.string.retrofit),getString(R.string.on_next));
                        diller=dilList;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(getString(R.string.retrofit),getString(R.string.on_Error) + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete()
                    {
                        Log.e(getString(R.string.retrofit),getString(R.string.on_complete));
                        if(diller.size()>0) {
                            initRecycleView(diller);
                        }
                    }
                });
    }

    private  void  initRecycleView(List<ProgramlamaModel> dilList)
    {
        //modelden çekilmiş olan dil listesi cardview şeklinde gösteriliyor.
        recyclerView =findViewById(R.id.rcvDiller);
        ProgramlamaAdaptor ProgramlamaAdaptor =new ProgramlamaAdaptor(dilList, getApplicationContext(), new ProgramlamaAdaptor.OnItemClickListener() {
            @Override
            public void onClik(int position) {
                ProgramlamaModel tiklananDil =dilList.get(position);
                opennextActivity(tiklananDil);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(ProgramlamaAdaptor);
    }

    private void opennextActivity(ProgramlamaModel tiklananDil){
        //hangi dile bastıysak o Dilin modelden çekilerek E3 ekranında gösterilmesi sağlanıyor.

        Intent secondActivityIntent=new Intent(getApplicationContext(),E3_DetayActivity.class);
        String tiklananDilString= ObjectUtil.dilToJsonString(tiklananDil);
        secondActivityIntent.putExtra(Constants.TIKLANAN_DIL_TASINANIN_BASLIGI,tiklananDilString);
        startActivity(secondActivityIntent);


    }

    //geri tuşuna basıldığında gelecek ekranın methodu yazıldı.
    //alert dialog koyularak çıkış yapılmak istenip istenmemesi soruldu.
    @Override    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage(getString(R.string.exit_alert_message));
        builder.setPositiveButton(getString(R.string.exit_alert_yes), new DialogInterface.OnClickListener() {
            @Override            public void onClick(DialogInterface dialog, int which) {
                //cevap evet ise program kapatılıyor.
                finish();
            }
        });
        builder.setNegativeButton(getString(R.string.exit_alert_no), new DialogInterface.OnClickListener() {
            @Override            public void onClick(DialogInterface dialog, int which) {
                //cevap hayır ise alert dialog kapatılarak ekrana dönülüyor.
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}