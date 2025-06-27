<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Aeronaves</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<style>
    .bg-gradient-custom {
        background: linear-gradient(135deg, #2c3e50, #0208b4);
        min-height: 100vh;
    }
</style>
<body class="bg-gradient-custom text-white">

<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="card shadow">
                <div class="card-header bg-dark text-white">
                    <h3 class="mb-0">${aeronaveEditar != null ? 'Editar Aeronave' : 'Cadastro de Aeronaves'}</h3>
                </div>
                <div class="card-body">
                    <form action="aeronave" method="post">
                        <c:if test="${aeronaveEditar != null}">
                            <input type="hidden" name="id" value="${aeronaveEditar.id}" />
                        </c:if>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="modelo" class="form-label">Modelo</label>
                                <input type="text" class="form-control" name="modelo" placeholder="Modelo"
                                       value="${aeronaveEditar != null ? aeronaveEditar.modelo : ''}" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="fabricante" class="form-label">Fabricante</label>
                                <input type="text" class="form-control" name="fabricante" placeholder="Fabricante"
                                       value="${aeronaveEditar != null ? aeronaveEditar.fabricante : ''}" required>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="anoFabricacao" class="form-label">Ano de Fabricação</label>
                                <input type="number" class="form-control" name="anoFabricacao" placeholder="Ano de Fabricação"
                                       value="${aeronaveEditar != null ? aeronaveEditar.anoFabricacao : ''}" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="registroNacional" class="form-label">Registro Nacional</label>
                                <input type="text" class="form-control" name="registroNacional" placeholder="Registro Nacional"
                                       value="${aeronaveEditar != null ? aeronaveEditar.registroNacional : ''}" required>
                            </div>
                        </div>

                        <div class="d-grid">
                            <input type="submit" class="btn btn-success" value="${aeronaveEditar != null ? 'Salvar Alterações' : 'Cadastrar'}" />
                        </div>
                    </form>

                    <!-- Mensagem de retorno -->
                    <c:if test="${not empty msg}">
                        <div class="alert alert-info mt-3">${msg}</div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

    <!-- Lista de Aeronaves -->
    <div class="row justify-content-center mt-5">
        <div class="col-lg-10">
            <h4 class="mb-3 text-white" >Lista de Aeronaves</h4>
            <div class="table-responsive">
                <table class="table table-striped table-bordered">
                    <thead class="table-dark">
                    <tr>
                        <th>Modelo</th>
                        <th>Fabricante</th>
                        <th>Ano de Fabricação</th>
                        <th>Registro Nacional</th>
                        <th colspan="2">Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="aeronave" items="${aeronaves}">
                        <tr>
                            <td>${aeronave.modelo}</td>
                            <td>${aeronave.fabricante}</td>
                            <td>${aeronave.anoFabricacao}</td>
                            <td>${aeronave.registroNacional}</td>
                            <td>
                                <a href="aeronave?opcao=editar&info=${aeronave.id}" class="btn btn-sm btn-warning">Editar</a>
                            </td>
                            <td>
                                <a href="aeronave?opcao=excluir&info=${aeronave.id}" class="btn btn-sm btn-danger"
                                   onclick="return confirm('Tem certeza que deseja excluir esta aeronave?')">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="text-end">
                <a href="dashboard" class="btn btn-secondary mt-3">Voltar ao Dashboard</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS (opcional, apenas se for usar componentes como dropdown, modals etc) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
