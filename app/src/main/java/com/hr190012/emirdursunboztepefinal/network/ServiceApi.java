package com.hr190012.emirdursunboztepefinal.network;



import com.hr190012.emirdursunboztepefinal.model.ProgramlamaModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ServiceApi {

    //base url
    //
    @GET("applicationData.json")
    Observable<List<ProgramlamaModel>> datayiGetir();
    //json dosyasını çağırarak E2 ekranındaki datayıGetir methoduna yollar.
}
