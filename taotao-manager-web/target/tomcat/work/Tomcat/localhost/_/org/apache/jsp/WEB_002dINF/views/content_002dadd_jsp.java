/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-08-05 02:44:17 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class content_002dadd_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<link href=\"/js/kindeditor-4.1.10/themes/default/default.css\" type=\"text/css\" rel=\"stylesheet\">\r\n");
      out.write("<script type=\"text/javascript\" charset=\"utf-8\" src=\"/js/kindeditor-4.1.10/kindeditor-all-min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" charset=\"utf-8\" src=\"/js/kindeditor-4.1.10/lang/zh_CN.js\"></script>\r\n");
      out.write("<div style=\"padding:10px 10px 10px 10px\">\r\n");
      out.write("\t<form id=\"contentAddForm\" class=\"itemForm\" method=\"post\">\r\n");
      out.write("\t\t<input type=\"hidden\" name=\"categoryId\"/>\r\n");
      out.write("\t    <table cellpadding=\"5\">\r\n");
      out.write("\t        <tr>\r\n");
      out.write("\t            <td>内容标题:</td>\r\n");
      out.write("\t            <td><input class=\"easyui-textbox\" type=\"text\" name=\"title\" data-options=\"required:true\" style=\"width: 280px;\"></input></td>\r\n");
      out.write("\t        </tr>\r\n");
      out.write("\t        <tr>\r\n");
      out.write("\t            <td>内容子标题:</td>\r\n");
      out.write("\t            <td><input class=\"easyui-textbox\" type=\"text\" name=\"subTitle\" style=\"width: 280px;\"></input></td>\r\n");
      out.write("\t        </tr>\r\n");
      out.write("\t        <tr>\r\n");
      out.write("\t            <td>内容描述:</td>\r\n");
      out.write("\t            <td><input class=\"easyui-textbox\" name=\"titleDesc\" data-options=\"multiline:true,validType:'length[0,150]'\" style=\"height:60px;width: 280px;\"></input>\r\n");
      out.write("\t            </td>\r\n");
      out.write("\t        </tr>\r\n");
      out.write("\t         <tr>\r\n");
      out.write("\t            <td>URL:</td>\r\n");
      out.write("\t            <td><input class=\"easyui-textbox\" type=\"text\" name=\"url\" style=\"width: 280px;\"></input></td>\r\n");
      out.write("\t        </tr>\r\n");
      out.write("\t        <tr>\r\n");
      out.write("\t            <td>图片:</td>\r\n");
      out.write("\t            <td>\r\n");
      out.write("\t                <a href=\"javascript:void(0)\" class=\"easyui-linkbutton onePicUpload\">图片上传</a>\r\n");
      out.write("\t                <br><input type=\"hidden\" name=\"pic\" />\r\n");
      out.write("\t            </td>\r\n");
      out.write("\t        </tr>\r\n");
      out.write("\t        <tr>\r\n");
      out.write("\t            <td>图片2:</td>\r\n");
      out.write("\t            <td>\r\n");
      out.write("\t            \t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton onePicUpload\">图片上传</a>\r\n");
      out.write("\t            \t<br><input type=\"hidden\" name=\"pic2\" />\r\n");
      out.write("\t            </td>\r\n");
      out.write("\t        </tr>\r\n");
      out.write("\t        <tr>\r\n");
      out.write("\t            <td>内容:</td>\r\n");
      out.write("\t            <td>\r\n");
      out.write("\t                <textarea style=\"width:800px;height:300px;visibility:hidden;\" name=\"content\"></textarea>\r\n");
      out.write("\t            </td>\r\n");
      out.write("\t        </tr>\r\n");
      out.write("\t    </table>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t<div style=\"padding:5px\">\r\n");
      out.write("\t    <a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" onclick=\"submitForm()\">提交</a>\r\n");
      out.write("\t    <a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" onclick=\"clearForm()\">重置</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t//编辑器参数\r\n");
      out.write("\tkingEditorParams = {\r\n");
      out.write("\t\tfilePostName  : \"uploadFile\",   \r\n");
      out.write("\t\tuploadJson : '/rest/pic/upload',\t\r\n");
      out.write("\t\tdir : \"image\" \r\n");
      out.write("\t};\r\n");
      out.write("\r\n");
      out.write("\tvar contentAddEditor ;\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t//创建富文本编辑器\r\n");
      out.write("\t\tcontentAddEditor =  KindEditor.create(\"#contentAddForm [name=content]\", kingEditorParams);\r\n");
      out.write("\t\t//初始化单图片上传\r\n");
      out.write("\t\tinitOnePicUpload();\r\n");
      out.write("\t\t//把内容分类id放到input中，提交到后台\r\n");
      out.write("\t\t$(\"#contentAddForm [name=categoryId]\").val($(\"#contentCategoryTree\").tree(\"getSelected\").id);\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t//提交逻辑\r\n");
      out.write("\tfunction submitForm(){\r\n");
      out.write("\t\t//校验\r\n");
      out.write("\t\tif(!$('#contentAddForm').form('validate')){\r\n");
      out.write("\t\t\t$.messager.alert('提示','表单还未填写完成!');\r\n");
      out.write("\t\t\treturn ;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//编辑器的同步，把编辑器的内容同步到多行文本域中\r\n");
      out.write("\t\tcontentAddEditor.sync();\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//提交到后台的RESTful\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t   type: \"POST\",\r\n");
      out.write("\t\t   url: \"/rest/content\",\r\n");
      out.write("\t\t   //把表单的元素序列化，拼装成key=value&key2=value2&key3=value3格式\r\n");
      out.write("\t\t   data: $(\"#contentAddForm\").serialize(),\r\n");
      out.write("\t\t   success: function(msg){\r\n");
      out.write("\t\t\t   if(msg == \"0\"){\r\n");
      out.write("\t\t\t\t   $.messager.alert('提示','新增内容成功!');\r\n");
      out.write("\t\t\t\t   //重新加载datagrid\r\n");
      out.write("\t \t\t\t   $(\"#contentList\").datagrid(\"reload\");\r\n");
      out.write("\t\t\t   }else{\r\n");
      out.write("\t\t\t\t   $.messager.alert('提示','新增内容失败!');\r\n");
      out.write("\t\t\t   }\r\n");
      out.write("\t\t\t   \r\n");
      out.write("\t\t\t   //关闭弹窗\r\n");
      out.write(" \t\t\t   TT.closeCurrentWindow();\r\n");
      out.write("\t\t   },\r\n");
      out.write("\t\t   error: function(){\r\n");
      out.write("\t\t\t   $.messager.alert('提示','新增内容失败!');\r\n");
      out.write("\t\t   }\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction clearForm(){\r\n");
      out.write("\t\t$('#contentAddForm').form('reset');\r\n");
      out.write("\t\tcontentAddEditor.html('');\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//初始化单图片上传\r\n");
      out.write("\tfunction initOnePicUpload(){\r\n");
      out.write("\t\t//获取上传按钮，绑定点击事件\r\n");
      out.write("    \t$(\".onePicUpload\").click(function(){\r\n");
      out.write("    \t\t//this就是按钮，获取同级的input元素\r\n");
      out.write("\t\t\tvar input = $(this).siblings(\"input\");\r\n");
      out.write("\t\t\t//加载单图片上传组件\r\n");
      out.write("\t\t\tKindEditor.editor(kingEditorParams).loadPlugin('image', function() {\r\n");
      out.write("\t\t\t\tthis.plugin.imageDialog({\r\n");
      out.write("\t\t\t\t\tshowRemote : false,\r\n");
      out.write("\t\t\t\t\t//点击“确定”按钮执行逻辑\r\n");
      out.write("\t\t\t\t\tclickFn : function(url, title, width, height, border, align) {\r\n");
      out.write("\t\t\t\t\t\t//获取同级的img标签，清除，其实就是回显前，清除原来的图片\r\n");
      out.write("\t\t\t\t\t\tinput.parent().find(\"img\").remove();\r\n");
      out.write("\t\t\t\t\t\tinput.val(url);\r\n");
      out.write("\t\t\t\t\t\t//在input标签后添加a标签，里面是img标签，其实就是图片回显\r\n");
      out.write("\t\t\t\t\t\tinput.after(\"<a href='\"+url+\"' target='_blank'><img src='\"+url+\"' width='80' height='50'/></a>\");\r\n");
      out.write("\t\t\t\t\t\t//关闭上传界面\r\n");
      out.write("\t\t\t\t\t\tthis.hideDialog();\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t});\r\n");
      out.write("    }\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\r\n");
      out.write("</script>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
