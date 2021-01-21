package com.hr190012.emirdursunboztepefinal.adaptor;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hr190012.emirdursunboztepefinal.R;


public class ProgramlamaViewHolder extends RecyclerView.ViewHolder {

    ImageView imgDilLogo;
    TextView txtDilAdi;
    TextView txtTarih;

    public ProgramlamaViewHolder(@NonNull View itemView, ProgramlamaAdaptor.OnItemClickListener listener) {
        super(itemView);

        imgDilLogo =itemView.findViewById(R.id.imgDilLogo);
        txtDilAdi =itemView.findViewById(R.id.txtDilAdi);
        txtTarih =itemView.findViewById(R.id.txtTarih);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClik(getAdapterPosition());
            }
        });
    }
}
