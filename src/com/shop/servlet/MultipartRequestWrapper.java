package com.shop.servlet;

import com.shop.utils.PropertiesUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MultipartRequestWrapper extends HttpServletRequestWrapper {
	
    private  final static  String PATH = PropertiesUtils.getValue("upload_path");
    Map<String,String[]> params = new HashMap<>();
    public MultipartRequestWrapper(HttpServletRequest request) {
        super(request);
        setParams(request);
    }

    private void setParams(HttpServletRequest request) {
        try {
            boolean ismul = ServletFileUpload.isMultipartContent(request);
            if(ismul){
                ServletFileUpload servletFileUpload = new ServletFileUpload();
                FileItemIterator itemIterator =  servletFileUpload.getItemIterator(request);
                InputStream is = null;
                while (itemIterator.hasNext()){
                    FileItemStream fis = itemIterator.next();
                    is = fis.openStream();
                    if(fis.isFormField()){
                        setFormParam(fis.getFieldName(),is);
//                        params.put(fis.getFieldName(),new String[]{Streams.asString(is)});
                    }else {
                        if(is.available()>0){
//                            String path_ = request.getSession().getServletContext().getRealPath("/upload/");

                            Streams.copy(is,new FileOutputStream(PATH+"/"+fis.getName()),true);
                            params.put(fis.getFieldName(),new String[]{PropertiesUtils.getValue("upload_url")+fis.getName()});
                        }

                    }
                }
            }else {
                params = request.getParameterMap();
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setFormParam(String fieldName, InputStream is) throws IOException {
        if(params.containsKey(fieldName)){
            String[] values = params.get(fieldName);
            values = Arrays.copyOf(values,values.length+1);
            values[values.length-1]=Streams.asString(is);
            params.put(fieldName,values);
        }else {
            params.put(fieldName,new String[]{Streams.asString(is)});
        }
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return params;
    }

    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if(values!=null){
            return  values[0];
        }
        return null;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = params.get(name);
        if(values!=null){
            return  values;
        }
        return null;
    }

}
