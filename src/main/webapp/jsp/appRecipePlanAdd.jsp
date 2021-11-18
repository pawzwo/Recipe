<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/headrers&footers/header_app.jsp" %>

<div class="m-4 p-3 width-medium">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <form method="post">
            <div class="row border-bottom border-3 p-1 m-1">
                <div class="col noPadding">
                    <h3 class="color-header text-uppercase">DODAJ PRZEPIS DO PLANU</h3>
                </div>
                <div class="col d-flex justify-content-end mb-2 noPadding">
                    <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button>
                </div>
            </div>

            <div class="schedules-content">
                <c:if test="${param.dataBaseError==1}">
                    <label style="color: #f37878">ups... cos poszło nie przy zapisie do bazy danych</label>
                </c:if>
                <c:if test="${param.inputError==1}">
                    <label style="color: #f37878">prosze uzupełnić wszytskie okna formularza</label>
                </c:if>

                <div class="form-group row">
                    <label for="choosePlan" class="col-sm-2 label-size col-form-label">
                        Wybierz plan
                    </label>
                    <div class="col-sm-3">
                        <select class="form-control" id="choosePlan" name="plan">
                            <c:forEach items="${allPlan}" var="plan">
                                <option>${plan.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="name" class="col-sm-2 label-size col-form-label">
                        Nazwa posiłku
                    </label>
                    <div class="col-sm-10">
                        <input type="text" name="name" class="form-control" value="" id="name"
                               placeholder="Nazwa posiłku" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="number" class="col-sm-2 label-size col-form-label">
                        Numer posiłku
                    </label>
                    <div class="col-sm-2">
                        <input type="number" name="number" class="form-control" value="" id="number"
                               placeholder="Numer posiłki" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="recipie" class="col-sm-2 label-size col-form-label">
                        Przepis
                    </label>
                    <div class="col-sm-4">
                        <select class="form-control" id="recipie" name="recipe">
                            <c:forEach items="${allRecipe}" var="recipe">
                                <option>${recipe.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="day" class="col-sm-2 label-size col-form-label">
                        Dzień
                    </label>
                    <div class="col-sm-2">
                        <select class="form-control" id="day" name="day">
                            <c:forEach items="${allDays}" var="day">
                                <option>${day.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

            </div>
        </form>
    </div>
</div>
<%@ include file="/jsp/headrers&footers/footer_app.jsp" %>