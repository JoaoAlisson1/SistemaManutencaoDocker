<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Login</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="CSS/style.css">


</head>
<body>

<div class="login-box text-center">
    <div class="logo">
        <img src="imagens/aviation_logo-22.jpg" alt="logo">
    </div>
    <h2 class="mb-3 text-white">Login</h2>
    <p class="text-white">Por favor, insira seu email e senha</p>

    <form action="login" method="post">
        <div class="form-group input-group mb-3">
            <span class="fa fa-user"></span>
            <input type="email" class="form-control" name="email" placeholder="E-mail" required>
        </div>

        <div class="form-group input-group mb-3">
            <span class="fa fa-lock"></span>
            <input type="password" class="form-control" name="senha" placeholder="Senha" required>
        </div>

        <button type="submit" class="btn btn-outline-light btn-lg w-100">Logar</button>
    </form>

    <c:if test="${not empty msg}">
        <div class="alert alert-warning mt-3" role="alert">
                ${msg}
        </div>
    </c:if>

</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
