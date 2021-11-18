<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/headrers&footers/header_app.jsp" %>

        <div class="m-4 p-4 width-medium">
            <div class="dashboard-header m-4">
                <div class="dashboard-menu">
                    <div class="menu-item border-dashed">
                        <a href="/app/recipe/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj przepis</span>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="/app/plan/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj plan</span>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="/app/recipe/plan/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj przepis do planu</span>
                        </a>
                    </div>
                </div>

                <div class="dashboard-alerts">
                    <div class="alert-item alert-info">
                        <i class="fas icon-circle fa-info-circle"></i>
                        <span class="font-weight-bold">Liczba przepisów: ${recipeQua}</span>
                    </div>
                    <div class="alert-item alert-light">
                        <i class="far icon-calendar fa-calendar-alt"></i>
                        <span class="font-weight-bold">Liczba planów: ${planQua}</span>
                    </div>
                </div>
            </div>
            <div class="m-4 p-4 border-dashed">
                <h2 class="dashboard-content-title">
                    <span>Ostatnio dodany plan:</span> ${planName}
                </h2>
                <c:forEach items="${days}" var="day">
                <table class="table">

                    <thead>
                    <tr class="d-flex">
                        <th class="col-2">${day}</th>
                        <th class="col-8"></th>
                        <th class="col-2"></th>
                    </tr>
                    </thead>
                    <c:forEach items="${plan}" var="planday">
                       <c:if test="${planday.dayName.equals(day)}">
                    <tbody>
                    <tr class="d-flex">
                        <td class="col-2">${planday.mealName}</td>
                        <td class="col-8">${planday.recipeName}</td>
                        <td class="col-2"><button type="button" class="btn btn-primary rounded-0">Szczegóły</button></td>
                    </tr>
                    </tbody>
                        </c:if>
                    </c:forEach>
                </table>
                </c:forEach>

            </div>
        </div>

<%@ include file="/jsp/headrers&footers/footer_app.jsp" %>