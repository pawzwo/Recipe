<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/headrers&footers/header_app.jsp" %>
<div class="m-4 p-3 width-medium ">
    <div class="dashboard-content border-dashed p-3 m-4">
        <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding">
                <h3 class="color-header text-uppercase">SZCZEGÓŁY PLANU</h3>
            </div>
            <div class="col d-flex justify-content-end mb-2 noPadding">
                <a href="/app/plan/list" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
            </div>
        </div>

        <div class="schedules-content">
            <div class="schedules-content-header">
                <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Nazwa planu
                                </span>
                    <div class="col-sm-10">
                        <p class="schedules-text">${planDescr.name}</p>
                    </div>
                </div>
                <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Opis planu
                                </span>
                    <div class="col-sm-10">
                        <p class="schedules-text">
                            ${planDescr.description}
                        </p>
                    </div>
                </div>
            </div>
            <c:choose>
                <c:when test="${not empty komunikat}">
                    <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    ${komunikat}
                                </span>
                    </div>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${days}" var="day">
                        <table class="table">
                            <thead>
                            <tr class="d-flex">
                                <th class="col-2">${day}</th>
                                <th class="col-7"></th>
                                <th class="col-1"></th>
                                <th class="col-2"></th>
                            </tr>
                            </thead>
                            <tbody class="text-color-lighter">
                            <c:forEach items="${planDays}" var="planDay">
                                <c:if test="${day == planDay.dayName}">
                                    <tr class="d-flex">
                                        <td class="col-2">${planDay.mealName}</td>
                                        <td class="col-7">${planDay.recipeName}</td>
                                        <c:if test="${not empty delete}">
                                            <div class="col noPadding"><h5 class="color-header text-uppercase">Jestes pewien ze chcesz usunać</h5></div>
                                            <a href="/app/recipe/plan/delete?recipePlanId=${planDay.recipePlanId}&planId=${planDescr.id}" class="btn btn-danger rounded-0 text-light m-1">Tak, Usuń</a>
                                            <a href="/app/plan/details?planid=${planDescr.id}" class="btn btn-warning rounded-0 text-light m-1">Anuluj</a>
                                        </c:if>
                                        <c:if test="${empty delete}">
                                            <td class="col-1 center">
                                                <a href="/app/plan/details?planId=${planDescr.id}&delete=1" class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                                            </td>
                                        </c:if>

                                        <td class="col-2 center">
                                            <a href="/app/recipe/details?recipeId=${planDay.recipeId}" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<%@ include file="/jsp/headrers&footers/footer_app.jsp" %>