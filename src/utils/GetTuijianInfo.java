package utils;

import FormatOut.JsonOutFormat;
import FormatOut.OutFormat;
import FormatOut.StringOutFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Keen on 11/27/2016.
 */
@WebServlet(name = "GetTuijianInfo")
public class GetTuijianInfo extends HttpServlet {

    private  ExecuteSql executeSql;

    /**
     * Constructor of the object.
     */
    public GetTuijianInfo() {
        super();
        executeSql = new ExecuteSql();
    }


    public OutFormat setOutFormat(OutFormat outFormat){
        return outFormat;
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * The doGet method of the servlet. <br>
     *
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException if an error occurred
     * http://localhost:8080/NetSecWebServer/servlet/GetTuijianInfo?index=0 获取前30条新闻列表（默认是每次返回30条信息）
     * http://localhost:8080/NetSecWebServer/servlet/GetTuijianInfo?index=58 获取id=58之前的30条新闻列表
     * http://192.168.56.1:8080/NetSecWebServer/servlet/GetTuijianInfo?id=1 获取id=1的新闻
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String index = request.getParameter("index");
        String id = request.getParameter("id");
        //获取资讯列表
        if(index != null &&  id == null){

            List<Object> listResult = new ArrayList<>();
            listResult = executeSql.getTuijianList(index);
            OutFormat outFormat = setOutFormat(new JsonOutFormat());
            out.println(outFormat.OutString(listResult));
        }else if(id != null && index == null){
            List<Object> listResult = new ArrayList<>();
            listResult = executeSql.getTuijianContent(id);
            OutFormat outFormat = setOutFormat(new StringOutFormat());
            out.println(outFormat.OutString(listResult));
        }

    }
}
