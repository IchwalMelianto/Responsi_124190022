package com.responsi.covidjawabarat.Hospital;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.responsi.covidjawabarat.Service.ApiMain;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalViewModel extends ViewModel {
    private ApiMain apiMain;

    private MutableLiveData<ArrayList<DataItem>> listMutableLiveData = new MutableLiveData<ArrayList<DataItem>>();

    public void setListMutableLiveData(){
        if (this.apiMain == null){
            apiMain = new ApiMain();
        }

        apiMain.getApiCovid().getHospital().enqueue(new Callback<HospitalResponse>() {
            @Override
            public void onResponse(Call<HospitalResponse> call, Response<HospitalResponse> response) {
                HospitalResponse hospitalResponse = response.body();
                if (hospitalResponse != null && hospitalResponse.getData() != null){
                    ArrayList<DataItem> dataItems = hospitalResponse.getData();
                    listMutableLiveData.postValue(dataItems);
                }
            }

            @Override
            public void onFailure(Call<HospitalResponse> call, Throwable t) {

            }
        });
    }

    public MutableLiveData<ArrayList<DataItem>> getListMutableLiveData(){
        return listMutableLiveData;
    }
}
