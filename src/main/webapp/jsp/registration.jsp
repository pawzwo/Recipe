<%--
  Created by IntelliJ IDEA.
  User: pawel
  Date: 16.11.2021
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/jsp/header.jsp" %>
<main>
    <section class="dashboard-section">
        <div class="container pt-4 pb-4">
            <div class="border-dashed view-height">
                <div class="container w-25">
                    <!-- fix action, method -->
                    <!-- add name attribute for all inputs -->
                    <form class="padding-small text-center" method="post">
                        <h1 class="text-color-darker">Rejestracja</h1>
                        <div class="form-group">
                            <input type="text" class="form-control" id="name" name="name" placeholder="podaj imię" value="${param.name}" required>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="surname" name="surname" placeholder="podaj nazwisko" value="${param.surname}" required>
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-control" id="email" name="email" placeholder="podaj email" value="${param.email}" required>
                        </div>
                        <c:if test="${param.pass==0}">
                            <label style="color: red">podane hasła róznią się</label>
                        </c:if>

                        <div class="form-group">
                            <input type="password" class="form-control" id="password" name="password" placeholder="podaj hasło" required>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" id="repassword" name="repassword" placeholder="powtórz hasło" required>
                        </div>
                        <button class="btn btn-color rounded-0" type="submit">Zarejestruj</button>
                    </form>
                </div>
            </div>
        </div>
    </section>
</main>

<%@ include file="/jsp/footer.jsp" %>
