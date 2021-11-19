<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/headrers&footers/header_app.jsp" %>
<div class="m-4 p-3 width-medium">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding"><h3 class="color-header text-uppercase">Lista Przepisów</h3></div>
            <div class="col noPadding d-flex justify-content-end mb-2"><a href="/app/recipe/add" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Dodaj przepis</a></div>
        </div>
        <c:if test="${planName!=null}">
            <div style="color: red" class="col noPadding"><h5 class="color-header text-uppercase">Nie można usunąć przepisu ponieważ znajduje się w planie: ${planName}</h5></div>
        </c:if>
        <table class="table border-bottom schedules-content">
            <thead>
            <tr class="d-flex text-color-darker">
                <th scope="col" class="col-1">ID</th>
                <th scope="col" class="col-2">NAZWA</th>
                <th scope="col" class="col-7">OPIS</th>
                <th scope="col" class="col-2 center">AKCJE</th>
            </tr>
            </thead>
            <tbody class="text-color-lighter">
            <c:forEach items="${allAdminRecipe}" var="recipe">
            <tr class="d-flex">
                <th scope="row" id="${recipe.id}" class="col-1">${recipe.id}</th>
                <td class="col-2">
                    ${recipe.name}
                </td>
                <td class="col-7"> ${recipe.description} </td>
                <c:if test="${delete!=recipe.id}">
                <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                    <a href="/app/recipe/list?recipeId=${recipe.id}#${recipe.id}" class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                    <a href="/app/recipe/details?recipeId=${recipe.id}&list=1" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                    <a href="/app/recipe/edit?recipeId=${recipe.id}" class="btn btn-warning rounded-0 text-light m-1">Edytuj</a>
                </td>
                </c:if>
                <c:if test="${delete==recipe.id}">
                    <td class="col-2 d-flex align-items-center justify-content-center flex-wrap">
                        <div class="col noPadding"><h5 class="color-header text-uppercase">Jestes pewien ze chcesz usunać</h5></div>
                        <a href="/app/delete/recipe?recipeId=${recipe.id}" class="btn btn-danger rounded-0 text-light m-1">Tak, Usuń</a>
                        <a href="/app/recipe/list" class="btn btn-warning rounded-0 text-light m-1">Anuluj</a>
                    </td>
                </c:if>
            </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="/jsp/headrers&footers/footer_app.jsp" %>