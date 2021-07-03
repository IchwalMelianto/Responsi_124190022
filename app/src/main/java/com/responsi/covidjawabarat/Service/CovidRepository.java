package com.responsi.covidjawabarat.Service;

import com.responsi.covidjawabarat.Covid.CovidResponse;
import com.responsi.covidjawabarat.Hospital.HospitalResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidRepository {
    @GET("rekapitulasi_v2/jabar/harian")
    Call<CovidResponse> getCovid();

    @GET("sebaran_v2/jabar/faskes")
    Call<HospitalResponse> getHospital();
}
