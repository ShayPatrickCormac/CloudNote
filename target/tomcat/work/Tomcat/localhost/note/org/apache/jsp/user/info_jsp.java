/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2021-06-05 01:56:11 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class info_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"col-md-9\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <script>\r\n");
      out.write("        function checkUser(){\r\n");
      out.write("            var nickName=$('#nick').val();\r\n");
      out.write("            if(nickName.length==0){\r\n");
      out.write("                $(\"#msg\").html(\"Nickname can't be empty\");\r\n");
      out.write("                return false;\r\n");
      out.write("            }\r\n");
      out.write("            return true;\r\n");
      out.write("        }\r\n");
      out.write("    </script>\r\n");
      out.write("    <div class=\"data_list\">\r\n");
      out.write("        <div class=\"data_list_title\"><span class=\"glyphicon glyphicon-edit\"></span>&nbsp;User Center </div>\r\n");
      out.write("        <div class=\"container-fluid\">\r\n");
      out.write("            <div class=\"row\" style=\"padding-top: 20px;\">\r\n");
      out.write("                <div class=\"col-md-8\">\r\n");
      out.write("                    <form class=\"form-horizontal\" method=\"post\" action=\"user?act=save\" enctype=\"multipart/form-data\" onsubmit=\"return checkUser();\">\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <input type=\"hidden\" name=\"act\" value=\"save\">\r\n");
      out.write("                            <label for=\"nickName\" class=\"col-sm-2 control-label\">NickName</label>\r\n");
      out.write("                            <div class=\"col-sm-3\">\r\n");
      out.write("                                <input class=\"form-control\" name=\"nick\" id=\"nickName\" placeholder=\"NickName\" value=\"Test\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <label for=\"img\" class=\"col-sm-2 control-label\">Avatar</label>\r\n");
      out.write("                            <div class=\"col-sm-5\">\r\n");
      out.write("                                <input type=\"file\" id=\"img\" name=\"img\">\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <label for=\"mood\" class=\"col-sm-2 control-label\">mood</label>\r\n");
      out.write("                            <div class=\"col-sm-10\">\r\n");
      out.write("                                <textarea class=\"form-control\" name=\"mood\" id=\"mood\" rows=\"3\">Test2</textarea>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"form-group\">\r\n");
      out.write("                            <div class=\"col-sm-offset-2 col-sm-10\">\r\n");
      out.write("                                <button type=\"submit\" id=\"btn\" class=\"btn btn-success\">Modify</button>&nbsp;&nbsp;<span style=\"color:red\" id=\"msg\"></span>\r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </form>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"col-md-4\"><img style=\"width:260px;height:200px\" src=\"/statics/images/h2.jpg\"></div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("    <script>\r\n");
      out.write("        $(function(){\r\n");
      out.write("\r\n");
      out.write("                var target=$(\"#nickName\");\r\n");
      out.write("                target.blur(\r\n");
      out.write("                    function(){\r\n");
      out.write("                        $(\"#btn\").attr('disabled',false);\r\n");
      out.write("                        $(\"#msg\").html('');\r\n");
      out.write("                        var value =target.val();\r\n");
      out.write("                        if(value.length==0 ||value=='Test2'){\r\n");
      out.write("                            target.val('Test2');\r\n");
      out.write("                            return ;\r\n");
      out.write("                        }\r\n");
      out.write("\r\n");
      out.write("                        //ajax验证\r\n");
      out.write("                        $.getJSON(\"user\",{\r\n");
      out.write("                            act:'unique',\r\n");
      out.write("                            nick:value\r\n");
      out.write("                        },function(data){\r\n");
      out.write("                            if(data.resultCode==-1){\r\n");
      out.write("                                $(\"#msg\").html(value+\"Username already exists\");\r\n");
      out.write("                                target.val('');\r\n");
      out.write("                                $(\"#btn\").attr('disabled',true);\r\n");
      out.write("                            }else{\r\n");
      out.write("                                $(\"#btn\").attr('disabled',false);\r\n");
      out.write("                            }\r\n");
      out.write("                        });\r\n");
      out.write("                    }\r\n");
      out.write("\r\n");
      out.write("                );\r\n");
      out.write("            }\r\n");
      out.write("        );\r\n");
      out.write("    </script>\r\n");
      out.write("</div>");
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