<%@ page import="java.util.* "%>
<%@ page import="olona.Employe" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    Hellooooooo
    <%
        Vector v=(Vector)request.getAttribute("listeEmp");
        Object[] objs=v.toArray();
        for(int i=0;i<objs.length;i++){
            out.println(((Employe)objs[i]).getNom());
        }
    %>
</body>
</html>