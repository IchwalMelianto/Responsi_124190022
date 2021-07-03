package com.responsi.covidjawabarat.Covid;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.responsi.covidjawabarat.Service.ApiMain;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidViewModel extends ViewModel {
    private ApiMain apiMain;

    private MutableLiveData<ArrayList<ContentItem>> listMutableLiveData = new MutableLiveData<ArrayList<ContentItem>>();

    public void setListMutableLiveData(){
        if (this.apiMain == null){
            apiMain = new ApiMain();
        }

        apiMain.getApiCovid().getCovid().enqueue(new Callback<CovidResponse>() {
            @Override
            public void onResponse(Call<CovidResponse> call, Response<CovidResponse> response) {
                CovidResponse covidResponse = response.body();
                if (covidResponse != null && covidResponse.getData() != null){
                    ArrayList<ContentItem> contentItems = covidResponse.getData().getContent();
                    listMutableLiveData.postValue(contentItems);
                }
            }

            @Override
            public void onFailure(Call<CovidResponse> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<ArrayList<ContentItem>> getListMutableLiveData(){
        return listMutableLiveData;
    }
}
