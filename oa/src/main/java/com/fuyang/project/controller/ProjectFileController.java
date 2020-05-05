package com.fuyang.project.controller;

import com.fuyang.bean.Attachment;
import com.fuyang.bean.AttachmentExtends;
import com.fuyang.project.service.ProjectFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
@RequestMapping("/project")
public class ProjectFileController {
@Autowired
    private ProjectFileService projectFileService;
@RequestMapping("/project-attachment-show")
    public String showFileList(Model model){
    List<AttachmentExtends> attachmentExtends= projectFileService.queryFileList();
    model.addAttribute("attachments",attachmentExtends);
    return "/project-file.jsp";
}
    //保存功能
    @RequestMapping("/project-attachment-saveInfo")
    public String projectAttachmentSaveInfo(Attachment attachment, MultipartFile[]files) throws IOException {
        if (files != null && files.length > 0){
            String path = "";
            //遍历
            for(MultipartFile f : files){
                String originalFilename = f.getOriginalFilename();
                File file = new File("D:\\OA",originalFilename);
                f.transferTo(file);
                String p = file.getName() + ";";
                path += p;
            }
            attachment.setPath(path);
        }
        projectFileService.save(attachment);
        return "redirect:/project/project-attachment-show";
    }

    //打包压缩下载
    @RequestMapping("/project-attachment-download")
    public String projectAttachmentDownload(String path, HttpServletResponse response) throws IOException {
//        System.out.println(path);
        //路径1;路径2;路径3;
        String zipPath = "D:\\OA";
        String[] split = path.split(";");
        File[] files = new File[split.length];
        for(int i = 0;i < split.length;i++){
            files[i] = new File(zipPath+"\\"+split[i]);
        }
        //开始实现下载，压缩下载
        //先压缩，后下载

        //先压缩实现：指定压缩文件名、通过压缩输出流把被压缩文件压缩到压缩文件中、释放资源
        String zipName = System.currentTimeMillis()+".zip";
        File zipFile = new File(zipPath,zipName);
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));

        //读被压缩文件
        InputStream is = null;
        for(File f : files){
            is = new FileInputStream(f);

            //向压缩文件中添加一个被压缩的文件的名称
            zipOut.putNextEntry(new ZipEntry(f.getName()));

            int temp = 0;
            while((temp = is.read()) != -1){
                zipOut.write(temp);
            }
            is.close();
        }
        zipOut.close();
        ///////////////////////////////////////////////////////////////////////////////
        //处理下载时名称中文乱码
        String name = zipFile.getName();
        //先解码，再编码
        name = new String(name.getBytes("utf-8"),"iso8859-1");
        //一个头--响应头，作用是设置下载时的文件名称的
        response.setHeader("Content-Disposition","attachment;filename="+name);
        //读服务器端本地文件的读入流
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(zipFile));
        //把服务器端文件写出到用户端的写出流
        OutputStream os = response.getOutputStream();
        byte[]bs = new byte[1024];
        int len = -1;
        while ((len = bis.read(bs))!=-1){
            os.write(bs,0,len);
        }
        os.close();
        bis.close();
        return "redirect:/project/project-attachment-show";
    }
}

