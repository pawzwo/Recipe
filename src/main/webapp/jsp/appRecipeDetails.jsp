<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/headrers&footers/header_app.jsp" %>
<div class="m-4 p-3 width-medium text-color-darker">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <div class="mt-4 ml-4 mr-4">
            <div class="row border-bottom border-3">
                <div class="col"><h3 class="color-header text-uppercase">Szczegóły przepisu</h3></div>
                <c:if test="${param.list==1}">
                <div class="col d-flex justify-content-end mb-2"><a href="/app/recipe/list" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a></div>
                </c:if>
                <c:if test="${param.dashboard==1}">
                    <div class="col d-flex justify-content-end mb-2"><a href="/app/dashboard" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a></div>
                </c:if>
            </div>

            <table class="table borderless">
                <tbody>
                <tr class="d-flex">
                    <th scope="row" class="col-2">Nazwa Przepisu</th>
                    <td class="col-7">
                        ${recipe.name}
                    </td>
                </tr>
                <tr class="d-flex">
                    <th scope="row" class="col-2">Opis przepisu</th>
                    <td class="col-7">${recipe.description}</td>
                </tr>
                <tr class="d-flex">
                    <th scope="row" class="col-2">Przygotowanie (minuty)</th>
                    <td class="col-7">
                        ${recipe.preparationTime}
                    </td>
                </tr>
                </tbody>
            </table>

            <div class="row d-flex">
                <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Sposób przygotowania</h3></div>
                <div class="col-2"></div>
                <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Składniki</h3></div>
            </div>
            <div class="row d-flex">
                <div class="col-5 p-4">
                    <p>${recipe.preparation}</p>
                </div>
                <div class="col-2"></div>
                <ul class="col-5 p-4 list-unstyled">
                    <c:forEach items="${ingredients}" var="ingr">
                        <li>${ingr}</li>
                    </c:forEach>
                </ul>
            </div>

        </div>
    </div>
</div>

<%@ include file="/jsp/headrers&footers/footer_app.jsp" %>