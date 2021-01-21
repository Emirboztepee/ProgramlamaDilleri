package com.hr190012.emirdursunboztepefinal.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.hr190012.emirdursunboztepefinal.model.ProgramlamaModel;
import com.hr190012.emirdursunboztepefinal.R;
import com.hr190012.emirdursunboztepefinal.util.GlideUtil;

import java.util.List;

public class ProgramlamaAdaptor extends RecyclerView.Adapter<ProgramlamaViewHolder> {


    List<ProgramlamaModel> diller;
    Context context;
    OnItemClickListener listener;

    public interface OnItemClickListener {
        void onClik(int position);

    }


    public ProgramlamaAdaptor(List<ProgramlamaModel> diller, Context context,OnItemClickListener listener) {
        this.diller = diller;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProgramlamaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_kod_diller,parent,false);
        return new ProgramlamaViewHolder(itemView,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramlamaViewHolder viewHolder, int position) {

        //cardview için dil adı, tarih ve logoyu çekerek cardviewe basıyor.
        viewHolder.txtDilAdi.setText(diller.get(position).getProgramlamaDili());
        viewHolder.txtTarih.setText(diller.get(position).getKTarihi());
        GlideUtil.resmiIndiripGoster(context,diller.get(position).getLogoUrl(),viewHolder.imgDilLogo);

    }

    @Override
    public int getItemCount() {
        return diller.size();
    }
}
