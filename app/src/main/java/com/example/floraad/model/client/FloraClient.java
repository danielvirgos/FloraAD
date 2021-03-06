package com.example.floraad.model.client;

import com.example.floraad.model.entity.CreateResponse;
import com.example.floraad.model.entity.Flora;
import com.example.floraad.model.entity.Imagen;
import com.example.floraad.model.entity.RowsResponse;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface FloraClient {

    //Borrar imagen ruta
    @DELETE("api/imagen/{id}")
    Call<RowsResponse> deleteImagen(@Path("id") long id);

    //Intento que a partir de introudcir el nombre obtener el id //Pero no va
    @GET("api/flora/{nombre}")
    Call<Flora>getFloraNombre(@Path("nombre") String nombre);

    @DELETE("api/flora/{id}")
    Call<RowsResponse> deleteFlora(@Path("id") long id);

    @GET("api/flora/{id}")
    Call<Flora> getFlora(@Path("id") long id);

    @GET("api/flora")
    Call<ArrayList<Flora>> getFlora();

    @POST("api/flora")
    Call<CreateResponse> createFlora(@Body Flora flora);

    @PUT("api/flora/{id}")
    Call<RowsResponse> editFlora(@Path("id") long id, @Body Flora flora);

    @Multipart
    @POST("api/imagen/subir")
    Call<Long> subirImagen(@Part MultipartBody.Part file, @Part("idflora") long idFlora, @Part("descripcion") String descripcion);
}
