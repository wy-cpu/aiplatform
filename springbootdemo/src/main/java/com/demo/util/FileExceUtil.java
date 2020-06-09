package com.demo.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileExceUtil {

    public static void createFile(HttpServletResponse response, HSSFWorkbook workbook) {
        // 设置文件名
        String fileName ="数据信息";
        try {
            // 捕获内存缓冲区的数据，转换成字节数组
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            // 获取内存缓冲中的数据
            byte[] content = out.toByteArray();
            // 将字节数组转化为输入流
            InputStream in = new ByteArrayInputStream(content);
            //通过调用reset（）方法可以重新定位。
            response.reset();
            // 如果文件名是英文名不需要加编码格式，如果是中文名需要添加"iso-8859-1"防止乱码
            response.setHeader("Content-Disposition", "attachment; filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
            response.addHeader("Content-Length", "" + content.length);
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            ServletOutputStream outputStream = response.getOutputStream();
            BufferedInputStream bis = new BufferedInputStream(in);
            BufferedOutputStream bos = new BufferedOutputStream(outputStream);
            byte[] buff = new byte[8192];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bis.close();
            bos.close();
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * <pre>ExcelDownload 下载excel文件方法
     * 创建人：滕宁 15039635948@163.com
     * 创建时间：2018-3-9 下午2:04:04
     * 修改人：滕宁 15039635948@163.com
     * 修改时间：2018-3-9 下午2:04:04
     * 修改备注：
     */
   /* public static void excelDownload(XSSFWorkbook workBook, HttpServletResponse response) {
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            //让浏览器识别是什么类型的文件
            response.reset(); // 重点突出
            // 重点突出
            response.setCharacterEncoding("UTF-8");
            // 不同类型的文件对应不同的MIME类型 // 重点突出
            response.setContentType("application/x-msdownload");
            // inline在浏览器中直接显示，不提示用户下载
            // attachment弹出对话框，提示用户进行下载保存本地
            // 默认为inline方式
            response.setHeader("Content-Disposition", "attachment;filename="+UUID.randomUUID().toString()+".xlsx");
            workBook.write(out);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if( null != out){
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }*/
}
