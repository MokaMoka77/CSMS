<%--
  Created by IntelliJ IDEA.
  User: KhoaBug
  Date: 7/16/2023
  Time: 5:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Confirm Modal-->
<div class="modal fade" id="reactivateModal" data-bs-backdrop="static" tabindex="-1" role="dialog"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="${pageContext.request.contextPath}/active-user"
                  method="get">
                <div class="modal-header">
                    <h5 class="modal-title" id="staticBackdropLabel">Kich hoat</h5>
                    <button class="btn-close" type="button" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">Ban co chac chan muon huy kich hoat tai khoan nay</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button"
                            data-bs-dismiss="modal">Khong
                    </button>
                    <button class="btn btn-primary"
                            type="submit">Co
                    </button>
                    <input name="reactivate-id" type="hidden" id="reactivate-id">
                </div>
            </form>
        </div>
    </div>
</div>
