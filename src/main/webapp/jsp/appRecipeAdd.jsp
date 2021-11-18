<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/headrers&footers/header_app.jsp" %>

<div class="m-4 p-3 width-medium text-color-darker">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <!-- fix action, method -->
        <!-- add name attribute for all inputs -->
        <form method="post">
            <div class="mt-4 ml-4 mr-4">
                <div class="row border-bottom border-3">
                    <div class="col"><h3 class="color-header text-uppercase">Nowy przepis</h3></div>
                    <div class="col d-flex justify-content-end mb-2">
                        <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button>
                    </div>
                </div>
                <c:if test="${param.badData==1}">
                    <label style="color: #f37878">Wszytskie rekordy musza byc uzupełnione</label>
                </c:if>
                <c:if test="${param.notCreated==1}">
                    <label style="color: #f37878">Ups... wystąpił bład w trakcie tworzenia przepisu spróbuj
                        jeszcze raz </label>
                </c:if>

                <table class="table borderless">
                    <tbody>
                    <tr class="d-flex">
                        <th scope="row" class="col-2">Nazwa Przepisu</th>
                        <td class="col-7">
                            <input name="name" class="w-100 p-1" value="" required>
                        </td>
                    </tr>
                    <tr class="d-flex">
                        <th scope="row" class="col-2">Opis przepisu</th>
                        <td class="col-7"><textarea class="w-100 p-1" rows="5" name="description" required></textarea></td>
                    </tr>
                    <tr class="d-flex">
                        <th scope="row" class="col-2">Przygotowanie (minuty)</th>
                        <td class="col-3">
                            <input class="p-1" type="number" value="" name="preparationTime" required>
                        </td>
                    </tr>
                    </tbody>
                </table>

                <div class="row d-flex">
                    <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Sposób
                        przygotowania</h3></div>
                    <div class="col-2"></div>
                    <div class="col-5 border-bottom border-3"><h3 class="text-uppercase">Składniki</h3></div>
                </div>
                <div class="row d-flex">
                    <div class="col-5 p-4">
                        <textarea class="w-100 p-1" rows="10" name="preparation" required></textarea>
                    </div>
                    <div class="col-2"></div>

                    <div class="col-5 p-4">
                        <textarea class="w-100 p-1" rows="10" name="ingredients" required></textarea>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file="/jsp/headrers&footers/footer_app.jsp" %>