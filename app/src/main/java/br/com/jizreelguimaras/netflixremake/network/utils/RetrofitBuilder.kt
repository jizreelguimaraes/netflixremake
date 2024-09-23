package br.com.jizreelguimaras.netflixremake.network.utils

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    fun <T> provideRetrofitBuilder(baseUrl: String, clazz: Class<T>, timeout: Long): T {

        return provideRetrofitBuilder(false, baseUrl, clazz, timeout)
    }

    fun <T> provideRetrofitBuilder(baseUrl: String, clazz: Class<T>): T {

        return provideRetrofitBuilder(false, baseUrl, clazz, OK_HTTP_TIMEOUT)
    }

    fun <T> provideRetrofitBuilder(
        isDebug: Boolean,
        baseUrl: String,
        clazz: Class<T>,
        timeout: Long
    ): T {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient(createLoggingInterceptor(isDebug), timeout))
            .build()
            .create(clazz)
    }

    private fun createOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        timeout: Long
    ): OkHttpClient {

        try {

            return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .build()
        } catch (e: KeyManagementException) {

            e.printStackTrace();
        } catch (e: NoSuchAlgorithmException) {

            e.printStackTrace();
        } catch (e: KeyStoreException) {

            e.printStackTrace();
        }

        return OkHttpClient.Builder().build()
    }

    private fun createLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {

        return HttpLoggingInterceptor().apply {

            level = if (isDebug) {

                HttpLoggingInterceptor.Level.BASIC
            } else {

                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    private const val OK_HTTP_TIMEOUT = 3L
}