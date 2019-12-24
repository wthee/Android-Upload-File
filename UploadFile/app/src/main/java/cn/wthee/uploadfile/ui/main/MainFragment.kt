package cn.wthee.uploadfile.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import cn.wthee.uploadfile.R
import cn.wthee.uploadfile.service.UploadFileService
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.File


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        //构建要上传的文件，测试文件路径/storage/emulated/0/Android/media/cn.wthee.uploadfile/test.txt
        val saveFile = File(activity?.externalMediaDirs?.first(), "test.txt")
        uploadFile(saveFile)
    }

    private fun uploadFile(file: File) {
        val requestFile =
            RequestBody.create(MediaType.parse("application/otcet-stream"), file)
        //name 必须和服务器接收的@RequestParam("file") 一致
        val body = MultipartBody
            .Part.createFormData("file", file.name, requestFile)
        //服务端请求地址
        val builder = Retrofit.Builder()
            .baseUrl("http://192.168.43.48:1111/face/")
        //创建服务，发送文件
        val service = builder.build().create(UploadFileService::class.java)
        service.upload(body, "user001").enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("upload", "上传失败")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.e("upload", "上传成功")
            }
        })
    }
}
