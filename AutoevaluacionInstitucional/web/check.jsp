<%
    String user = request.getParameter("un");
    String pass = request.getParameter("pw");
    if (user.equalsIgnoreCase("admin")
            && pass.equals("test")) {
        String user2 = request.getParameter("un");
        session.setMaxInactiveInterval(24 * 60 * 60);
        session.setAttribute("user", user2);
        out.print(0);
    } else {
        out.print(1);
    }
%>






