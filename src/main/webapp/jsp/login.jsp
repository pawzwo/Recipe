<%--
  Created by IntelliJ IDEA.
  User: elkuchta
  Date: 17.11.2021
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/jsp/headrers&footers/header.jsp" %>
<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-25">
                <form class="padding-small text-center" action="/login" method="POST">
                    <h1 class="text-color-darker">Logowanie</h1>
                    <c:if test="${param.email==0}">
                        <label style="color: red">Niepoprawny email, pij Buerlecytyne zamiast diety układać!</label>
                    </c:if>
                    <div class="form-group">
                        <label for="email"></label>
                        <input type="text" class="form-control" id="email" name="email" placeholder="podaj adres email">
                    </div>
                    <c:if test="${param.password==0}">
                        <label style="color: red">Niepoprawne hasło, cholerny hakierze nie dostaniesz naszych przepisów!</label>
                    </c:if>
                    <div class="form-group">
                        <label for="password"></label>
                        <input type="text" class="form-control" id="password" name="password" placeholder="podaj hasło">
                    </div>
                    <button class="btn btn-color rounded-0" type="submit">Zaloguj</button>
                </form>
            </div>
        </div>
    </div>
</section>


<%@ include file="/jsp/headrers&footers/footer.jsp" %>

