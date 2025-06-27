<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Mecânicos</title>

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
                    <h3 class="mb-0">${mecanicoEditar != null ? 'Editar Mecânico' : 'Cadastro de Mecânicos'}</h3>
                </div>
                <div class="card-body">
                    <form action="mecanico" method="post">
                        <c:if test="${mecanicoEditar != null}">
                            <input type="hidden" name="id" value="${mecanicoEditar.id}" />
                        </c:if>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="nome" class="form-label">Nome:</label>
                                <input type="text" class="form-control" name="nome"
                                       value="${mecanicoEditar != null ? mecanicoEditar.nome : ''}" required />
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="registroAnac" class="form-label">Registro ANAC:</label>
                                <input type="text" class="form-control" name="registroAnac"
                                       value="${mecanicoEditar != null ? mecanicoEditar.registroAnac : ''}" required />
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="especialidade" class="form-label">Especialidade:</label>
                                <input type="text" class="form-control" name="especialidade"
                                       value="${mecanicoEditar != null ? mecanicoEditar.especialidade : ''}" required />
                            </div>
                            <div class="col-md-6 mb-3 d-flex align-items-center">
                                <div class="form-check mt-4">
                                    <input class="form-check-input" type="checkbox" name="ativo"
                                           id="ativo"
                                           <c:if test="${mecanicoEditar != null && mecanicoEditar.ativo}">checked</c:if> />
                                    <label class="form-check-label" for="ativo">Ativo</label>
                                </div>
                            </div>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-success">
                                ${mecanicoEditar != null ? 'Salvar Alterações' : 'Cadastrar'}
                            </button>
                        </div>
                    </form>

                    <c:if test="${not empty msg}">
                        <div class="alert alert-info mt-3">${msg}</div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

    <!-- Lista de Mecânicos -->
    <div class="row justify-content-center mt-5">
        <div class="col-lg-10">
            <h4 class="mb-3 text-white">Lista de Mecânicos</h4>
            <div class="table-responsive">
                <table class="table table-bordered table-striped align-middle">
                    <thead class="table-dark">
                    <tr>
                        <th>Nome</th>
                        <th>Registro ANAC</th>
                        <th>Especialidade</th>
                        <th>Ativo</th>
                        <th colspan="2">Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="mecanico" items="${mecanicos}">
                        <tr>
                            <td>${mecanico.nome}</td>
                            <td>${mecanico.registroAnac}</td>
                            <td>${mecanico.especialidade}</td>
                            <td>${mecanico.ativo ? 'Sim' : 'Não'}</td>
                            <td>
                                <a href="mecanico?opcao=editar&info=${mecanico.id}" class="btn btn-sm btn-warning">Editar</a>
                            </td>
                            <td>
                                <a href="mecanico?opcao=excluir&info=${mecanico.id}" class="btn btn-sm btn-danger"
                                   onclick="return confirm('Tem certeza que deseja excluir este mecânico?')">Excluir</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="text-end mt-3">
                <a href="dashboard" class="btn btn-secondary">Voltar ao Dashboard</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS (opcional, para componentes como dropdowns, modals etc.) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
