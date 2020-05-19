package com.ja.freeboard.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDownloadUtil extends AbstractView {

	public FileDownloadUtil() {
        // 객체가 생성될 때 Content Type을 다음과 같이 변경 
		setContentType("application/download; utf-8");
    }
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		File file = (File)model.get("downloadFile");
        
        response.setContentType(getContentType());
        response.setContentLength((int) file.length());
        

        String fileName = null;
        
        // 브라우저, 운영체제정보
        String userAgent = request.getHeader("User-Agent");

        // IE 11
        if (userAgent.indexOf("Trident") > -1) {
            fileName = URLEncoder.encode(file.getName(), "UTF-8");
        }
        
        else {
            fileName = new String(file.getName().getBytes("UTF-8"));
        }
 
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        
        OutputStream out = response.getOutputStream();
 
        FileInputStream fis = null;
		
        try {
            fis = new FileInputStream(file);
            FileCopyUtils.copy(fis, out);
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            if(fis != null){
                try{
                    fis.close();
                }catch(Exception e){}
            }
        }// try end;
        out.flush();
        
    }
}        