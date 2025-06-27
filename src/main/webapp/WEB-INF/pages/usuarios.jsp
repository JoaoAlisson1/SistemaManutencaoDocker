<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <title>Usuários</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .bg-gradient-custom {
      background: linear-gradient(135deg, #2c3e50, #0208b4);
      min-height: 100vh;
    }
  </style>
</head>
<body class="bg-gradient-custom text-white">

<div class="container py-5">
  <!-- Formulário -->
  <div class="row justify-content-center">
    <div class="col-lg-6 col-md-8">
      <div class="card shadow">
        <div class="card-header bg-dark text-white">
          <h4 class="mb-0">
            ${usuarioEditar != null ? 'Editar Usuário' : 'Cadastro de Usuários'}
          </h4>
        </div>
        <div class="card-body">
          <form action="usuario" method="post">

            <c:if test="${usuarioEditar != null}">
              <input type="hidden" name="id" value="${usuarioEditar.id}" />
            </c:if>

            <div class="mb-3">
              <label for="email" class="form-label">Email</label>
              <input type="text" name="email" class="form-control" placeholder="Email"
                     value="${usuarioEditar != null ? usuarioEditar.email : ''}" required />
            </div>

            <div class="mb-3">
              <label for="senha" class="form-label">Senha</label>
              <input type="password" name="senha" class="form-control" placeholder="Senha"
                     value="${usuarioEditar != null ? usuarioEditar.senha : ''}" required />
            </div>

            <div class="d-grid">
              <input type="submit" class="btn btn-success"
                     value="${usuarioEditar != null ? 'Salvar Alterações' : 'Cadastrar'}" />
            </div>
          </form>
        </div>
      </div>

      <c:if test="${not empty msg}">
        <div class="alert alert-info text-center mt-3">${msg}</div>
      </c:if>
    </div>
  </div>

  <!-- Lista de usuários -->
  <div class="row justify-content-center mt-5">
    <div class="col-lg-10">
      <h4 class="text-center mb-3">Lista de Usuários</h4>
      <div class="table-responsive">
        <table class="table table-bordered table-striped align-middle">
          <thead class="table-dark">
          <tr>
            <th>Email</th>
            <th>Ativo</th>
            <th colspan="2" class="text-center">Ações</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="usuario" items="${usuarios}">
            <tr>
              <td>${usuario.email}</td>
              <td>${usuario.ativo ? 'Sim' : 'Não'}</td>
              <td class="text-center">
                <a href="usuario?opcao=editar&&info=${usuario.id}" class="btn btn-sm btn-warning">Editar</a>
              </td>
              <td class="text-center">
                <a href="usuario?opcao=excluir&&info=${usuario.id}" class="btn btn-sm btn-danger"
                   onclick="return confirm('Deseja realmente excluir?');">Excluir</a>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>

      <div class="text-end mt-4">
        <a href="dashboard" class="btn btn-secondary">Voltar ao Dashboard</a>
      </div>
    </div>
  </div>
</div>

</body>
</html>
