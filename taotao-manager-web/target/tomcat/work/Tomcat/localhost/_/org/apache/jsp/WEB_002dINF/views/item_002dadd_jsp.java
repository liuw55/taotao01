/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-08-04 13:06:26 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class item_002dadd_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t<form id=\"itemAddForm\" class=\"itemForm\" method=\"post\">\r\n");
      out.write("\t    <table cellpadding=\"5\">\r\n");
      out.write("\t        <tr>\r\n");
      out.write("\t            <td>商品类目:</td>\r\n");
      out.write("\t            <td>\r\n");
      out.write("\t            \t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton selectItemCat\">选择类目</a>\r\n");
      out.write("\t            \t<span ></span>\r\n");
      out.write("\t            \t<input type=\"hidden\" name=\"cid\" style=\"width: 280px;\"></input>\r\n");
      out.write("\t            </td>\r\n");
      out.write("\t        </tr>\r\n");
      out.write("\t        <tr>\r\n");
      out.write("\t            <td>商品标题:</td>\r\n");
      out.write("\t            <td><input class=\"easyui-textbox\" type=\"text\" name=\"title\" data-options=\"required:true\" style=\"width: 280px;\"></input></td>\r\n");
      out.write("\t        </tr>\r\n");
      out.write("\t        <tr>\r\n");
      out.write("\t            <td>商品卖点:</td>\r\n");
      out.write("\t            <td><input class=\"easyui-textbox\" name=\"sellPoint\" data-options=\"multiline:true,validType:'length[0,150]'\" style=\"height:60px;width: 280px;\"></input></td>\r\n");
      out.write("\t        </tr>\r\n");
      out.write("\t        <tr>\r\n");
      out.write("\t            <td>商品价格:</td>\r\n");
      out.write("\t            <td><input class=\"easyui-numberbox\" type=\"text\" name=\"price\" data-options=\"min:1,max:99999999,precision:2,required:true\" />\r\n");
      out.write("\t            </td>\r\n");
      out.write("\t        </tr>\r\n");
      out.write("\t        <tr>\r\n");
      out.write("\t            <td>库存数量:</td>\r\n");
      out.write("\t            <td><input class=\"easyui-numberbox\" type=\"text\" name=\"num\" data-options=\"min:1,max:99999999,precision:0,required:true\" /></td>\r\n");
      out.write("\t        </tr>\r\n");
      out.write("\t        <tr>\r\n");
      out.write("\t            <td>条形码:</td>\r\n");
      out.write("\t            <td>\r\n");
      out.write("\t                <input class=\"easyui-textbox\" type=\"text\" name=\"barcode\" data-options=\"validType:'length[1,30]'\" />\r\n");
      out.write("\t            </td>\r\n");
      out.write("\t        </tr>\r\n");
      out.write("\t        <tr>\r\n");
      out.write("\t            <td>商品图片:</td>\r\n");
      out.write("\t            <td>\r\n");
      out.write("\t            \t <a href=\"javascript:void(0)\" class=\"easyui-linkbutton picFileUpload\">上传图片</a>\r\n");
      out.write("\t            \t <div class=\"pics\"><ul></ul></div>\r\n");
      out.write("\t                 <input type=\"hidden\" name=\"image\"/>\r\n");
      out.write("\t            </td>\r\n");
      out.write("\t        </tr>\r\n");
      out.write("\t        <tr>\r\n");
      out.write("\t            <td>商品描述:</td>\r\n");
      out.write("\t            <td>\r\n");
      out.write("\t                <textarea style=\"width:800px;height:300px;visibility:hidden;\" name=\"desc\"></textarea>\r\n");
      out.write("\t            </td>\r\n");
      out.write("\t        </tr>\r\n");
      out.write("\t        <tr class=\"params hide\">\r\n");
      out.write("\t        \t<td>商品规格:</td>\r\n");
      out.write("\t        \t<td>\r\n");
      out.write("\t        \t\t\r\n");
      out.write("\t        \t</td>\r\n");
      out.write("\t        </tr>\r\n");
      out.write("\t    </table>\r\n");
      out.write("\t    <input type=\"hidden\" name=\"itemParams\"/>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t<div style=\"padding:5px\">\r\n");
      out.write("\t    <a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" onclick=\"submitForm()\">提交</a>\r\n");
      out.write("\t    <a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" onclick=\"clearForm()\">重置</a>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t//编辑器参数\r\n");
      out.write("\tkingEditorParams = {\r\n");
      out.write("\t\tfilePostName  : \"uploadFile\",  //上传的文件名 \r\n");
      out.write("\t\tuploadJson : '/rest/pic/upload', //上传的路径\r\n");
      out.write("\t\tdir : \"image\"   //上传的文件类型\r\n");
      out.write("\t};\r\n");
      out.write("\t\r\n");
      out.write("\tvar itemAddEditor ;\r\n");
      out.write("\t\r\n");
      out.write("\t//页面加载完时执行以下逻辑\r\n");
      out.write("\t$(function(){\r\n");
      out.write("\t\t//创建富文本编辑器\r\n");
      out.write("\t\titemAddEditor = KindEditor.create(\"#itemAddForm [name=desc]\", kingEditorParams);\r\n");
      out.write("\t\t//初始化类目选择\r\n");
      out.write("\t\tinitItemCat();\r\n");
      out.write("\t\t//初始化图片上传\r\n");
      out.write("\t\tinitPicUpload();\r\n");
      out.write("\t});\r\n");
      out.write("\t\r\n");
      out.write("\t//提交商品信息到后台\r\n");
      out.write("\tfunction submitForm(){\r\n");
      out.write("\t\t//校验表单\r\n");
      out.write("\t\tif(!$('#itemAddForm').form('validate')){\r\n");
      out.write("\t\t\t$.messager.alert('提示','表单还未填写完成!');\r\n");
      out.write("\t\t\treturn ;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//把富文本编辑器编辑区域的html代码。同步到多行文本中，向后台提交的是多行文本\r\n");
      out.write("\t\t//因为编辑器的编辑区域是div标签，不能提交\r\n");
      out.write("\t\titemAddEditor.sync();\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t//提交到后台的RESTful\r\n");
      out.write("\t\t$.ajax({\r\n");
      out.write("\t\t   type: \"POST\",\r\n");
      out.write("\t\t   url: \"/rest/item\",\r\n");
      out.write("\t\t   data: $(\"#itemAddForm\").serialize(),\r\n");
      out.write("\t\t   success: function(msg){\r\n");
      out.write("\t\t\t   if(msg == \"0\"){\r\n");
      out.write("\t\t\t\t   $.messager.alert('提示','新增商品成功!');  \r\n");
      out.write("\t\t\t   }else{\r\n");
      out.write("\t\t\t\t   $.messager.alert('提示','新增商品发生异常，保存失败!'); \r\n");
      out.write("\t\t\t   }\r\n");
      out.write("\t\t   },\r\n");
      out.write("\t\t   error: function(){\r\n");
      out.write("\t\t\t   $.messager.alert('提示','新增商品失败!');\r\n");
      out.write("\t\t   }\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction clearForm(){\r\n");
      out.write("\t\t$('#itemAddForm').form('reset');\r\n");
      out.write("\t\titemAddEditor.html('');\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t//类目选择初始化\r\n");
      out.write("\tfunction initItemCat(){\r\n");
      out.write("\t\t//获取class为selectItemCat的元素，其实就是类目选择按钮\r\n");
      out.write("\t\tvar selectItemCat = $(\".selectItemCat\");\r\n");
      out.write("\t\t//给类目选择按钮增加点击事件\r\n");
      out.write("   \t\tselectItemCat.click(function(){\r\n");
      out.write("   \t\t\t//添加div标签，并设置css属性\r\n");
      out.write("  \t\t\t//在div标签里面添加ul标签，并打开窗口\r\n");
      out.write("   \t\t\t$(\"<div>\").css({padding:\"5px\"}).html(\"<ul>\")\r\n");
      out.write("   \t\t\t.window({\r\n");
      out.write("   \t\t\t\t//窗口属性设置\r\n");
      out.write("   \t\t\t\twidth:'500',\r\n");
      out.write("   \t\t\t    height:\"450\",\r\n");
      out.write("   \t\t\t    modal:true,\r\n");
      out.write("   \t\t\t    closed:true,\r\n");
      out.write("   \t\t\t    iconCls:'icon-save',\r\n");
      out.write("   \t\t\t    title:'选择类目',\r\n");
      out.write("   \t\t\t\t//当窗口打开后执行的逻辑\r\n");
      out.write("   \t\t\t    onOpen : function(){\r\n");
      out.write("   \t\t\t   \t\t//这里的this是打开的窗口本身\r\n");
      out.write("   \t\t\t    \tvar _win = this;\r\n");
      out.write("   \t\t\t   \t\t//在窗口范围内，搜索ul标签\r\n");
      out.write("  \t\t\t    \t//找到ul标签，并创建EasyUI树\r\n");
      out.write("   \t\t\t    \t$(\"ul\",_win).tree({\r\n");
      out.write("   \t\t\t    \t\t//异步树，发起请求，创建树\r\n");
      out.write("   \t\t\t    \t\turl:'/rest/item/cat',\r\n");
      out.write("   \t\t\t    \t\tmethod:'GET',\r\n");
      out.write("   \t\t\t    \t\tanimate:true,\r\n");
      out.write("   \t\t\t    \t\t//给树上的所有节点添加点击事件\r\n");
      out.write("   \t\t\t    \t\tonClick : function(node){\r\n");
      out.write("   \t\t\t    \t\t\tif($(this).tree(\"isLeaf\",node.target)){\r\n");
      out.write("   \t\t\t    \t\t\t\t// 填写到cid中\r\n");
      out.write("   \t\t\t    \t\t\t\tselectItemCat.parent().find(\"[name=cid]\").val(node.id);\r\n");
      out.write("   \t\t\t    \t\t\t\tselectItemCat.next().text(node.text);\r\n");
      out.write("   \t\t\t    \t\t\t\t$(_win).window('close');\r\n");
      out.write("   \t\t\t    \t\t\t}\r\n");
      out.write("   \t\t\t    \t\t}\r\n");
      out.write("   \t\t\t    \t});\r\n");
      out.write("   \t\t\t    },\r\n");
      out.write("   \t\t\t    onClose : function(){\r\n");
      out.write("   \t\t\t    \t$(this).window(\"destroy\");\r\n");
      out.write("   \t\t\t    }\r\n");
      out.write("   \t\t\t}).window('open');\r\n");
      out.write("   \t\t});\r\n");
      out.write("    }\r\n");
      out.write("\t\r\n");
      out.write("\t//图片上传初始化\r\n");
      out.write("\tfunction initPicUpload(){\r\n");
      out.write("\t\t//class选择器，其实获取到的就是上传图片按钮，绑定点击事件\r\n");
      out.write("       \t$(\".picFileUpload\").click(function(){\r\n");
      out.write("       \t\t//id选择器，其实获取到的就是form表单\r\n");
      out.write("       \t\tvar form = $('#itemAddForm');\r\n");
      out.write("       \t\t//加载多图片上传组件（可参考富文本编辑器的文档）\r\n");
      out.write("       \t\tKindEditor.editor(kingEditorParams).loadPlugin('multiimage',function(){\r\n");
      out.write("       \t\t\t//editor:就是编辑器本身\r\n");
      out.write("       \t\t\tvar editor = this;\r\n");
      out.write("       \t\t\t//执行插件的逻辑，显示上传界面\r\n");
      out.write("       \t\t\teditor.plugin.multiImageDialog({\r\n");
      out.write("       \t\t\t\t//当点击“全部插入”按钮，执行以下逻辑\r\n");
      out.write("       \t\t\t\t//urlList：多图片上传成功后，返回的图片url\r\n");
      out.write("\t\t\t\t\tclickFn : function(urlList) {\r\n");
      out.write("\t\t\t\t\t\t//获取class为pics的li的标签，删除，清空之前上传的图片\r\n");
      out.write("\t\t\t\t\t\t$(\".pics li\").remove();\r\n");
      out.write("\t\t\t\t\t\t//声明图片url数组\r\n");
      out.write("\t\t\t\t\t\tvar imgArray = [];\r\n");
      out.write("\t\t\t\t\t\t//遍历返回的图片url\r\n");
      out.write("\t\t\t\t\t\t//i遍历的坐标，data遍历的变量\r\n");
      out.write("\t\t\t\t\t\tKindEditor.each(urlList, function(i, data) {\r\n");
      out.write("\t\t\t\t\t\t\t//从遍历的数据中获取url，其实就是获取图片的url\r\n");
      out.write("\t\t\t\t\t\t\t//放到声明数组中\r\n");
      out.write("\t\t\t\t\t\t\timgArray.push(data.url);\r\n");
      out.write("\t\t\t\t\t\t\t//获取class为pics的ul标签\r\n");
      out.write("\t\t\t\t\t\t\t//在后面追加li标签，回显上传成功的图片\r\n");
      out.write("\t\t\t\t\t\t\t$(\".pics ul\").append(\"<li><a href='\"+data.url+\"' target='_blank'><img src='\"+data.url+\"' width='80' height='50' /></a></li>\");\r\n");
      out.write("\t\t\t\t\t\t});\r\n");
      out.write("\t\t\t\t\t\t//获取name=image的元素，其实就是获取图片上传的input标签\r\n");
      out.write("\t\t\t\t\t\t//往input标签里赋值\r\n");
      out.write("\t\t\t\t\t\t//imgArray.join(\",\")：把数据转为字符串，数组中的元素用，分隔\r\n");
      out.write("\t\t\t\t\t\tform.find(\"[name=image]\").val(imgArray.join(\",\"));\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t//关闭上传界面\r\n");
      out.write("\t\t\t\t\t\teditor.hideDialog();\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("       \t\t});\r\n");
      out.write("       \t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
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
