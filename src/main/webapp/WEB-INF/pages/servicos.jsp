<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8" />
    <title>Ordens de Serviço</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
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
                    <h3 class="mb-0">
                        ${ordemEditar != null ? 'Editar Ordem de Serviço' : 'Cadastro de Ordem de Serviço'}
                    </h3>
                </div>
                <div class="card-body">
                    <form action="servico" method="post">
                        <c:if test="${ordemEditar != null}">
                            <input type="hidden" name="id" value="${ordemEditar.id}" />
                        </c:if>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="descricaoServico" class="form-label">Descrição do Serviço</label>
                                <input type="text" name="descricaoServico" class="form-control"
                                       value="${ordemEditar != null ? ordemEditar.descricaoServico : ''}" required />
                            </div>
                            <div class="col-md-6">
                                <label for="tipoManutencao" class="form-label">Tipo de Manutenção</label>
                                <select name="tipoManutencao" class="form-select" required>
                                    <option value="">Selecione</option>
                                    <option value="PREVENTIVA" ${ordemEditar != null && ordemEditar.tipoManutencao == 'PREVENTIVA' ? 'selected' : ''}>Preventiva</option>
                                    <option value="CORRETIVA" ${ordemEditar != null && ordemEditar.tipoManutencao == 'CORRETIVA' ? 'selected' : ''}>Corretiva</option>
                                </select>
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="dataSolicitacao" class="form-label">Data de Solicitação</label>
                                <input type="date" name="dataSolicitacao" class="form-control"
                                       value="${ordemEditar != null ? ordemEditar.dataSolicitacao : ''}" required />
                            </div>
                            <div class="col-md-6">
                                <label for="dataConclusao" class="form-label">Data de Conclusão</label>
                                <input type="date" name="dataConclusao" class="form-control"
                                       value="${ordemEditar != null && ordemEditar.dataConclusao != null ? ordemEditar.dataConclusao : ''}" />
                            </div>
                        </div>

                        <div class="row mb-3">
                            <div class="col-md-6">
                                <label for="status" class="form-label">Status</label>
                                <select name="status" class="form-select" required>
                                    <option value="">Selecione</option>
                                    <option value="PENDENTE" ${ordemEditar != null && ordemEditar.status == 'PENDENTE' ? 'selected' : ''}>Pendente</option>
                                    <option value="EM_EXECUCAO" ${ordemEditar != null && ordemEditar.status == 'EM_EXECUCAO' ? 'selected' : ''}>Em execução</option>
                                    <option value="CONCLUIDO" ${ordemEditar != null && ordemEditar.status == 'CONCLUIDO' ? 'selected' : ''}>Concluída</option>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label for="aeronave_id" class="form-label">Aeronave</label>
                                <select name="aeronave_id" class="form-select" required>
                                    <option value="">Selecione uma aeronave</option>
                                    <c:forEach var="aeronave" items="${aeronaves}">
                                        <option value="${aeronave.id}"
                                                <c:if test="${ordemEditar != null && ordemEditar.aeronave.id == aeronave.id}">selected</c:if>>
                                                ${aeronave.modelo} - ${aeronave.registroNacional}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="mecanico_id" class="form-label">Mecânico</label>
                            <select name="mecanico_id" class="form-select" required>
                                <option value="">Selecione um mecânico</option>
                                <c:forEach var="mecanico" items="${mecanicos}">
                                    <c:if test="${mecanico.ativo}">
                                        <option value="${mecanico.id}"
                                                <c:if test="${ordemEditar != null && ordemEditar.mecanico.id == mecanico.id}">selected</c:if>>
                                                ${mecanico.nome} - ${mecanico.registroAnac}
                                        </option>
                                    </c:if>
                                </c:forEach>
                            </select>

                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-success">
                                ${ordemEditar != null ? 'Salvar Alterações' : 'Cadastrar'}
                            </button>
                        </div>
                    </form>
                </div>
            </div>

            <!-- Mensagem de retorno -->
            <c:if test="${not empty msg}">
                <div class="alert alert-info mt-3">${msg}</div>
            </c:if>
        </div>
    </div>

    <!-- Lista de Ordens de Serviço -->
    <div class="row justify-content-center mt-5">
        <div class="col-lg-10">
            <h4 class="mb-3 text-white">Lista de Ordens de Serviço</h4>
            <div class="table-responsive">
                <table class="table table-bordered table-striped align-middle">
                    <thead class="table-dark">
                    <tr>
                        <th>Descrição do Serviço</th>
                        <th>Tipo de Manutenção</th>
                        <th>Data de Solicitação</th>
                        <th>Data de Conclusão</th>
                        <th>Status</th>
                        <th>Aeronave</th>
                        <th>Mecânico</th>
                        <th colspan="2" class="text-center">Ações</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="ordem" items="${ordens}">
                        <tr>
                            <td>${ordem.descricaoServico}</td>
                            <td>${ordem.tipoManutencao}</td>
                            <td>${ordem.dataSolicitacao}</td>
                            <td><c:out value="${ordem.dataConclusao != null ? ordem.dataConclusao : '-'}" /></td>
                            <td>${ordem.status}</td>
                            <td>${ordem.aeronave.modelo} - ${ordem.aeronave.registroNacional}</td>
                            <td>${ordem.mecanico.nome} - ${ordem.mecanico.registroAnac}</td>
                            <td>
                                <a href="servico?opcao=editar&info=${ordem.id}" class="btn btn-sm btn-warning">Editar</a>
                            </td>
                            <td>
                                <a href="servico?opcao=excluir&info=${ordem.id}" class="btn btn-sm btn-danger"
                                   onclick="return confirm('Deseja realmente excluir?');">Excluir</a>
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
