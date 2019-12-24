package cn.wthee.face.controller

import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.io.File


@RestController
@RequestMapping("/face")
class MainController {

    @PostMapping("/upload")
    fun uploadFile(@RequestParam("file") multipartFile: MultipartFile?, @RequestParam("userId") userId: String){
        val dir = System.getProperty("user.dir")+ "/data/" + userId // data文件夹需手动创建
        val path = File(dir)
        if (!path.exists()){//若此目录不存在，则创建
            path.mkdir();
            println(message = "创建文件夹路径为：$path")
        }
        //保存文件
        multipartFile?.transferTo(File(dir+ "/"+multipartFile?.originalFilename))
    }

}