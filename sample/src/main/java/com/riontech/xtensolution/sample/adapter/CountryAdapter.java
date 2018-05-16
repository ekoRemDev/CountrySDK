package com.riontech.xtensolution.sample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.riontech.xtensolution.sample.R;
import com.riontech.xtensolution.sample.activity.CountryDetailsActivity;
import com.xtensolutions.country.model.Country;

import java.util.ArrayList;

/**
 * Created by Riontech
 * Vaghela Mithun R.
 * vaghela.mithun@gmail.com,
 * 9727206702
 * on 15-Mar-18.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryHolder> {
    private ArrayList<Country> countries;
    private LayoutInflater inflater;

    public enum VIEW_TYPE {GRID, LIST}

    private VIEW_TYPE viewType;

    public CountryAdapter(Context context, ArrayList<Country> countries, VIEW_TYPE viewType) {
        inflater = LayoutInflater.from(context);
        this.countries = countries;
        this.viewType = viewType;
    }

    public void setViewType(VIEW_TYPE viewType) {
        this.viewType = viewType;
    }

    @Override
    public int getItemViewType(int position) {
        if (viewType == VIEW_TYPE.GRID)
            return 0;
        else
            return 1;
    }

    @Override
    public CountryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_item_list, null);
        if (viewType == 0)
            view = inflater.inflate(R.layout.row_item_grid, null);
        return new CountryHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryHolder holder, int position) {
        holder.bindData(countries.get(position));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }
}

class CountryHolder extends RecyclerView.ViewHolder {
    private TextView txtName;
    private TextView txtCapital;
    private TextView txtPopulation;
    private ImageView imgFlag;

    public CountryHolder(View itemView) {
        super(itemView);
        txtName = itemView.findViewById(R.id.txtCountryNameAndCode);
        txtCapital = itemView.findViewById(R.id.txtCountryCapital);
        txtPopulation = itemView.findViewById(R.id.txtPopulationValue);
        imgFlag = itemView.findViewById(R.id.imgFlag);
    }

    public void bindData(final Country country) {

        try {
            txtName.setText(country.getCountryName() + "(" + country.getCountryCode() + ")");
            txtPopulation.setText(country.getPopulation());
            txtCapital.setText("Capital : " + country.getCapital());
            imgFlag.setImageResource(country.getSmallFlag(itemView.getContext()));
//            Glide.with(itemView.getContext()).load(country.getSmallFlag(itemView.getContext())).into(imgFlag);
            itemView.setTag(country);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Country c = (Country) view.getTag();
                    CountryDetailsActivity.startDetailActivity(view.getContext(), country);
                }
            });
//        Drawable drawable = CountryUtils.getSmallFlag(country.getCountryCode(), itemView.getContext());
//        if (drawable != null)
//            imgFlag.setImageDrawable(drawable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
